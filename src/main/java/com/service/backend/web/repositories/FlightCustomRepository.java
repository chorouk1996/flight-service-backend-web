package com.service.backend.web.repositories;

import com.service.backend.web.models.dto.requests.SearchFlightRequest;
import com.service.backend.web.models.entities.Flight;
import com.service.backend.web.models.enumerators.SortDirectionEnum;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.*;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class FlightCustomRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Flight> findByCriteria(SearchFlightRequest criteria) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Flight> query = cb.createQuery(Flight.class);
        Root<Flight> flight = query.from(Flight.class);

        List<Predicate> predicates = new ArrayList<>();

        predicates.add(cb.equal(flight.get("origin"), criteria.getDeparture_city()));
        predicates.add(cb.equal(flight.get("destination"), criteria.getDestination_city()));
        if (criteria.getDate() != null) {
            LocalDateTime startOfDay = criteria.getDate().atStartOfDay();
            LocalDateTime endOfDay = criteria.getDate().atTime(23, 59, 59);
            predicates.add(cb.between(flight.get("departureTime"), startOfDay, endOfDay));
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

        if (criteria.getSort() != null && criteria.getSort().getSortField() != null  && !criteria.getSort().getSortField().equals("duration")) {
            Path<Object> sortPath = flight.get(criteria.getSort().getSortField());
            if (criteria.getSort().getSortDirection() == SortDirectionEnum.ASC) {
                query.orderBy(cb.asc(sortPath));
            } else {
                query.orderBy(cb.desc(sortPath));
            }
        }
        return entityManager.createQuery(query).getResultList();
    }


}
