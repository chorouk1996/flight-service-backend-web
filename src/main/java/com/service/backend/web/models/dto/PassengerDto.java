package com.service.backend.web.models.dto;


import lombok.Data;

@Data
public class PassengerDto {

    private Long id;

    private String firstName;

    private String lastName;

    private Integer age;

    private String email;


}
