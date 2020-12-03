package com.hs.lunpair.domain.user.entity;

import com.hs.lunpair.core.error.exception.UserDefineException;
import lombok.Getter;

import java.util.Arrays;

@Getter
public enum UserRole {

    CUSTOMER("고객"), SELLER("판매자"),ADMIN("관리자");

    String role;

    UserRole(String role) {
        this.role = role;
    }

    //해당 문자열이 열거형에 존재하는지 확인 후 있으면 반환
    public static UserGender findByString(String role){
        return Arrays.stream(UserGender.values())
                .filter(userSex -> userSex.toString().equalsIgnoreCase(role))
                .findAny().orElseThrow(() -> new UserDefineException("UserSex 항목을 찾을 수 없습니다."));
    }
}
