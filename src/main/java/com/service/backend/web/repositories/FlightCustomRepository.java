package com.service.backend.web.repositories;

import com.service.backend.web.models.entities.Flight;
import com.service.backend.web.models.enumerators.SortDirectionEnum;
import com.service.backend.web.models.requests.SearchFlightRequest;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.*;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class FlightCustomRepository {

    private static final String PRICE = "price";
    @PersistenceContext
    private EntityManager entityManager;

    public List<Flight> findByCriteria(SearchFlightRequest criteria) {

        CriteriaQuery<Flight> query = buildCriteria(criteria, false);
        List<Flight> flights = entityManager.createQuery(query).setMaxResults(100).getResultList();
        if (flights.isEmpty() && Boolean.TRUE.equals(criteria.getFlexibleDates())) {
            query = buildCriteria(criteria, true);
            flights = entityManager.createQuery(query).setMaxResults(100).getResultList();

        }
        return flights;
    }

    private CriteriaQuery<Flight> buildCriteria(SearchFlightRequest criteria, boolean flexibleDates) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Flight> query = cb.createQuery(Flight.class);
        Root<Flight> flight = query.from(Flight.class);

        query.where(buildPredicates(cb, flight, criteria, flexibleDates).toArray(new Predicate[0]));

        if (criteria.getSort() != null && criteria.getSort().getSortField() != null && !criteria.getSort().getSortField().equals("duration")) {
            Path<Object> sortPath = flight.get(criteria.getSort().getSortField());
            if (criteria.getSort().getSortDirection() == SortDirectionEnum.ASC) {
                query.orderBy(cb.asc(sortPath));
            } else {
                query.orderBy(cb.desc(sortPath));
            }
        }
        return query;
    }

    private List<Predicate> buildPredicates(CriteriaBuilder cb, Root<Flight> flight, SearchFlightRequest criteria, boolean flexibleDates) {
        List<Predicate> predicates = new ArrayList<>();

        if (criteria.getDepartureCity() != null)
            predicates.add(cb.equal(flight.get("origin"), criteria.getDepartureCity()));
        if (criteria.getDestinationCity() != null)
            predicates.add(cb.equal(flight.get("destination"), criteria.getDestinationCity()));

        addDatePredicate(criteria, flexibleDates, predicates, cb, flight);
        addFilterPredicate(cb, flight, criteria, predicates);
        addPricePredicate(cb, flight, criteria, predicates);

        return predicates;
    }

    private void addDatePredicate(SearchFlightRequest criteria, boolean flexibleDates, List<Predicate> predicates, CriteriaBuilder cb, Root<Flight> flight) {
        if (criteria.getDate() != null) {
            LocalDateTime startOfDay = criteria.getDate().atStartOfDay();
            LocalDateTime endOfDay = criteria.getDate().atTime(23, 59, 59);
            if (flexibleDates) {
                startOfDay = criteria.getDate().atStartOfDay().minusDays(1);
                endOfDay = criteria.getDate().atStartOfDay().plusDays(1);
            }
            predicates.add(cb.between(flight.get("departureTime"), startOfDay, endOfDay));
        }
    }


    private void addFilterPredicate(CriteriaBuilder cb, Root<Flight> flight, SearchFlightRequest criteria, List<Predicate> predicates) {
        if (criteria.getAirline() != null) {
            predicates.add(cb.equal(flight.get("airlineName"), criteria.getAirline()));
        }
        if (criteria.getAircraft() != null) {
            predicates.add(cb.equal(flight.get("aircraftType"), criteria.getAircraft()));
        }
        if (criteria.getStatus() != null) {
            predicates.add(cb.equal(flight.get("flightStatus"), criteria.getStatus()));
        }
    }

    private void addPricePredicate(CriteriaBuilder cb, Root<Flight> flight, SearchFlightRequest criteria, List<Predicate> predicates) {
        if (criteria.getMaxPrice() != null && criteria.getMinPrice() == null) {
            predicates.add(cb.lessThanOrEqualTo(flight.get(PRICE), criteria.getMaxPrice()));
        } else if (criteria.getMinPrice() != null && criteria.getMaxPrice() == null) {
            predicates.add(cb.greaterThanOrEqualTo(flight.get(PRICE), criteria.getMinPrice()));
        } else if (criteria.getMinPrice() != null && criteria.getMaxPrice() != null) {
            predicates.add(cb.between(flight.get(PRICE), criteria.getMinPrice(), criteria.getMaxPrice()));
        }
    }
}
