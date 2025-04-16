package com.service.backend.web.services.interfaces;

import com.service.backend.web.models.dto.FlightDto;

import java.util.List;

public interface IFlightService {


     FlightDto addFlight(FlightDto flight);

     List<FlightDto> getAllFlight();

}
