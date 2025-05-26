package com.service.backend.web.models.entities;

import com.service.backend.web.models.enumerators.BookingStatusEnum;
import jakarta.persistence.*;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class Booking {
    @Id
    @GeneratedValue
    private Long id;


    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @JoinColumn(name="flight_id")
    @ManyToOne
    private Flight flight;

    @Column
    private LocalDateTime bookingDate;

    @Column
    @Enumerated(EnumType.STRING)
    private BookingStatusEnum status;

    @OneToMany(mappedBy = "booking",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Passenger> passengers;


}
