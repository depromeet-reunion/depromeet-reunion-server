package com.depromeet.reunion.server.global.validator;

import com.depromeet.reunion.server.global.annotation.IsPhone;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PhoneNumberValidator implements ConstraintValidator<IsPhone, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }
        return value.matches("^010\\d{8}$");
    }
}
