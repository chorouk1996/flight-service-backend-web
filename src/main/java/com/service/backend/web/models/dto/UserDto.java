package com.service.backend.web.models.dto;


import com.service.backend.web.models.enumerators.RoleEnum;

import lombok.Data;

import java.util.List;

@Data
public class UserDto {

    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private RoleEnum role;

    private List<BookingDto> booking;


}
