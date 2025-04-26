package com.service.backend.web.services.interfaces;

import com.service.backend.web.models.dto.PassengerDto;
import com.service.backend.web.models.dto.requests.CreatePassengerRequest;
import com.service.backend.web.models.dto.requests.UpdatePassengerRequest;
import com.service.backend.web.models.entities.Passenger;

import java.util.List;

public interface IPassengerService {

    PassengerDto addPassenger(CreatePassengerRequest passenger,String username);

     List<PassengerDto> getAllPassenger(String username);

    PassengerDto getPassengerById(String username,Long id);

    PassengerDto updatePassengerByUser(String username, UpdatePassengerRequest passenger ,Long id);

    void deletePassengerByUser(String username,Long id);

    List<Passenger> addAllPassenger(List<CreatePassengerRequest> passenger);
}
