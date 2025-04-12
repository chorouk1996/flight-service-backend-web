package com.service.backend.web.models.entities;

import jakarta.persistence.Entity;
import org.springframework.data.annotation.Id;

@Entity
public class Passenger {

    @Id
    private Long id;

    private String name;

    private Integer age;

    private Booking booking;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
