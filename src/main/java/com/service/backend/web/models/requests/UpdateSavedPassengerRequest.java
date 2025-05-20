package com.service.backend.web.models.requests;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UpdateSavedPassengerRequest {

    private String firstName;

    private String lastName;

    private Integer age;

    private String email;


}