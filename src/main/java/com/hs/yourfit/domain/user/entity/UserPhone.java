package com.hs.yourfit.domain.user.entity;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode
@Getter
public class UserPhone {
    @Column(name = "number")
    private String number;

    public UserPhone(String number) {
        this.number = number;
    }
}
