package com.service.backend.web.services.interfaces;

import com.service.backend.web.models.dto.AuditLogDto;
import com.service.backend.web.models.enumerators.BookingStatusEnum;
import com.service.backend.web.models.requests.SearchAuditRequest;

import java.util.List;

public interface IAuditLogService {

    void auditBookingCancel(BookingStatusEnum oldStatus, long entityId);
    void auditBookingConfirmation(long entityId);
    void auditFlightStatusUpdate(BookingStatusEnum oldStatus, BookingStatusEnum currentStatus, long entityId);

    List<AuditLogDto> getAudit();

    List<AuditLogDto> getAuditByCriteria(SearchAuditRequest request);

}
