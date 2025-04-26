package com.service.backend.web.models.entities;

import jakarta.persistence.*;

@Entity
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String mail) {
        this.email = mail;
    }

    public String getBookedByUserEmail() {
        return bookedByUserEmail;
    }

    public void setBookedByUserEmail(String bookedByUserEmail) {
        this.bookedByUserEmail = bookedByUserEmail;
    }
}
