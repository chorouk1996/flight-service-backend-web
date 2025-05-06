package com.service.backend.web.repositories;

import com.service.backend.web.models.entities.Booking;
import com.service.backend.web.models.entities.User;
import com.service.backend.web.models.enumerators.BookingStatusEnum;
import com.service.backend.web.models.responses.IBookingByMonthResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookingRepository extends JpaRepository<Booking,Long> {

    Optional<Booking> findByIdAndStatusNot(Long bookingId , BookingStatusEnum status);

    Optional<Booking> findByIdAndStatus(Long bookingId , BookingStatusEnum status);

    Optional<Booking> findByIdAndUserAndStatusNot(Long bookingId ,User user, BookingStatusEnum status);
    List<Booking> findByUser(User user, Pageable pageable);

    List<Booking> findByUserAndStatusAndFlight_DepartureTimeAfter(User user,BookingStatusEnum status,LocalDateTime date, Pageable pageable);

    List<Booking> findByUserAndStatusAndFlight_DepartureTimeBefore(User user,BookingStatusEnum status,LocalDateTime date, Pageable pageable);

    List<Booking> findByStatusAndBookingDateBefore(BookingStatusEnum status, LocalDateTime date);

    List<Booking> findByFlightIdAndStatus(Long id,BookingStatusEnum status);

    Optional<Booking> findByIdAndStatusAndUser(Long id,BookingStatusEnum status,User user);

    List<Booking> findByStatus(BookingStatusEnum status);
    long countByStatus(BookingStatusEnum cancelled);


    @Query(value="select to_char(b.booking_date,'YYYY/MM') as month, count(*) as bookingCount, sum(f.price) as revenue from booking b" +
            " JOIN flight f on f.id = b.flight_id " +
            " WHERE b.status ='CONFIRMED' " +
            "GROUP BY to_char(b.booking_date,'YYYY/MM')"
            , nativeQuery = true)
    List<IBookingByMonthResponse> getBookingsByMonth();
}
