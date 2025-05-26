package com.service.backend.web.models.entities;


import jakarta.persistence.*;

import lombok.Data;


@Entity
@Data
public class RefreshToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String hashToken;

    @Column
    private long createdAT;

    @Column
    private long expiredAT;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column
    private Boolean expired;


}
