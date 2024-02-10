package com.example.big_event.validation;

import com.example.big_event.anno.State;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class StateValidation implements ConstraintValidator<State,String> {

    /**
     *
     * @param value 校验数据
     * @param constraintValidatorContext
     * @return
     */
    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if (value.isEmpty()) {
            return false;
        }
        return value.equals("已发布") || value.equals("草稿");
    }
}
