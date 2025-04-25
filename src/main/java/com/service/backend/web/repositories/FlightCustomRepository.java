package com.service.backend.web.repositories;

import com.service.backend.web.models.dto.requests.SearchFlightRequest;
import com.service.backend.web.models.entities.Flight;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class FlightCustomRepository  {

    @PersistenceContext
    private EntityManager entityManager;

   public  List<Flight> findByCriteria(SearchFlightRequest criteria){

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Flight> query = cb.createQuery(Flight.class);
        Root<Flight> flight = query.from(Flight.class);

        List<Predicate> predicates = new ArrayList<>();

        predicates.add(cb.equal(flight.get("origin"), criteria.getDeparture_city()));
        predicates.add(cb.equal(flight.get("destination"), criteria.getDestination_city()));
        if (criteria.getDate() != null) {
            predicates.add(cb.equal(cb.function("DATE", LocalDate.class, flight.get("departureTime")), criteria.getDate()));
        }
        if (criteria.getAirline() != null) {
            predicates.add(cb.equal(flight.get("airlineName"), criteria.getAirline()));
        }
        if (criteria.getAircraft() != null) {
            predicates.add(cb.equal(flight.get("aircraftType"), criteria.getAircraft()));
        }
        if (criteria.getStatus() != null) {
            predicates.add(cb.equal(flight.get("flightStatus"), criteria.getStatus()));
        }

        query.where(predicates.toArray(new Predicate[0]));

        return entityManager.createQuery(query).getResultList();
    }




}
