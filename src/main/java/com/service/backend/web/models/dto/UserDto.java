package com.service.backend.web.models.dto;


import com.service.backend.web.models.enumerators.RoleEnum;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class UserDto {

    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private RoleEnum role;

    private List<BookingDto> booking;


}
