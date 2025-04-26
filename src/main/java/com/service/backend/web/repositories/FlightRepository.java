package com.service.backend.web.repositories;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.service.backend.web.models.entities.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface FlightRepository extends JpaRepository<Flight,Long> {
    Optional<Flight> getFlightById(Long id);

    List<Flight> findByOriginAndDestination(String origin, String destination);

    List<Flight> findByOriginAndDestinationAndDepartureTime(String origin, String destination, LocalDateTime departureTime);





}
