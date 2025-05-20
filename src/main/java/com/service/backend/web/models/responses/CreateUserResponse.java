package com.service.backend.web.models.responses;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreateUserResponse {

    private Long id;
    private String firstName;

    private String lastName;

    private String email;


    private String role;


}
