package com.service.backend.web.services.implementation;

import com.service.backend.web.models.dto.AuditLogDto;
import com.service.backend.web.models.enumerators.BookingStatusEnum;
import com.service.backend.web.models.enumerators.EntityActionEnum;
import com.service.backend.web.models.enumerators.EntityTypeEnum;
import com.service.backend.web.models.requests.SearchAuditRequest;
import com.service.backend.web.repositories.AuditLogCustomRepository;
import com.service.backend.web.repositories.AuditLogRepository;
import com.service.backend.web.security.UserDetailsImpl;
import com.service.backend.web.services.interfaces.IAuditLogService;
import com.service.backend.web.services.mapper.AuditLogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AuditLogService implements IAuditLogService {

    private static final String SYSTEM = "SYSTEM";

    @Autowired
    AuditLogCustomRepository auditLogCustomRepository;
    @Autowired
    AuditLogRepository auditLogRepository;
    @Override
    @Transactional
    public void auditBookingCancel(BookingStatusEnum oldStatus, long entityId) {
            String performedBy  = Optional.of(SecurityContextHolder
                    .getContext()).map(a->  Optional.of(a).get().getAuthentication()).map(a -> ((UserDetailsImpl)a.getPrincipal()).getUsername()).orElse(SYSTEM);
            AuditLogDto auditLog = new AuditLogDto(LocalDateTime.now(),EntityTypeEnum.BOOKING,EntityActionEnum.UPDATE,entityId,performedBy,"Status changed from "+oldStatus+" to CANCELED");
        auditLogRepository.save(AuditLogMapper.mapAuditLogDtoToEntity(auditLog));
    }

    @Override
    @Transactional
    public void auditBookingConfirmation(long entityId) {
        String performedBy  = Optional.of(SecurityContextHolder
                .getContext()).map(a->  Optional.of(a).get().getAuthentication()).map(a -> ((UserDetailsImpl)a.getPrincipal()).getUsername()).orElse(SYSTEM);
        AuditLogDto auditLog = new AuditLogDto(LocalDateTime.now(),EntityTypeEnum.BOOKING,EntityActionEnum.UPDATE,entityId,performedBy,"Status changed from PENDING_PAYMENT to CONFIRMED");
        auditLogRepository.save(AuditLogMapper.mapAuditLogDtoToEntity(auditLog));
    }

    @Override
    @Transactional
    public void auditFlightStatusUpdate(BookingStatusEnum oldStatus,BookingStatusEnum currentStatus, long entityId) {
        String performedBy  = Optional.of(SecurityContextHolder
                .getContext()).map(a->  Optional.of(a).get().getAuthentication()).map(a -> ((UserDetailsImpl)a.getPrincipal()).getUsername()).orElse(SYSTEM);
        AuditLogDto auditLog = new AuditLogDto(LocalDateTime.now(),EntityTypeEnum.FLIGHT,EntityActionEnum.UPDATE,entityId,performedBy,"Status changed from "+oldStatus+" to " + currentStatus);
        auditLogRepository.save(AuditLogMapper.mapAuditLogDtoToEntity(auditLog));
    }

    @Override
    @Transactional
    public List<AuditLogDto> getAudit() {
        return auditLogRepository.findAll().stream().map(AuditLogMapper::mapAuditLogEntityToDto).toList();
    }

    @Override
    @Transactional
    public List<AuditLogDto> getAuditByCriteria(SearchAuditRequest request) {
        return auditLogCustomRepository.findByCriteria(request).stream().map(AuditLogMapper::mapAuditLogEntityToDto).toList();
    }
}
