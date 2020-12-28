package com.hs.yourfit.domain.dress.controller;

import com.hs.yourfit.common.model.ResponseData;
import com.hs.yourfit.common.model.StringResponse;
import com.hs.yourfit.core.aop.logging.RequestLogging;
import com.hs.yourfit.domain.dress.dto.request.PantsCreateRequest;
import com.hs.yourfit.domain.dress.dto.request.PantsUpdateRequest;
import com.hs.yourfit.domain.dress.dto.request.TopCreateRequest;
import com.hs.yourfit.domain.dress.dto.request.TopUpdateRequest;
import com.hs.yourfit.domain.dress.service.DressCreateService;
import com.hs.yourfit.domain.dress.service.DressDeleteService;
import com.hs.yourfit.domain.dress.service.DressGetService;
import com.hs.yourfit.domain.dress.service.DressUpdateService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/api/dress")
public class DressController {

    private final DressCreateService dressCreateService;
    private final DressGetService dressGetService;
    private final DressUpdateService dressUpdateService;
    private final DressDeleteService dressDeleteService;

    @RequestLogging
    @ApiOperation("하의 정보 생성")
    @PostMapping("/pants")
    @ResponseStatus(HttpStatus.CREATED)
    public StringResponse createPants(
            @RequestHeader("accessToken") String accessToken,
            @RequestBody @Valid PantsCreateRequest pantsCreateRequest){
        return new StringResponse(dressCreateService.createPants(pantsCreateRequest,accessToken),HttpStatus.CREATED);
    }

    @RequestLogging
    @ApiOperation("상의 정보 생성")
    @PostMapping("/top")
    @ResponseStatus(HttpStatus.CREATED)
    public StringResponse createTop(
            @RequestHeader("accessToken") String accessToken,
            @RequestBody @Valid TopCreateRequest topCreateRequest){
        return new StringResponse(dressCreateService.createTop(topCreateRequest, accessToken), HttpStatus.CREATED);
    }

    @RequestLogging
    @ApiOperation("개인의 상의 수선정보 가져오기")
    @GetMapping("/top")
    @ResponseStatus(HttpStatus.OK)
    public ResponseData getTop(@RequestHeader("accessToken") String accessToken){
        return dressGetService.getTop(accessToken,HttpStatus.OK);
    }

    @RequestLogging
    @ApiOperation("개인의 하의 수선정보 가져오기")
    @GetMapping("/pants")
    @ResponseStatus(HttpStatus.OK)
    public ResponseData getPants(@RequestHeader("accessToken") String accessToken){
        return dressGetService.getPants(accessToken,HttpStatus.OK);
    }

    @RequestLogging
    @ApiOperation("개인의 상의 수선정보 수정")
    @PutMapping("/top")
    @ResponseStatus(HttpStatus.OK)
    public StringResponse updateTop(
            @RequestHeader("accessToken") String accessToken,
            @RequestBody @Valid TopUpdateRequest topUpdateRequest){
        return new StringResponse(dressUpdateService.updateTop(topUpdateRequest,accessToken),HttpStatus.OK);
    }

    @RequestLogging
    @ApiOperation("개인의 하의 수선정보 수정")
    @PutMapping("/pants")
    @ResponseStatus(HttpStatus.OK)
    public StringResponse updatePants(
            @RequestHeader("accessToken") String accessToken,
            @RequestBody @Valid PantsUpdateRequest pantsUpdateRequest){
        return new StringResponse(dressUpdateService.updatePants(pantsUpdateRequest,accessToken),HttpStatus.OK);
    }

    @RequestLogging
    @ApiOperation("상의 수선정보 삭제")
    @DeleteMapping("/top/{topId}")
    @ResponseStatus(HttpStatus.OK)
    public StringResponse deleteTop(
            @RequestHeader("accessToken") String accessToken,
            @PathVariable Long topId){
        return new StringResponse(dressDeleteService.deleteTop(topId,accessToken),HttpStatus.OK);
    }

    @RequestLogging
    @ApiOperation("하의 수선정보 삭제")
    @DeleteMapping("/pants/{pantsId}")
    @ResponseStatus(HttpStatus.OK)
    public StringResponse deletePants(
            @RequestHeader("accessToken") String accessToken,
            @PathVariable Long pantsId){
        return new StringResponse(dressDeleteService.deletePants(pantsId,accessToken),HttpStatus.OK);
    }
}
