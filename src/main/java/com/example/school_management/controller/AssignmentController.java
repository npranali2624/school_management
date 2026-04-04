package com.example.school_management.controller;

import com.example.school_management.dto.ApiResponse;
import com.example.school_management.dto.AssignmentRequestDto;
import com.example.school_management.dto.AssignmentResponseDto;
import com.example.school_management.service.AssignmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/assignments")
@RequiredArgsConstructor
public class AssignmentController {

    private final AssignmentService assignmentService;

    @PostMapping
    public ResponseEntity<ApiResponse<AssignmentResponseDto>> createAssignment(
            @Valid @RequestBody AssignmentRequestDto requestDto) {

        AssignmentResponseDto response = assignmentService.createAssignment(requestDto);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.ok("Assignment created successfully", response));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<AssignmentResponseDto>> getAssignmentById(
            @PathVariable Long id) {

        AssignmentResponseDto response = assignmentService.getAssignmentById(id);

        return ResponseEntity.ok(
                ApiResponse.ok("Assignment fetched successfully", response)
        );
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<AssignmentResponseDto>>> getAllAssignments() {

        List<AssignmentResponseDto> response = assignmentService.getAllAssignments();

        return ResponseEntity.ok(
                ApiResponse.ok("Assignments fetched successfully", response)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<AssignmentResponseDto>> updateAssignment(
            @PathVariable Long id,
            @Valid @RequestBody AssignmentRequestDto requestDto) {

        AssignmentResponseDto response = assignmentService.updateAssignment(id, requestDto);

        return ResponseEntity.ok(
                ApiResponse.ok("Assignment updated successfully", response)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteAssignment(@PathVariable Long id) {

        assignmentService.deleteAssignment(id);

        return ResponseEntity.ok(
                ApiResponse.ok("Assignment deleted successfully", null)
        );
    }

    @PatchMapping("/{id}/publish")
    public ResponseEntity<ApiResponse<AssignmentResponseDto>> publishAssignment(
            @PathVariable Long id) {

        AssignmentResponseDto response = assignmentService.publishAssignment(id);

        return ResponseEntity.ok(
                ApiResponse.ok("Assignment published successfully", response)
        );
    }

    @PatchMapping("/{id}/cancel")
    public ResponseEntity<ApiResponse<AssignmentResponseDto>> cancelAssignment(
            @PathVariable Long id) {

        AssignmentResponseDto response = assignmentService.cancelAssignment(id);

        return ResponseEntity.ok(
                ApiResponse.ok("Assignment cancelled successfully", response)
        );
    }
}