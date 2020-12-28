package com.hs.yourfit.domain.dress.entity.enums;

import com.hs.yourfit.core.error.exception.UserDefineException;
import lombok.Getter;

import java.util.Arrays;

@Getter
public enum LengthCategory {

    SHORT("짧은 기장"),LONG("긴 기장"),MIDDLE("중간 기장");

    String category;

    LengthCategory(String category) {
        this.category = category;
    }

    //해당 문자열이 열거형에 존재하는지 확인 후 있으면 반환
    public static LengthCategory findByString(String category){
        return Arrays.stream(LengthCategory.values())
                .filter(lengthCategory -> lengthCategory.toString().equalsIgnoreCase(category))
                .findAny().orElseThrow(() -> new UserDefineException("기장 종목을 찾을 수 없습니다."));
    }
}
