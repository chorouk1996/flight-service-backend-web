package com.service.backend.web.services.interfaces;

import com.service.backend.web.models.dto.PassengerDto;

import java.util.List;

public interface IPassengerService {

    public void addPassenger(PassengerDto passenger);

    public List<PassengerDto> getAllPassenger();
}
