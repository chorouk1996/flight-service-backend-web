package com.service.backend.web.services.implementation;

import com.service.backend.web.models.dto.FlightDto;
import com.service.backend.web.models.dto.requests.SearchFlightRequest;
import com.service.backend.web.models.enumerators.SortDirectionEnum;
import com.service.backend.web.repositories.FlightCustomRepository;
import com.service.backend.web.repositories.FlightRepository;
import com.service.backend.web.services.interfaces.IFlightService;
import com.service.backend.web.services.mapper.FlightMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static com.service.backend.web.services.mapper.FlightMapper.mapFlightDtoToEntity;
import static com.service.backend.web.services.mapper.FlightMapper.mapFlightEntityToDto;

@Service
public class FlightService implements IFlightService {

    FlightRepository flightRepository;

    @Autowired
    FlightCustomRepository flightCustomRepository;

    @Override
    public FlightDto addFlight(FlightDto flight) {

        return mapFlightEntityToDto(flightRepository.save(mapFlightDtoToEntity(flight)));
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
        if (criteria.getSort() != null && criteria.getSort().getSortField().equals("duration")) {
            if (SortDirectionEnum.valueOf(criteria.getSort().getSortDirection().name()) == SortDirectionEnum.ASC)
                return flightCustomRepository.findByCriteria(criteria).stream().map(FlightMapper::mapFlightEntityToDto).sorted(Comparator.comparingLong(FlightDto::getDurationMinutes)).toList();
            return flightCustomRepository.findByCriteria(criteria).stream().map(FlightMapper::mapFlightEntityToDto).sorted(Comparator.comparingLong(FlightDto::getDurationMinutes).reversed()).toList();

        }
        return flightCustomRepository.findByCriteria(criteria).stream().map(FlightMapper::mapFlightEntityToDto).toList();
    }

    public FlightService(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }
}
