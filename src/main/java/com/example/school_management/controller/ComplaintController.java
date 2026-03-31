package com.example.school_management.controller;

import com.example.school_management.dto.ApiResponse;
import com.example.school_management.dto.ComplaintRequestDto;
import com.example.school_management.dto.ComplaintResponseDto;
import com.example.school_management.enums.ComplaintCategory;
import com.example.school_management.enums.ComplaintStatus;
import com.example.school_management.enums.Priority;
import com.example.school_management.service.ComplaintService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/complaints")
@RequiredArgsConstructor
public class ComplaintController {

    private final ComplaintService complaintService;

    @PostMapping
    public ResponseEntity<ApiResponse<ComplaintResponseDto>> createComplaint(
            @Valid @RequestBody ComplaintRequestDto request) {

        ComplaintResponseDto response = complaintService.createComplaint(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.ok("Complaint created successfully", response));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ComplaintResponseDto>>> getAllComplaints() {

        List<ComplaintResponseDto> response = complaintService.getAllComplaints();

        return ResponseEntity.ok(
                ApiResponse.ok("Complaints fetched successfully", response)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ComplaintResponseDto>> getComplaintById(
            @PathVariable Long id) {

        ComplaintResponseDto response = complaintService.getComplaintById(id);

        return ResponseEntity.ok(
                ApiResponse.ok("Complaint fetched successfully", response)
        );
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<ApiResponse<List<ComplaintResponseDto>>> getByStudent(
            @PathVariable Long studentId) {

        List<ComplaintResponseDto> response =
                complaintService.getComplaintsByStudent(studentId);

        return ResponseEntity.ok(
                ApiResponse.ok("Student complaints fetched successfully", response)
        );
    }

    @GetMapping("/teacher/{teacherId}")
    public ResponseEntity<ApiResponse<List<ComplaintResponseDto>>> getByTeacher(
            @PathVariable Long teacherId) {

        List<ComplaintResponseDto> response =
                complaintService.getComplaintsByTeacher(teacherId);

        return ResponseEntity.ok(
                ApiResponse.ok("Teacher complaints fetched successfully", response)
        );
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<ApiResponse<List<ComplaintResponseDto>>> getByStatus(
            @PathVariable ComplaintStatus status) {

        List<ComplaintResponseDto> response =
                complaintService.getComplaintsByStatus(status);

        return ResponseEntity.ok(
                ApiResponse.ok("Complaints by status fetched successfully", response)
        );
    }

    @GetMapping("/priority/{priority}")
    public ResponseEntity<ApiResponse<List<ComplaintResponseDto>>> getByPriority(
            @PathVariable Priority priority) {

        List<ComplaintResponseDto> response =
                complaintService.getComplaintsByPriority(priority);

        return ResponseEntity.ok(
                ApiResponse.ok("Complaints by priority fetched successfully", response)
        );
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<ApiResponse<List<ComplaintResponseDto>>> getByCategory(
            @PathVariable ComplaintCategory category) {

        List<ComplaintResponseDto> response =
                complaintService.getComplaintsByCategory(category);

        return ResponseEntity.ok(
                ApiResponse.ok("Complaints by category fetched successfully", response)
        );
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<ApiResponse<ComplaintResponseDto>> updateStatus(
            @PathVariable Long id,
            @RequestParam ComplaintStatus status) {

        ComplaintResponseDto response =
                complaintService.updateStatus(id, status);

        return ResponseEntity.ok(
                ApiResponse.ok("Complaint status updated successfully", response)
        );
    }

    @PatchMapping("/{id}/resolve")
    public ResponseEntity<ApiResponse<ComplaintResponseDto>> resolveComplaint(
            @PathVariable Long id,
            @RequestParam String resolutionComment,
            @RequestParam String resolvedBy) {

        ComplaintResponseDto response =
                complaintService.resolveComplaint(id, resolutionComment, resolvedBy);

        return ResponseEntity.ok(
                ApiResponse.ok("Complaint resolved successfully", response)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteComplaint(
            @PathVariable Long id) {

        complaintService.deleteComplaint(id);

        return ResponseEntity.ok(
                ApiResponse.ok("Complaint deleted successfully", null)
        );
    }
}