package com.service.backend.web.models.entities;

import jakarta.persistence.*;

import lombok.Data;


@Entity
@Data
public class SavedPassenger {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private User user;

    private String firstName;
    private String lastName;
    private Integer age;
    private String email;



}