package com.example.school_management.controller;

import com.example.school_management.dto.*;
import com.example.school_management.service.AdminService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping("/dashboard")
    public ResponseEntity<ApiResponse<String>> adminDashboard() {
        return ResponseEntity.ok(
                ApiResponse.ok("Dashboard fetched", "Admin Dashboard")
        );
    }
    @PostMapping("/create")
    public ResponseEntity<?> createAdmin(@Valid @RequestBody AdminRequestDto request) {
        try {
            AdminResponseDto response = adminService.createAdmin(request);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(ApiResponse.ok("Admin created successfully", response));
        } catch (RuntimeException e) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(ApiResponse.error(e.getMessage()));
        }
    }
    @GetMapping("/all")
    public ResponseEntity<?> getAllAdmins() {
        try {
            List<AdminResponseDto> admins = adminService.getAllAdmins();
            return ResponseEntity.ok(ApiResponse.ok("Admins fetched successfully", admins));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiResponse.error(e.getMessage()));
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getAdminById(@PathVariable Long id) {
        try {
            AdminResponseDto admin = adminService.getAdminById(id);
            return ResponseEntity.ok(ApiResponse.ok("Admin fetched successfully", admin));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(ApiResponse.error("Admin not found"));
        }
    }
}
