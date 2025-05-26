package com.service.backend.web.controllers;


import com.service.backend.web.models.dto.AuditLogDto;
import com.service.backend.web.models.requests.SearchAuditRequest;
import com.service.backend.web.services.interfaces.IAuditLogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/audit")
@AllArgsConstructor
public class AuditAdminController {


    private final  IAuditLogService auditLogService;


    @Operation(
            summary = "Retrieve audit logs",
            description = "Fetches a list of audit logs that track user actions, filtered by the provided criteria. Only accessible to administrators."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Audit logs successfully retrieved"),
            @ApiResponse(responseCode = "400", description = "Invalid search criteria provided"),
            @ApiResponse(responseCode = "403", description = "Access denied - admin privileges required"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("/logs")
    @PreAuthorize("hasAuthority(T(com.service.backend.web.constantes.Role).ADMIN")
    public List<AuditLogDto> getAuditLog(@RequestBody @Valid SearchAuditRequest req) {
        return auditLogService.getAuditByCriteria(req);
    }

}
