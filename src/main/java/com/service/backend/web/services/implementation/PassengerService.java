package com.service.backend.web.services.implementation;

import com.service.backend.web.models.dto.PassengerDto;
import com.service.backend.web.models.dto.requests.CreatePassengerRequest;
import com.service.backend.web.models.entities.Passenger;
import com.service.backend.web.repositories.PassengerRepository;
import com.service.backend.web.services.interfaces.IPassengerService;
import com.service.backend.web.services.mapper.PassengerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.service.backend.web.services.mapper.PassengerMapper.mapPassengerDtoToEntity;

@Service
public class PassengerService implements IPassengerService {

    PassengerRepository passengerRepository;

    @Override
    public void addPassenger(PassengerDto passenger) {
        passengerRepository.save(mapPassengerDtoToEntity(passenger));

    }

    @Override
    public List<PassengerDto> getAllPassenger() {
        return passengerRepository.findAll().stream().map(PassengerMapper::mapPassengerEntityToDto).toList();
    }


    @Override
    public List<Passenger> addAllPassenger(List<CreatePassengerRequest> passengers) {
        return passengerRepository.saveAll(passengers.stream().map(PassengerMapper::mapPassengerDtoToEntity).toList());
    }

    public PassengerService(@Autowired PassengerRepository passengerRepository) {
        this.passengerRepository = passengerRepository;
    }
}
