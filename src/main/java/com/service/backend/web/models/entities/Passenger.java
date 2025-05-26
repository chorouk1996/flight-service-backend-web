package com.service.backend.web.models.entities;

import jakarta.persistence.*;

import lombok.Data;

@Entity
@Data
public class Passenger {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private String email;
    @Column
    private Integer age;

    private String bookedByUserEmail;

    @ManyToOne
    @JoinColumn(name="booking_id")
    private Booking booking;


}
