package com.service.backend.web.controllers;


import com.service.backend.web.models.dto.AuditLogDto;
import com.service.backend.web.models.requests.SearchAuditRequest;
import com.service.backend.web.services.interfaces.IAuditLogService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/audit")
@AllArgsConstructor
public class AuditAdminController {


    private final  IAuditLogService auditLogService;


    @PostMapping("/logs")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<AuditLogDto> getauditLog(@RequestBody @Valid SearchAuditRequest req) {
        return auditLogService.getAuditByCriteria(req);
    }


}
