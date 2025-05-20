package com.service.backend.web.models.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
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
