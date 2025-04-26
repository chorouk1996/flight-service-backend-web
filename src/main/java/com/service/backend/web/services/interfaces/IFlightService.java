package com.service.backend.web.services.interfaces;

import com.service.backend.web.models.dto.FlightDto;
import com.service.backend.web.models.dto.requests.CreateFlightRequest;
import com.service.backend.web.models.dto.requests.SearchFlightRequest;
import com.service.backend.web.models.dto.requests.UpdateFlightRequest;
import com.service.backend.web.models.dto.responses.CreateFlightResponse;

import java.util.List;

public interface IFlightService {


     CreateFlightResponse addFlight(CreateFlightRequest flight);

     CreateFlightResponse updateFlight(UpdateFlightRequest flight);

     List<FlightDto> getAllFlight();

     FlightDto  getFlight(Long id);

     List<FlightDto> searchFlight(SearchFlightRequest criteria);

     FlightDto getAvailableFlight(Long id);

     void cancelFlight(Long flightId);
}
