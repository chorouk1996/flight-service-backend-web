package com.service.backend.web.models.entities;


import com.service.backend.web.models.enumerators.EntityActionEnum;
import com.service.backend.web.models.enumerators.EntityTypeEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
public class AuditLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private LocalDateTime timestamp;

    @Enumerated(EnumType.STRING)
    private EntityTypeEnum entityType;

    @Enumerated(EnumType.STRING)
    private EntityActionEnum action;
    private long entityId;
    private String performedBy;
    private String details;



}
