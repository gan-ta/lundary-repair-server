package com.hs.lunpair.domain.user.entity;

import com.hs.lunpair.common.model.BaseEntity;
import com.hs.lunpair.domain.user.dto.request.UserUpdateRequest;
import lombok.*;
import org.hibernate.annotations.Where;

import javax.persistence.*;

//회원 정보 구현
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

    @Column(name = "password", length = 100)
    private String password;

    @Column(name = "name", nullable = false)
    private String name;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "number",column = @Column(name = "homePhone"))
    })
    private UserPhone homePhone = new UserPhone();

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "number",column = @Column(name = "cellPhone"))
    })
    private UserPhone cellPhone = new UserPhone();

    @Column(name = "gender")
    @Enumerated(value = EnumType.STRING)
    private UserGender userGender;

    @Column(name = "role")
    @Enumerated(value = EnumType.STRING)
    private UserRole userRole;

    @Column(name = "fcmToken")
    private String fcmToken;

    @Column(name = "refreshToken", length = 500)
    private String refreshToken;

    @Column(name = "role")

    public void updateFcmToken(String fcmToken){
        this.fcmToken = fcmToken;
    }

    public void updatePw(String password){this.password = password;}

    public void updateUser(UserUpdateRequest userUpdateRequest){
        this.name = userUpdateRequest.getName();
        this.homePhone = new UserPhone(userUpdateRequest.getHomePhoneNumber());
        this.cellPhone = new UserPhone(userUpdateRequest.getCellPhoneNumber());
        this.userGender = userUpdateRequest.getUserGender();
    }
}
