package com.service.backend.web.repositories;

import com.service.backend.web.models.entities.Passenger;
import com.service.backend.web.models.enumerators.BookingStatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PassengerRepository extends JpaRepository<Passenger,Long> {

    List<Passenger> findByBooking_Flight_idAndBooking_status(Long flightId, BookingStatusEnum status);
}
