package com.service.backend.web.services.inetrface;

import com.service.backend.web.models.dto.FlightDto;

import java.util.List;

public interface IFlightService {


    public FlightDto addFlight(FlightDto flight);

    public List<FlightDto> getAllFlight();

}
