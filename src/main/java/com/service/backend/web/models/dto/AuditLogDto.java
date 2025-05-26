package com.service.backend.web.models.dto;

import com.service.backend.web.models.enumerators.EntityActionEnum;
import com.service.backend.web.models.enumerators.EntityTypeEnum;
import lombok.AllArgsConstructor;

import lombok.NoArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuditLogDto {

    private LocalDateTime timestamp;
    private EntityTypeEnum entityType;
    private EntityActionEnum action;
    private long entityId;
    private String performedBy;
    private String details;


}
