package com.service.backend.web.services.mapper;

import com.service.backend.web.models.dto.AuditLogDto;
import com.service.backend.web.models.entities.AuditLog;

public class AuditLogMapper {

    private AuditLogMapper() {
        throw new UnsupportedOperationException("Don't instantiate this  Utility class");
    };

    public static AuditLog mapAuditLogEntityToDto(AuditLogDto auditLog){
        AuditLog entity = new AuditLog();
        entity.setTimestamp(auditLog.getTimestamp());
        entity.setAction(auditLog.getAction());
        entity.setDetails(auditLog.getDetails());
        entity.setEntityId(auditLog.getEntityId());
        entity.setEntityType(auditLog.getEntityType());
        entity.setPerformedBy(auditLog.getPerformedBy());
        return entity;
    }


}
