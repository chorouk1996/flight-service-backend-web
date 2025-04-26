package com.service.backend.web.services.interfaces;

import com.service.backend.web.models.dto.FlightDto;
import com.service.backend.web.models.dto.requests.SearchFlightRequest;

import java.util.List;

public interface IFlightService {


     FlightDto addFlight(FlightDto flight);

     List<FlightDto> getAllFlight();

     FlightDto  getFlight(Long id);

     List<FlightDto> searchFlight(SearchFlightRequest criteria);

     FlightDto getAvailableFlight(Long id);

}
