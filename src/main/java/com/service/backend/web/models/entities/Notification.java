package com.service.backend.web.models.entities;

import com.service.backend.web.models.enumerators.NotificationTypeEnum;
import jakarta.persistence.*;

import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Notification {
    @Id
    @GeneratedValue
    private Long id;


    @ManyToOne
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
