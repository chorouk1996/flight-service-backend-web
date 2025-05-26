package com.service.backend.web.annotation;


import com.service.backend.web.validator.ActiveFlightValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = {ActiveFlightValidator.class})
public @interface ActiveFlight {
    String message() default "The flight is not active.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
