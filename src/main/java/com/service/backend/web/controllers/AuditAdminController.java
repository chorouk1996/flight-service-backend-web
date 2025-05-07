package com.service.backend.web.controllers;


import com.service.backend.web.models.dto.AuditLogDto;
import com.service.backend.web.models.requests.SearchAuditRequest;
import com.service.backend.web.services.interfaces.IAuditLogService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin/audit")
public class AuditAdminController {

    @Autowired
    IAuditLogService auditLogService;


    @GetMapping("/logs")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<AuditLogDto> getauditLog(@RequestBody @Valid SearchAuditRequest req) {
        return auditLogService.getAuditByCriteria(req);
    }


}
