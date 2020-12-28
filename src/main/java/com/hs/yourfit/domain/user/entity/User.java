package com.hs.yourfit.domain.user.entity;

import com.hs.yourfit.common.model.BaseEntity;
import com.hs.yourfit.domain.user.dto.request.UserUpdateRequest;
import com.hs.yourfit.domain.user.entity.enums.UserGender;
import com.hs.yourfit.domain.user.entity.enums.UserRole;
import lombok.*;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

//회원 정보
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "tbl_user")
@Entity
@AttributeOverride(name = "id", column = @Column(name = "user_id"))
@Where(clause = "deleted=0")
@AllArgsConstructor
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

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private UserBusinessInfo userBusinessInfo;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserAddress> userAddressList = new ArrayList<>();

    @Builder
    public User(Long id,
                String email,
                String password,
                String name,
                UserPhone phoneNumber1,
                UserPhone phoneNumber2,
                UserGender userGender,
                UserRole userRole,
                UserBusinessInfo userBusinessInfo,
                List<UserAddress> userAddressList){
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.phoneNumber1 = phoneNumber1;
        this.phoneNumber2 = phoneNumber2;
        this.userGender = userGender;
        this.userRole = userRole;
        this.userBusinessInfo = userBusinessInfo;
        this.userAddressList = userAddressList;
    }

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
    }
}
