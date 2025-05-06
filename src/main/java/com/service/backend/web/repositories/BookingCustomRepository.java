package com.service.backend.web.repositories;

import com.service.backend.web.models.entities.Booking;
import com.service.backend.web.models.requests.SearchBookingRequest;
import com.service.backend.web.services.implementation.UserService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class BookingCustomRepository {

    @Autowired
    UserService userService;
    @PersistenceContext
    private EntityManager entityManager;


    public List<Booking> findByCriteria(SearchBookingRequest criteria) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Booking> query = cb.createQuery(Booking.class);
        Root<Booking> booking = query.from(Booking.class);

        List<Predicate> predicates = new ArrayList<>();
        if (criteria.getFlightNumber() != null)
            predicates.add(cb.equal(booking.get("flight_id"), criteria.getFlightNumber()));
        if (criteria.getEmail() != null)
            predicates.add(cb.equal(booking.get("user_id"), userService.getUserById(criteria.getEmail()).getId()));
        if (criteria.getStatus() != null)
            predicates.add(cb.equal(booking.get("status"), criteria.getStatus()));

        query.where(predicates.toArray(new Predicate[0]));

        return entityManager.createQuery(query).setFirstResult((criteria.getPage()+1) * criteria.getSize()).setMaxResults(criteria.getSize()).getResultList();
    }
}
