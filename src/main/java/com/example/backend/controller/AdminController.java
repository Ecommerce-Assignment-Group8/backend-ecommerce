package com.example.backend.controller;

import com.example.backend.dto.response.RevenueStatsDTO;
import com.example.backend.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "http://localhost:5173")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @GetMapping("/revenue")
    public ResponseEntity<RevenueStatsDTO> getRevenueStats(@org.springframework.web.bind.annotation.RequestParam(required = false, defaultValue = "2025") Integer year) {
        return adminService.getRevenueStats(year);
    }
}

