package com.example.school_management.controller;

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

    // ── POST /api/complaints ──
    @PostMapping
    public ResponseEntity<ComplaintResponseDto> createComplaint(
            @Valid @RequestBody ComplaintRequestDto request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(complaintService.createComplaint(request));
    }

    // ── GET /api/complaints ──
    @GetMapping
    public ResponseEntity<List<ComplaintResponseDto>> getAllComplaints() {
        return ResponseEntity.ok(complaintService.getAllComplaints());
    }

    // ── GET /api/complaints/{id} ──
    @GetMapping("/{id}")
    public ResponseEntity<ComplaintResponseDto> getComplaintById(@PathVariable Long id) {
        return ResponseEntity.ok(complaintService.getComplaintById(id));
    }

    // ── GET /api/complaints/student/{studentId} ──
    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<ComplaintResponseDto>> getByStudent(
            @PathVariable Long studentId) {
        return ResponseEntity.ok(complaintService.getComplaintsByStudent(studentId));
    }

    // ── GET /api/complaints/teacher/{teacherId} ──
    @GetMapping("/teacher/{teacherId}")
    public ResponseEntity<List<ComplaintResponseDto>> getByTeacher(
            @PathVariable Long teacherId) {
        return ResponseEntity.ok(complaintService.getComplaintsByTeacher(teacherId));
    }

    // ── GET /api/complaints/status/{status} ──
    @GetMapping("/status/{status}")
    public ResponseEntity<List<ComplaintResponseDto>> getByStatus(
            @PathVariable ComplaintStatus status) {
        return ResponseEntity.ok(complaintService.getComplaintsByStatus(status));
    }

    // ── GET /api/complaints/priority/{priority} ──
    @GetMapping("/priority/{priority}")
    public ResponseEntity<List<ComplaintResponseDto>> getByPriority(
            @PathVariable Priority priority) {
        return ResponseEntity.ok(complaintService.getComplaintsByPriority(priority));
    }

    // ── GET /api/complaints/category/{category} ──
    @GetMapping("/category/{category}")
    public ResponseEntity<List<ComplaintResponseDto>> getByCategory(
            @PathVariable ComplaintCategory category) {
        return ResponseEntity.ok(complaintService.getComplaintsByCategory(category));
    }

    // ── PATCH /api/complaints/{id}/status ──
    @PatchMapping("/{id}/status")
    public ResponseEntity<ComplaintResponseDto> updateStatus(
            @PathVariable Long id,
            @RequestParam ComplaintStatus status) {
        return ResponseEntity.ok(complaintService.updateStatus(id, status));
    }

    // ── PATCH /api/complaints/{id}/resolve ──
    @PatchMapping("/{id}/resolve")
    public ResponseEntity<ComplaintResponseDto> resolveComplaint(
            @PathVariable Long id,
            @RequestParam String resolutionComment,
            @RequestParam String resolvedBy) {
        return ResponseEntity.ok(complaintService
                .resolveComplaint(id, resolutionComment, resolvedBy));
    }

    // ── DELETE /api/complaints/{id} ──
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteComplaint(@PathVariable Long id) {
        complaintService.deleteComplaint(id);
        return ResponseEntity.ok("Complaint deleted successfully");
    }
}