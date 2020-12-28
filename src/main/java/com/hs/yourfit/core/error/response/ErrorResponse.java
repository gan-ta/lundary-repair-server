package com.hs.yourfit.core.error.response;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.hs.yourfit.common.model.ResponseData;
import com.hs.yourfit.core.error.enums.ErrorCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

@Getter
@NoArgsConstructor
public class ErrorResponse extends ResponseData {
    private String message;
    private List<ErrorResponseDetail> errors;
    private String code;

    private ErrorResponse(final ErrorCode code, final List<ErrorResponseDetail> errors) {
        super(false,code.getStatus());
        this.message = code.getMessage();
        this.errors = errors;
        this.code = code.getCode();
    }

    private ErrorResponse(final ErrorCode code) {
        super(false,code.getStatus());
        this.message = code.getMessage();
        this.code = code.getCode();
        this.errors = new ArrayList<>();
    }

    public ErrorResponse(final String message){
        super(false, HttpStatus.INTERNAL_SERVER_ERROR);
        this.message = message;
        this.code = "";
        this.errors = new ArrayList<>();
    }


    public static ErrorResponse of(final ErrorCode code) {
        return new ErrorResponse(code);
    }

    public static ErrorResponse of(final String message){
        return new ErrorResponse(message);
    }

    public static ErrorResponse of(final ErrorCode code, final BindingResult bindingResult) {
        return new ErrorResponse(code, ErrorResponseDetail.of(bindingResult));
    }

//    public static ErrorResponse of(MethodArgumentTypeMismatchException e) {
//        final String value = e.getValue() == null ? "" : e.getValue().toString();
//        final List<ErrorResponseDetail> errors = ErrorResponseDetail.of(e.getName(), value, e.getErrorCode());
//        return new ErrorResponse(ErrorCode.INVALID_INPUT_VALUE, errors);
//    }

    @Getter
    @NoArgsConstructor
    public static class ErrorResponseDetail {
        private String field;
        private String value;
        private String reason;

        private ErrorResponseDetail(final String field, final String value, final String reason) {
            this.field = field;
            this.value = value;
            this.reason = reason;
        }

//        public static List<ErrorResponseDetail> of(final String field, final String value, final String reason) {
//            List<ErrorResponseDetail> ErrorResponseDetailList = new ArrayList<>();
//            ErrorResponseDetailList.add(new ErrorResponseDetail(field, value, reason));
//            return ErrorResponseDetailList;
//        }

        private static List<ErrorResponseDetail> of(BindingResult bindingResult) {
            final List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            return fieldErrors.stream()
                    .map(error -> new ErrorResponseDetail(
                            error.getField(),
                            error.getRejectedValue() == null ? "" : error.getRejectedValue().toString(),
                            error.getDefaultMessage()))
                    .collect(Collectors.toList());
        }
    }

}
