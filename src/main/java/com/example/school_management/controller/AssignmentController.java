package com.example.school_management.controller;

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
    public ResponseEntity<AssignmentResponseDto> createAssignment(
            @Valid @RequestBody AssignmentRequestDto requestDto) {
        AssignmentResponseDto response = assignmentService.createAssignment(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    @GetMapping("/{id}")
    public ResponseEntity<AssignmentResponseDto> getAssignmentById(@PathVariable Long id) {
        AssignmentResponseDto response = assignmentService.getAssignmentById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<AssignmentResponseDto>> getAllAssignments() {
        List<AssignmentResponseDto> response = assignmentService.getAllAssignments();
        return ResponseEntity.ok(response);
    }



    @PutMapping("/{id}")
    public ResponseEntity<AssignmentResponseDto> updateAssignment(
            @PathVariable Long id,
            @Valid @RequestBody AssignmentRequestDto requestDto) {
        AssignmentResponseDto response = assignmentService.updateAssignment(id, requestDto);
        return ResponseEntity.ok(response);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAssignment(@PathVariable Long id) {
        assignmentService.deleteAssignment(id);
        return ResponseEntity.ok("Assignment deleted successfully.");
    }


    @PatchMapping("/{id}/publish")
    public ResponseEntity<AssignmentResponseDto> publishAssignment(@PathVariable Long id) {
        AssignmentResponseDto response = assignmentService.publishAssignment(id);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}/cancel")
    public ResponseEntity<AssignmentResponseDto> cancelAssignment(@PathVariable Long id) {
        AssignmentResponseDto response = assignmentService.cancelAssignment(id);
        return ResponseEntity.ok(response);
    }
}