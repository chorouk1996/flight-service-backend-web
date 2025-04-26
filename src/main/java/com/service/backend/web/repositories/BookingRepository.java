package com.service.backend.web.repositories;

import com.service.backend.web.models.entities.Booking;
import com.service.backend.web.models.entities.User;
import com.service.backend.web.models.enumerators.BookingStatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookingRepository extends JpaRepository<Booking,Long> {

    Optional<Booking> findByIdAndStatusNot(Long bookingId , BookingStatusEnum status);

    Optional<Booking> findByIdAndUserAndStatusNot(Long bookingId ,User user, BookingStatusEnum status);
    List<Booking> findByUser(User user);
}
