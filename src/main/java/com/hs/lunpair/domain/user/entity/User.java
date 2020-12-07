package com.hs.lunpair.domain.user.entity;

import com.hs.lunpair.common.model.BaseEntity;
import com.hs.lunpair.domain.user.dto.request.UserUpdateRequest;
import com.hs.lunpair.domain.user.entity.enums.UserGender;
import com.hs.lunpair.domain.user.entity.enums.UserRole;
import lombok.*;
import org.hibernate.annotations.Where;

import javax.persistence.*;

//회원 정보
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "tbl_user")
@Entity
@AttributeOverride(name = "id", column = @Column(name = "user_id"))
@Where(clause = "deleted=0")
@AllArgsConstructor
@Builder
public class User extends BaseEntity {

    @Column(unique = true, name = "identity", nullable = false,length = 50)
    private String email;

    @Column(name = "password", length = 200,nullable = false)
    private String password;

    @Column(name = "name", nullable = false)
    private String name;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "number",column = @Column(name = "phoneNumber1",nullable = false))
    })
    private UserPhone phoneNumber1 = new UserPhone();

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "number",column = @Column(name = "phoneNumber2"))
    })
    private UserPhone phoneNumber2 = new UserPhone();

    @Column(name = "gender",nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UserGender userGender;

    @Column(name = "role",nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UserRole userRole;

//    @Column(name = "fcmToken")
//    private String fcmToken;
//
//    @Column(name = "refreshToken", length = 500)
//    private String refreshToken;

//    public void updateFcmToken(String fcmToken){
//        this.fcmToken = fcmToken;
//    }

    public void updatePw(String password){this.password = password;}

    public void updateUser(UserUpdateRequest userUpdateRequest){
        this.name = userUpdateRequest.getName();
        this.phoneNumber1 = new UserPhone(userUpdateRequest.getPhoneNumber1());
        this.phoneNumber2 = new UserPhone(userUpdateRequest.getPhoneNumber2());
        this.userGender = userUpdateRequest.getUserGender();
    }
}
