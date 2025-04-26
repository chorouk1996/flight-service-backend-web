package com.service.backend.web.services.interfaces;

import com.service.backend.web.models.dto.PassengerDto;
import com.service.backend.web.models.dto.requests.CreateSavedPassengerRequest;
import com.service.backend.web.models.dto.requests.UpdateSavedPassengerRequest;
import com.service.backend.web.models.entities.Passenger;

import java.util.List;

public interface IPassengerService {

    PassengerDto addPassenger(CreateSavedPassengerRequest passenger, String username);

     List<PassengerDto> getAllPassenger(String username);

    PassengerDto getPassengerById(String username,Long id);

    PassengerDto updatePassengerByUser(String username, UpdateSavedPassengerRequest passenger , Long id);

    void deletePassengerByUser(String username,Long id);

    List<Passenger> addAllPassenger(List<CreateSavedPassengerRequest> passenger);
}
