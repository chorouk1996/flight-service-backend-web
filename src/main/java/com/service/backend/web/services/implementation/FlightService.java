package com.service.backend.web.services.implementation;

import com.service.backend.web.events.DelayFlightEvent;
import com.service.backend.web.exceptions.FunctionalException;
import com.service.backend.web.exceptions.FunctionalExceptionDto;
import com.service.backend.web.models.dto.FlightDto;
import com.service.backend.web.models.requests.CreateFlightRequest;
import com.service.backend.web.models.requests.SearchFlightRequest;
import com.service.backend.web.models.requests.UpdateFlightRequest;
import com.service.backend.web.models.requests.UpdateFlightStatusRequest;
import com.service.backend.web.models.responses.CreateFlightResponse;
import com.service.backend.web.models.entities.Flight;
import com.service.backend.web.models.enumerators.FlightStatusEnum;
import com.service.backend.web.models.enumerators.SortDirectionEnum;
import com.service.backend.web.models.responses.FlightByAirlineResponse;
import com.service.backend.web.repositories.FlightCustomRepository;
import com.service.backend.web.repositories.FlightRepository;
import com.service.backend.web.services.helper.FlightHelper;
import com.service.backend.web.services.interfaces.IFlightService;
import com.service.backend.web.services.mapper.FlightMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

import static com.service.backend.web.services.mapper.FlightMapper.mapCreateFlightRequestToEntity;
import static com.service.backend.web.services.mapper.FlightMapper.mapFlightEntityToCreateFlightResponse;

@Service
public class FlightService implements IFlightService {

    @Autowired
    FlightRepository flightRepository;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Autowired
    FlightCustomRepository flightCustomRepository;

    @Override
    public CreateFlightResponse addFlight(CreateFlightRequest flight) {

        return mapFlightEntityToCreateFlightResponse(flightRepository.save(mapCreateFlightRequestToEntity(flight)));
    }

    @Override
    public CreateFlightResponse updateFlight(Long id, UpdateFlightRequest flight) {
        checkIfReasonExist(flight.getStatus(), flight.getDelayReason());
        Flight oldFlight = getFlightById(id);
        FlightHelper.updateIfNotNull(oldFlight::setAircraftType, flight::getAircraftType);
        FlightHelper.updateIfNotNull(oldFlight::setFlightStatus, flight::getStatus);
        FlightHelper.updateIfNotNull(oldFlight::setFlightNumber, flight::getFlightNumber);
        FlightHelper.updateIfNotNull(oldFlight::setArrivalTime, flight::getArrivalTime);
        FlightHelper.updateIfNotNull(oldFlight::setBaggagePolicy, flight::getBaggagePolicy);
        FlightHelper.updateIfNotNull(oldFlight::setDepartureTime, flight::getDepartureTime);
        FlightHelper.updateIfNotNull(oldFlight::setSeats, flight::getSeats);
        FlightHelper.updateIfNotNull(oldFlight::setOrigin, flight::getOrigin);
        FlightHelper.updateIfNotNull(oldFlight::setDestination, flight::getDestination);
        FlightHelper.updateIfNotNull(oldFlight::setPrice, flight::getPrice);
        FlightHelper.updateIfNotNull(oldFlight::setAirlineName, flight::getAirlineName);
        if (flight.getStatus() == FlightStatusEnum.CANCELLED || flight.getStatus() == FlightStatusEnum.DELAYED || (flight.getDepartureTime() != null && flight.getDepartureTime().isAfter(oldFlight.getDepartureTime()))) {
            if (!StringUtils.hasText(flight.getDelayReason()))
                throw new FunctionalException(new FunctionalExceptionDto("Delay reason must be provided when status is DELAYED or CANCELLED", HttpStatus.BAD_REQUEST));
            oldFlight.setDelayReason(flight.getDelayReason());
            oldFlight.setFlightStatus(flight.getStatus() != null ? flight.getStatus() : FlightStatusEnum.DELAYED);
            if (oldFlight.getFlightStatus() == FlightStatusEnum.DELAYED)
                eventPublisher.publishEvent(new DelayFlightEvent(oldFlight.getId(), flight.getDelayReason()));
        }
        return mapFlightEntityToCreateFlightResponse(flightRepository.save(oldFlight));

    }

    @Override
    public CreateFlightResponse updateFlightStatus(Long id, UpdateFlightStatusRequest request) {
        Flight oldFlight = getFlightById(id);
        checkIfReasonExist(request.getStatus(), request.getDelayReason());
        if (request.getStatus() == FlightStatusEnum.DELAYED)
            eventPublisher.publishEvent(new DelayFlightEvent(oldFlight.getId(), request.getDelayReason()));
        FlightHelper.updateIfNotNull(oldFlight::setFlightStatus, request::getStatus);
        return mapFlightEntityToCreateFlightResponse(flightRepository.save(oldFlight));

    }

