package com.service.backend.web.services.implementation;

import com.service.backend.web.models.dto.PassengerDto;
import com.service.backend.web.repositories.PassengerRepository;
import com.service.backend.web.services.inetrface.IPassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.service.backend.web.services.helper.PassengerServiceHelper.mapPassengerDtoToEntity;
import static com.service.backend.web.services.helper.PassengerServiceHelper.mapPassengerEntityToDto;

@Service
public class PassengerService implements IPassengerService {

    @Autowired
    PassengerRepository passengerRepository;

    @Override
    public void addPassenger(PassengerDto passenger) {
        passengerRepository.save(mapPassengerDtoToEntity(passenger));

    }

    @Override
    public List<PassengerDto> getAllPassenger() {
        return passengerRepository.findAll().stream().map(pass->mapPassengerEntityToDto(pass)).toList();
    }
}
