package com.service.backend.web.services.inetrface;

import com.service.backend.web.models.dto.BookingDto;
import com.service.backend.web.models.dto.PassengerDto;
import com.service.backend.web.models.entities.Passenger;

import java.util.List;

public interface IPassengerService {

    public void addPassenger(PassengerDto passenger);

    public List<PassengerDto> getAllPassenger();
}
