package com.service.backend.web.services.mapper;

import com.service.backend.web.models.dto.AuditLogDto;
import com.service.backend.web.models.entities.AuditLog;

public class AuditLogMapper {

    private AuditLogMapper() {
        throw new UnsupportedOperationException("Don't instantiate this  Utility class");
    }

    public static AuditLog mapAuditLogDtoToEntity(AuditLogDto auditLog){
        AuditLog entity = new AuditLog();
        entity.setTimestamp(auditLog.getTimestamp());
        entity.setAction(auditLog.getAction());
        entity.setDetails(auditLog.getDetails());
        entity.setEntityId(auditLog.getEntityId());
        entity.setEntityType(auditLog.getEntityType());
        entity.setPerformedBy(auditLog.getPerformedBy());
        return entity;
    }

    public static AuditLogDto mapAuditLogEntityToDto(AuditLog auditLog){
        AuditLogDto dto = new AuditLogDto();
        dto.setTimestamp(auditLog.getTimestamp());
        dto.setAction(auditLog.getAction());
        dto.setDetails(auditLog.getDetails());
        dto.setEntityId(auditLog.getEntityId());
        dto.setEntityType(auditLog.getEntityType());
        dto.setPerformedBy(auditLog.getPerformedBy());
        return dto;
    }
}
