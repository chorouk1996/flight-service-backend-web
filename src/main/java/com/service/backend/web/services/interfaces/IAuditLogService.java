package com.service.backend.web.services.interfaces;

import com.service.backend.web.models.enumerators.BookingStatusEnum;

public interface IAuditLogService {

    void auditBookingCancel(BookingStatusEnum oldStatus, long entityId);
    void auditBookingConfirmation(long entityId);
    void auditFlightStatusUpdate(BookingStatusEnum oldStatus, BookingStatusEnum currentStatus, long entityId);


}
