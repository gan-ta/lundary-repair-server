package com.hs.lunpair.common.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = {EnumValidator.class})//구현체의 지정
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})//메서드,필드,파라미터에 적용 가능
@Retention(RetentionPolicy.RUNTIME)//어노테이션을 Runtime까지 유지
public @interface Enum {
    String message() default "Invalid value. This is not permitted.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    Class<? extends java.lang.Enum<?>> enumClass();
    boolean ignoreCase() default false;
}