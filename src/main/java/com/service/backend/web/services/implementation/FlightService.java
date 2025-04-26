package com.service.backend.web.services.implementation;

import com.service.backend.web.exceptions.FunctionalException;
import com.service.backend.web.exceptions.FunctionalExceptionDto;
import com.service.backend.web.models.dto.FlightDto;
import com.service.backend.web.models.dto.requests.CreateFlightRequest;
import com.service.backend.web.models.dto.requests.SearchFlightRequest;
import com.service.backend.web.models.dto.requests.UpdateFlightRequest;
import com.service.backend.web.models.dto.responses.CreateFlightResponse;
import com.service.backend.web.models.entities.Flight;
import com.service.backend.web.models.enumerators.FlightStatusEnum;
import com.service.backend.web.models.enumerators.SortDirectionEnum;
import com.service.backend.web.repositories.FlightCustomRepository;
import com.service.backend.web.repositories.FlightRepository;
import com.service.backend.web.services.helper.FlightHelper;
import com.service.backend.web.services.interfaces.IFlightService;
import com.service.backend.web.services.mapper.FlightMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import static com.service.backend.web.services.mapper.FlightMapper.*;

@Service
public class FlightService implements IFlightService {

    FlightRepository flightRepository;

    @Autowired
    FlightCustomRepository flightCustomRepository;

    @Override
    public CreateFlightResponse addFlight(CreateFlightRequest flight) {

        return mapFlightEntityToCreateFlightResponse(flightRepository.save(mapCreateFlightRequestToEntity(flight)));
    }
    @Override
    public CreateFlightResponse updateFlight(UpdateFlightRequest flight) {
        Flight oldFlight =  getFlightById(flight.getFlightId());

        FlightHelper.updateIfNotNull(oldFlight::setAircraftType,flight::getAircraftType);
        FlightHelper.updateIfNotNull(oldFlight::setFlightStatus,flight::getStatus);
        FlightHelper.updateIfNotNull(oldFlight::setFlightNumber,flight::getFlightNumber);
        FlightHelper.updateIfNotNull(oldFlight::setArrivalTime,flight::getArrivalTime);
        FlightHelper.updateIfNotNull(oldFlight::setBaggagePolicy,flight::getBaggagePolicy);
        FlightHelper.updateIfNotNull(oldFlight::setDepartureTime,flight::getDepartureTime);
        FlightHelper.updateIfNotNull(oldFlight::setSeats,flight::getSeats);
        FlightHelper.updateIfNotNull(oldFlight::setOrigin,flight::getOrigin);
        FlightHelper.updateIfNotNull(oldFlight::setDestination,flight::getDestination);
        FlightHelper.updateIfNotNull(oldFlight::setPrice,flight::getPrice);
        FlightHelper.updateIfNotNull(oldFlight::setAirlineName,flight::getAirlineName);

       return  mapFlightEntityToCreateFlightResponse(flightRepository.save(oldFlight));

    }




    @Override
    public List<FlightDto> getAllFlight() {
        return Collections.emptyList();
    }

    public Flight getFlightById(Long id) {
        return flightRepository.getFlightById(id).orElseThrow(
                ()-> {throw new FunctionalException(new FunctionalExceptionDto("Flight not found",HttpStatus.NOT_FOUND));}

        );
    }

    public Flight getAvailableFlightById(Long id) {
        return flightRepository.getFlightByIdAndFlightStatusNotAndDepartureTimeAfter(id,FlightStatusEnum.CANCELLED, LocalDateTime.now()).orElseThrow(
                ()-> {throw new FunctionalException(new FunctionalExceptionDto("Flight not found , departed or cancelled",HttpStatus.NOT_FOUND));}

        );
    }

    @Override
    public FlightDto getFlight(Long id) {
        return FlightMapper.mapFlightEntityToDto(getFlightById(id));
    }

    @Override
    public FlightDto getAvailableFlight(Long id) {
        return FlightMapper.mapFlightEntityToDto(getFlightById(id));
    }

    @Override
    public void cancelFlight(Long flightId) {
        Flight flight = getFlightById(flightId);
        flight.setFlightStatus(FlightStatusEnum.CANCELLED);
        flightRepository.save(flight);
    }

    public void decreaseSeat(Long flightId, int seat) {
        Flight flight = getFlightById(flightId);
        if(flight.getFlightStatus().equals(FlightStatusEnum.CANCELLED)) throw new FunctionalException(new FunctionalExceptionDto("Flight was Cancelled", HttpStatus.NOT_FOUND));
        if(flight.getSeats() < seat) throw new FunctionalException(new FunctionalExceptionDto("Seats available are insufficient", HttpStatus.CONFLICT));

        flight.setSeats(flight.getSeats() - seat);
        flightRepository.save(flight);
    }

    public void increaseSeat(Long flightId, int seat) {
        Flight flight = getFlightById(flightId);;
        flight.setSeats(flight.getSeats() + seat);
        flightRepository.save(flight);
    }
    @Override
    public List<FlightDto> searchFlight(SearchFlightRequest criteria) {
        if (criteria.getSort() != null && criteria.getSort().getSortField().equals("duration")) {
            if (criteria.getSort().getSortDirection() == SortDirectionEnum.ASC)
                return flightCustomRepository.findByCriteria(criteria).stream().map(FlightMapper::mapFlightEntityToDto).sorted(Comparator.comparingLong(FlightDto::getDurationMinutes)).toList();
            return flightCustomRepository.findByCriteria(criteria).stream().map(FlightMapper::mapFlightEntityToDto).sorted(Comparator.comparingLong(FlightDto::getDurationMinutes).reversed()).toList();

        }
        return flightCustomRepository.findByCriteria(criteria).stream().map(FlightMapper::mapFlightEntityToDto).toList();
    }

    public FlightService(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }
}
