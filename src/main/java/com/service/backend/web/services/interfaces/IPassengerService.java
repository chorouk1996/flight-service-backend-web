package com.service.backend.web.services.interfaces;

import com.service.backend.web.models.dto.PassengerDto;
import com.service.backend.web.models.dto.requests.CreatePassengerRequest;
import com.service.backend.web.models.entities.Passenger;

import java.util.List;

public interface IPassengerService {

     void addPassenger(PassengerDto passenger);

     List<PassengerDto> getAllPassenger();


    List<Passenger> addAllPassenger(List<CreatePassengerRequest> passenger);
}