    private void checkIfReasonExist(FlightStatusEnum status, String delayReason) {
        if ((status == FlightStatusEnum.CANCELLED || status == FlightStatusEnum.DELAYED) && !StringUtils.hasText(delayReason)) {
            throw new FunctionalException(new FunctionalExceptionDto("Delay reason must be provided when status is DELAYED or CANCELLED", HttpStatus.BAD_REQUEST));
        }
    }


    @Override
    public List<FlightDto> getAllFlight() {
        return Collections.emptyList();
    }

    @Override
    public Flight getFlightById(Long id) {
        return flightRepository.getFlightById(id).orElseThrow(
                () -> {
                    throw new FunctionalException(new FunctionalExceptionDto("Flight not found", HttpStatus.NOT_FOUND));
                }

        );
    }

    public Flight getAvailableFlightById(Long id) {
        return flightRepository.getFlightByIdAndFlightStatusNotAndDepartureTimeAfter(id, FlightStatusEnum.CANCELLED, LocalDateTime.now()).orElseThrow(
                () -> {
                    throw new FunctionalException(new FunctionalExceptionDto("Flight not found , departed or cancelled", HttpStatus.NOT_FOUND));
                }

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

    @Override
    public long countAll() {
        return flightRepository.count();
    }

    public void decreaseSeat(Long flightId, int seat) {
        Flight flight = getFlightById(flightId);
        if (flight.getFlightStatus().equals(FlightStatusEnum.CANCELLED))
            throw new FunctionalException(new FunctionalExceptionDto("Flight was Cancelled", HttpStatus.NOT_FOUND));
        if (flight.getSeats() < seat)
            throw new FunctionalException(new FunctionalExceptionDto("Seats available are insufficient", HttpStatus.CONFLICT));

        flight.setSeats(flight.getSeats() - seat);
        flightRepository.save(flight);
    }

    public void checkAvailableSeat(Long flightId, int seat) {
        Flight flight = getFlightById(flightId);
        if (flight.getFlightStatus().equals(FlightStatusEnum.CANCELLED))
            throw new FunctionalException(new FunctionalExceptionDto("Flight was Cancelled", HttpStatus.NOT_FOUND));
        if (flight.getSeats() < seat)
            throw new FunctionalException(new FunctionalExceptionDto("Seats available are insufficient", HttpStatus.CONFLICT));
    }

    public void increaseSeat(Long flightId, int seat) {
        Flight flight = getFlightById(flightId);
        flight.setSeats(flight.getSeats() + seat);
        flightRepository.save(flight);
    }

    @Override
    public List<FlightDto> userSearchFlight(SearchFlightRequest criteria) {
        if (criteria.getStatus() == FlightStatusEnum.CANCELLED || criteria.getStatus() == FlightStatusEnum.DEPARTED)
            throw new FunctionalException(new FunctionalExceptionDto("Users are not allowed to access cancelled flight", HttpStatus.FORBIDDEN));
        return searchFlight(criteria);
    }

    private List<FlightDto> searchFlight(SearchFlightRequest criteria) {
        Stream<FlightDto> flights = flightCustomRepository.findByCriteria(criteria).stream().map(FlightMapper::mapFlightEntityToDto);
        if (criteria.getMaxDurationMinutes() != null && criteria.getMinDurationMinutes() == null) {
            flights = flights.filter(flight -> flight.getDurationMinutes() <= criteria.getMaxDurationMinutes());
        }
        if (criteria.getMinDurationMinutes() != null && criteria.getMaxDurationMinutes() == null) {
            flights = flights.filter(flight -> flight.getDurationMinutes() >= criteria.getMinDurationMinutes());
        }
        if (criteria.getMinDurationMinutes() != null && criteria.getMaxDurationMinutes() != null) {
            flights = flights.filter(flight -> flight.getDurationMinutes() >= criteria.getMinDurationMinutes() && flight.getDurationMinutes() <= criteria.getMaxDurationMinutes());

        }
        if (criteria.getSort() != null && criteria.getSort().getSortField().equals("duration")) {
            if (criteria.getSort().getSortDirection() == SortDirectionEnum.ASC)
                flights = flights.sorted(Comparator.comparingLong(FlightDto::getDurationMinutes));
            flights = flights.sorted(Comparator.comparingLong(FlightDto::getDurationMinutes).reversed());
        }


        return flights.toList();
    }

    @Override
    public List<FlightDto> adminSearchFlight(SearchFlightRequest criteria) {
        return searchFlight(criteria);
    }


    @Override
    public List<FlightByAirlineResponse> getFlightsByAirline() {
        return flightRepository.countFlightByAirlineName().stream().map(FlightByAirlineResponse::new).toList();
    }


}
