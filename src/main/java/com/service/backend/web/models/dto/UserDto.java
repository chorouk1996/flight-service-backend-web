package com.service.backend.web.models.dto;


import com.service.backend.web.models.enumerators.RoleEnum;

import java.util.List;

public class UserDto {

    private Long id;

    private String name;

    private String email;

    private String password;

    private RoleEnum role;

    private List<BookingDto> booking;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public RoleEnum getRole() {
        return role;
    }

    public void setRole(RoleEnum role) {
        this.role = role;
    }

    public List<BookingDto> getBooking() {
        return booking;
    }

    public void setBooking(List<BookingDto> booking) {
        this.booking = booking;
    }
}
