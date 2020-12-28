package com.hs.yourfit.domain.user.dto.response;

import com.hs.yourfit.domain.user.entity.enums.UserGender;
import com.hs.yourfit.domain.user.entity.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserGetResponse{
    protected String email;
    protected  String name;
    protected  String phoneNumber1;
    protected String phoneNumber2;
    protected UserGender userGender;
    protected UserRole userRole;
}
