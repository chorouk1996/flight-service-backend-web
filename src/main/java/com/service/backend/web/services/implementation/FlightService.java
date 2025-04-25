package com.service.backend.web.services.implementation;

import com.service.backend.web.models.dto.FlightDto;
import com.service.backend.web.models.dto.requests.SearchFlightRequest;
import com.service.backend.web.repositories.FlightRepository;
import com.service.backend.web.services.interfaces.IFlightService;
import com.service.backend.web.services.mapper.FlightMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.service.backend.web.services.mapper.FlightMapper.mapFlightDtoToEntity;
import static com.service.backend.web.services.mapper.FlightMapper.mapFlightEntityToDto;

@Service
public class FlightService implements IFlightService {

    FlightRepository flightRepository;

    @Override
    public FlightDto  addFlight(FlightDto flight) {

       return  mapFlightEntityToDto(flightRepository.save(mapFlightDtoToEntity(flight)));
    }

    @Override
    public List<FlightDto> getAllFlight() {
        return Collections.emptyList();
    }

    @Override
    public FlightDto getFlight(Long id) {
         flightRepository.getFlightById(id);
        return null;
    }

    @Override
    public List<FlightDto> searchFlight(SearchFlightRequest criteria) {
        if(null != criteria.getDate() )
            return flightRepository.findByOriginAndDestinationAndDepartureTime(criteria.getDeparture_city(),criteria.getDestination_city(),criteria.getDate()).stream().map(FlightMapper::mapFlightEntityToDto).toList();

        return flightRepository.findByOriginAndDestination(criteria.getDeparture_city(),criteria.getDestination_city()).stream().map(FlightMapper::mapFlightEntityToDto).toList();
    }

    public FlightService(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }
}
