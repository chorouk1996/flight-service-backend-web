package com.service.backend.web.services.implementation;

import com.service.backend.web.models.dto.FlightDto;
import com.service.backend.web.repositories.FlightRepository;
import com.service.backend.web.services.inetrface.IFlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.service.backend.web.services.helper.FlightServiceHelper.mapFlightDtoToEntity;
import static com.service.backend.web.services.helper.FlightServiceHelper.mapFlightEntityToDto;

@Service
public class FlightService implements IFlightService {

    @Autowired
    FlightRepository flightRepository;

    @Override
    public FlightDto  addFlight(FlightDto flight) {

       return  mapFlightEntityToDto(flightRepository.save(mapFlightDtoToEntity(flight)));
    }

    @Override
    public List<FlightDto> getAllFlight() {
        return null;
    }


}
