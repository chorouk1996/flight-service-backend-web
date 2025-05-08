package com.service.backend.web.repositories;

import com.service.backend.web.models.entities.Flight;
import com.service.backend.web.models.enumerators.FlightStatusEnum;
import com.service.backend.web.models.responses.IFlightByAirlineResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface FlightRepository extends JpaRepository<Flight,Long> {

    @Query("    SELECT f.airlineName AS airline, COUNT(f) AS flightCount\n" +
            "    FROM Flight f\n" +
            "    GROUP BY f.airlineName")
    List<IFlightByAirlineResponse> countFlightByAirlineName();
    Optional<Flight> getFlightById(Long id);


    Optional<Flight> getFlightByIdAndFlightStatusNotAndDepartureTimeAfter(Long id, FlightStatusEnum status,LocalDateTime date);

    List<Flight> findByOriginAndDestination(String origin, String destination);

    List<Flight> findByOriginAndDestinationAndDepartureTime(String origin, String destination, LocalDateTime departureTime);





}
