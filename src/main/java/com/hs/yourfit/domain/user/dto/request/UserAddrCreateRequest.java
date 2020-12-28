package com.hs.yourfit.domain.user.dto.request;

import com.hs.yourfit.domain.user.entity.User;
import com.hs.yourfit.domain.user.entity.UserAddress;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@Builder
@Getter
public class UserAddrCreateRequest {

    @NotBlank(message = "주소를 입력해주세요")
    private String baseAddr;

    @NotBlank(message = "주소를 입력해주세요")
    private String detailAddr;

    @NotBlank(message = "우편번호를 입력해주세요")
    private String zipCode;

    public UserAddress representOf(User user){
        return UserAddress.builder()
                .baseAddr(this.baseAddr)
                .detailAddr(this.detailAddr)
                .zipCode(this.zipCode)
                .user(user)
                .represent(true)
                .build();
    }

    public UserAddress anotherOf(User user){
        return UserAddress.builder()
                .baseAddr(this.baseAddr)
                .detailAddr(this.detailAddr)
                .zipCode(this.zipCode)
                .user(user)
                .represent(false)
                .build();
    }
}
