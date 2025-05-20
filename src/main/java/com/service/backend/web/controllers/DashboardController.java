package com.service.backend.web.controllers;


import com.service.backend.web.models.responses.DashboardResponse;
import com.service.backend.web.services.interfaces.IDashboardService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/dashboard/")
@AllArgsConstructor
public class DashboardController {

    private final IDashboardService dashboardService;

    @GetMapping("overview")
    @PreAuthorize("hasAuthority('ADMIN')")
    public DashboardResponse getDashboardOverview() {
        return dashboardService.getOverview();
    }
}
