package com.hs.yourfit.domain.user.entity.enums;

import com.hs.yourfit.core.error.exception.UserDefineException;
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
    public static UserRole findByString(String role){
        return Arrays.stream(UserRole.values())
                .filter(userSex -> userSex.toString().equalsIgnoreCase(role))
                .findAny().orElseThrow(() -> new UserDefineException("유저 구분 항목을 찾을 수 없습니다."));
    }
}
