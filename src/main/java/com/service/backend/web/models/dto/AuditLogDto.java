package com.service.backend.web.models.dto;

import com.service.backend.web.models.enumerators.EntityActionEnum;
import com.service.backend.web.models.enumerators.EntityTypeEnum;

import java.time.LocalDateTime;


public class AuditLogDto {

    private LocalDateTime timestamp;
    private EntityTypeEnum entityType;
    private EntityActionEnum action;
    private long entityId;
    private String performedBy;
    private String details;


    public EntityTypeEnum getEntityType() {
        return entityType;
    }

    public void setEntityType(EntityTypeEnum entityType) {
        this.entityType = entityType;
    }

    public EntityActionEnum getAction() {
        return action;
    }

    public void setAction(EntityActionEnum action) {
        this.action = action;
    }

    public long getEntityId() {
        return entityId;
    }

    public void setEntityId(long entityId) {
        this.entityId = entityId;
    }

    public String getPerformedBy() {
        return performedBy;
    }

    public void setPerformedBy(String performedBy) {
        this.performedBy = performedBy;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public AuditLogDto(LocalDateTime timestamp,EntityTypeEnum entityType, EntityActionEnum action, long entityId, String performedBy, String details) {
        this.timestamp = timestamp;
        this.entityType = entityType;
        this.action = action;
        this.entityId = entityId;
        this.performedBy = performedBy;
        this.details = details;
    }
}
