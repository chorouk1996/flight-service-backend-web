package com.service.backend.web.models.entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Setter
@Getter
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
