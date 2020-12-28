package com.hs.yourfit.domain.user.entity.enums;

import com.hs.yourfit.core.error.exception.UserDefineException;
import lombok.Getter;

import java.util.Arrays;

@Getter
public enum UserGender {

    MALE("남성"), FEMALE("여성");

    String gender;

    UserGender(String gender) {
        this.gender = gender;
    }

    //해당 문자열이 열거형에 존재하는지 확인 후 있으면 반환
    public static UserGender findByString(String gender){
        return Arrays.stream(UserGender.values())
                .filter(userSex -> userSex.toString().equalsIgnoreCase(gender))
                .findAny().orElseThrow(() -> new UserDefineException("성별 항목을 찾을 수 없습니다."));
    }
}
