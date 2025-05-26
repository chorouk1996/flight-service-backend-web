package com.service.backend.web.models.requests;



import lombok.Data;

@Data
public class UpdateSavedPassengerRequest {

    private String firstName;

    private String lastName;

    private Integer age;

    private String email;


}