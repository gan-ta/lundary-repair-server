package com.hs.lunpair.core.error.handler;

import com.hs.lunpair.core.error.enums.ErrorCode;
import com.hs.lunpair.core.error.exception.UserDefineException;
import com.hs.lunpair.core.error.response.ErrorResponse;
import com.hs.lunpair.domain.user.exception.BusinessInfoNotFoundException;
import com.hs.lunpair.domain.user.exception.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 일반적인 예외를 처리
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleUnknownException(Exception e) {
        log.error(e.getMessage());

        final ErrorResponse response = ErrorResponse.of(e.getMessage());
        return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
    }

    /**
     * UserDefineException 예외를 커스터마이징하게 처리
     */
    @ExceptionHandler(UserDefineException.class)
    public ResponseEntity handleUserDefineException(UserDefineException e) {
        log.error(e.getMessage());

        final ErrorResponse errorResponse = ErrorResponse.of(e.getMessage());
        return new ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST);
    }


    /**
     * DTO 검증시 발생하는 예외 처리
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
        List<ObjectError> objectErrorList = e.getBindingResult().getAllErrors();
        for(ObjectError objectError : objectErrorList) {
            log.error(objectError.getDefaultMessage());
        }

        final ErrorResponse response = ErrorResponse.of(ErrorCode.INVALID_INPUT_VALUE, e.getBindingResult());
        return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
    }
}
