package com.service.backend.web.validator;

import com.service.backend.web.annotation.ActiveFlight;
import com.service.backend.web.exceptions.FunctionalException;
import com.service.backend.web.exceptions.FunctionalExceptionDto;
import com.service.backend.web.models.dto.FlightDto;
import com.service.backend.web.models.entities.Flight;
import com.service.backend.web.models.enumerators.FlightStatusEnum;
import com.service.backend.web.services.implementation.FlightService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
public class ActiveFlightValidator implements ConstraintValidator<ActiveFlight,Long> {

    private final FlightService flightService;
    @Override
    public void initialize(ActiveFlight constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    @Transactional
    public boolean isValid(Long aLong, ConstraintValidatorContext constraintValidatorContext) {
        FlightDto flight = flightService.getAvailableFlight(aLong);
        if (FlightStatusEnum.CANCELLED.equals(flight.getFlightStatus()) || FlightStatusEnum.DEPARTED.equals(flight.getFlightStatus()))
            return false;
        return true;
    }
}
