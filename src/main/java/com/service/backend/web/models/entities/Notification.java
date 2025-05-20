package com.service.backend.web.models.entities;

import com.service.backend.web.models.enumerators.NotificationTypeEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
public class Notification {
    @Id
    @GeneratedValue
    private Long id;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="user_id")
    private User user;

    private String title;

    @Column(length = 5000)
    private String message;
    private LocalDateTime createdAt;
    private Boolean isRead = false; // default false

    @Enumerated(EnumType.STRING)
    private NotificationTypeEnum type = NotificationTypeEnum.INFO;
    @ManyToOne(optional = true)
    private Flight flight;



}
