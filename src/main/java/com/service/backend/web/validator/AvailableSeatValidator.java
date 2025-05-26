package com.service.backend.web.validator;

import com.service.backend.web.annotation.AvailableSeat;
import com.service.backend.web.models.requests.CreateBookingRequest;
import com.service.backend.web.services.implementation.FlightService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AvailableSeatValidator implements ConstraintValidator<AvailableSeat, CreateBookingRequest> {

    private final FlightService flightService;

    @Override
    public void initialize(AvailableSeat constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(CreateBookingRequest createBookingRequest, ConstraintValidatorContext constraintValidatorContext) {
        int totalPassengers = createBookingRequest.getPassengerIds().size() + createBookingRequest.getPassengers().size();
        boolean isValid = flightService.checkAvailableSeat(createBookingRequest.getFlightId(), totalPassengers);

        if (!isValid) {
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate("Seats available are insufficient.")
                    .addPropertyNode("flightId")
                    .addConstraintViolation();
        }

        return isValid;
    }
}
