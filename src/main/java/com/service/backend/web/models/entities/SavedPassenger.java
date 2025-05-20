package com.service.backend.web.models.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Setter
@Getter
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