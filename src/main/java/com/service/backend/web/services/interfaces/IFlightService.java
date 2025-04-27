package com.service.backend.web.services.interfaces;

import com.service.backend.web.models.dto.FlightDto;
import com.service.backend.web.models.requests.CreateFlightRequest;
import com.service.backend.web.models.requests.SearchFlightRequest;
import com.service.backend.web.models.requests.UpdateFlightRequest;
import com.service.backend.web.models.requests.UpdateFlightStatusRequest;
import com.service.backend.web.models.responses.CreateFlightResponse;
import com.service.backend.web.models.entities.Flight;

import java.util.List;

public interface IFlightService {


     CreateFlightResponse addFlight(CreateFlightRequest flight);

     CreateFlightResponse updateFlight(Long id,UpdateFlightRequest flight);
     CreateFlightResponse updateFlightStatus(Long id, UpdateFlightStatusRequest request);

     List<FlightDto> getAllFlight();

     FlightDto  getFlight(Long id);

     Flight getFlightById(Long id);
     List<FlightDto> adminSearchFlight(SearchFlightRequest criteria);

     List<FlightDto> userSearchFlight(SearchFlightRequest criteria);

     FlightDto getAvailableFlight(Long id);

     void cancelFlight(Long flightId);

    long countAll();
}
