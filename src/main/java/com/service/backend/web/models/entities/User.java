package com.service.backend.web.models.entities;

import com.service.backend.web.models.enumerators.RoleEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Entity(name = "Users")
@Setter
@Getter
public class User implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private String email;

    @Column
    private String password;

    @Column
    @Enumerated(EnumType.STRING)
    private RoleEnum role;

    @OneToMany(mappedBy = "user")
    private List<Booking> booking;

    @Column
    private boolean enabled;

    @Column
    private int loyaltyPoints = 0;


}
