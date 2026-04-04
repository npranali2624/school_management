package com.example.school_management.controller;

import com.example.school_management.dto.ApiResponse;
import com.example.school_management.dto.FinanceOfficerRequestDto;
import com.example.school_management.dto.FinanceOfficerResponseDto;
import com.example.school_management.service.FinanceOfficerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize; // ✅ ADDED
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/finance")
@RequiredArgsConstructor
public class FinanceOfficerController {

    private final FinanceOfficerService financeOfficerService;


    @GetMapping("/dashboard")
    @PreAuthorize("hasRole('FINANCE')")
    public ResponseEntity<ApiResponse<String>> financeDashboard() {
        return ResponseEntity.ok(
                ApiResponse.ok("Dashboard fetched", "Finance Dashboard")
        );
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<FinanceOfficerResponseDto>> createFinanceOfficer(
            @Valid @RequestBody FinanceOfficerRequestDto request) {

        FinanceOfficerResponseDto response = financeOfficerService.createFinanceOfficer(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.ok("Finance Officer created successfully", response));
    }


    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN','FINANCE')")
    public ResponseEntity<ApiResponse<List<FinanceOfficerResponseDto>>> getAllFinanceOfficers() {

        List<FinanceOfficerResponseDto> officers = financeOfficerService.getAllFinanceOfficers();

        return ResponseEntity.ok(
                ApiResponse.ok("Finance Officers fetched successfully", officers)
        );
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','FINANCE')")
    public ResponseEntity<ApiResponse<FinanceOfficerResponseDto>> getFinanceOfficerById(
            @PathVariable Long id) {

        FinanceOfficerResponseDto officer = financeOfficerService.getFinanceOfficerById(id);

        return ResponseEntity.ok(
                ApiResponse.ok("Finance Officer fetched successfully", officer)
        );
    }


    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<FinanceOfficerResponseDto>> updateFinanceOfficer(
            @PathVariable Long id,
            @Valid @RequestBody FinanceOfficerRequestDto request) {

        FinanceOfficerResponseDto updated = financeOfficerService.updateFinanceOfficer(id, request);

        return ResponseEntity.ok(
                ApiResponse.ok("Finance Officer updated successfully", updated)
        );
    }

    @PatchMapping("/{id}/status")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<String>> toggleStatus(@PathVariable Long id) {

        financeOfficerService.toggleStatus(id);

        return ResponseEntity.ok(
                ApiResponse.ok("Finance Officer status updated successfully", null)
        );
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<String>> deleteFinanceOfficer(@PathVariable Long id) {

        financeOfficerService.deleteFinanceOfficer(id);

        return ResponseEntity.ok(
                ApiResponse.ok("Finance Officer deleted successfully", null)
        );
    }


    @GetMapping("/{id}/degree-type")
    @PreAuthorize("hasAnyRole('ADMIN','FINANCE')")
    public ResponseEntity<ApiResponse<String>> getDegreeTypeByFinanceOfficerId(
            @PathVariable Long id) {

        FinanceOfficerResponseDto officer = financeOfficerService.getFinanceOfficerById(id);

        return ResponseEntity.ok(
                ApiResponse.ok(
                        "Degree type fetched successfully",
                        officer.getDegreeType() != null ? officer.getDegreeType().name() : null
                )
        );
    }
}