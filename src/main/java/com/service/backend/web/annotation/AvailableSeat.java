package com.service.backend.web.annotation;


import com.service.backend.web.validator.ActiveFlightValidator;
import com.service.backend.web.validator.AvailableSeatValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = {AvailableSeatValidator.class})
public @interface AvailableSeat {
    String message() default "Seats available are insufficient.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
