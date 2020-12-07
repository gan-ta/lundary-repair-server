package com.hs.lunpair.domain.user.contoller;

import com.hs.lunpair.common.model.ResponseData;
import com.hs.lunpair.domain.user.dto.request.UserCreateRequest;
import com.hs.lunpair.domain.user.dto.response.UserCreateResponse;
import com.hs.lunpair.domain.user.service.UserCreateService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/api/users")
public class UserController {

    private final UserCreateService userCreateService;

    private final PasswordEncoder passwordEncoder;

    @ApiOperation("회원가입")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseData createUser(@RequestBody @Valid UserCreateRequest userCreateRequest){
        return new ResponseData(
                new UserCreateResponse(
                        userCreateService.createUser(
                                userCreateRequest.of(
                                        passwordEncoder.encode(userCreateRequest.getPassword())))),
                HttpStatus.CREATED);
    }

}
