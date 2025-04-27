package com.service.backend.web.controllers;


import com.service.backend.web.models.responses.DashboardResponse;
import com.service.backend.web.services.interfaces.IDashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/dashboard/")
public class DashboardController {

    @Autowired
    IDashboardService dashboardService;

    @GetMapping("overview")
    public DashboardResponse getDashboardOverview() {
        return dashboardService.getOverview();
    }
}
