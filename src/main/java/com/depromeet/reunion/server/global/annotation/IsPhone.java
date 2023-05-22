package com.depromeet.reunion.server.global.annotation;

import com.depromeet.reunion.server.global.validator.PhoneNumberValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target(FIELD)
@Retention(RUNTIME)
@Constraint(validatedBy = PhoneNumberValidator.class)
public @interface IsPhone {
    String message() default "need to be valid phone number, valid format 01012345678";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
