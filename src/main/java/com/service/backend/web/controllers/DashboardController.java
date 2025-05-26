package com.service.backend.web.controllers;


import com.service.backend.web.models.responses.DashboardResponse;
import com.service.backend.web.services.interfaces.IDashboardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/admin/dashboard/")
@AllArgsConstructor
@Tag(name = "Dashboard (Admin)", description = "Provides key metrics and overviews for administrators.")
public class DashboardController {

    private final IDashboardService dashboardService;

    @Operation(
            summary = "Get dashboard overview",
            description = "Returns general statistics and metrics related to bookings, users, and flights for administrators."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Dashboard data retrieved successfully"),
            @ApiResponse(responseCode = "403", description = "Access denied — admin role required"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("overview")
    @PreAuthorize("hasAuthority(T(com.service.backend.web.constantes.Role).ADMIN")
    public DashboardResponse getDashboardOverview() {
        return dashboardService.getOverview();
    }
}
