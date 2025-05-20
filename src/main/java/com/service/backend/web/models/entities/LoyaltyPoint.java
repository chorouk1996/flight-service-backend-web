package com.service.backend.web.models.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class LoyaltyPoint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @Column
    private Integer points;
    @Column
    private LocalDateTime earnedAt;

    @Column
    private LocalDateTime expiresAt;
    @JoinColumn(name = "booking_id")
    @ManyToOne
    private Booking bookingReference;
}
