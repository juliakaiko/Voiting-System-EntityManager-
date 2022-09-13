package com.step.springmvcapp.entity.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Documented //
@Target({ElementType.FIELD,ElementType.PARAMETER,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValidPassportNumberValidator.class)
public @interface ValidPassportNumber {
    String message() default "Passport number is not valid";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
