package com.example.big_event.anno;

import com.example.big_event.validation.StateValidation;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = { StateValidation.class})
public @interface State {

    String message() default "state is only '草稿' or '已发布' ";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {  };
}
