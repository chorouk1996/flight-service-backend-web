package com.service.backend.web.models.entities;


import com.service.backend.web.models.enumerators.TypeTokenEnum;
import jakarta.persistence.*;

import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class EmailToken {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    @Column
    private String token;

    @Enumerated(EnumType.STRING)
    @Column
    private TypeTokenEnum type;

    @Column
    private LocalDateTime expireAt;

    @Column
    private LocalDateTime createdAt;

    @Column
    private String email;

    @Column
    private boolean used;

    @Column
    private String ipAddress;



}
