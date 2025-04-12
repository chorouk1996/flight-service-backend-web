package com.service.backend.web.models.dto;


public class PassengerDto {

    private Long id;

    private String name;

    private Integer age;

    private BookingDto booking;

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

    public BookingDto getBooking() {
        return booking;
    }

    public void setBooking(BookingDto booking) {
        this.booking = booking;
    }
}
