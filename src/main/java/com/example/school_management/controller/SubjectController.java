package com.example.school_management.controller;

import com.example.school_management.dto.SubjectRequestDto;
import com.example.school_management.dto.SubjectResponseDto;
import com.example.school_management.service.SubjectService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subjects")
@RequiredArgsConstructor
public class SubjectController {

    private final SubjectService subjectService;

    // POST /api/subjects
    @PostMapping
    public ResponseEntity<SubjectResponseDto> createSubject(
            @Valid @RequestBody SubjectRequestDto request) {
        SubjectResponseDto response = subjectService.createSubject(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // GET /api/subjects
    @GetMapping
    public ResponseEntity<List<SubjectResponseDto>> getAllSubjects() {
        return ResponseEntity.ok(subjectService.getAllSubjects());
    }

    // GET /api/subjects/101
    @GetMapping("/{subjectCode}")
    public ResponseEntity<SubjectResponseDto> getSubjectByCode(
            @PathVariable Integer subjectCode) {
        return ResponseEntity.ok(subjectService.getSubjectByCode(subjectCode));
    }

    // PUT /api/subjects/101
    @PutMapping("/{subjectCode}")
    public ResponseEntity<SubjectResponseDto> updateSubject(
            @PathVariable Integer subjectCode,
            @Valid @RequestBody SubjectRequestDto request) {
        return ResponseEntity.ok(subjectService.updateSubject(subjectCode, request));
    }

    // DELETE /api/subjects/101
    @DeleteMapping("/{subjectCode}")
    public ResponseEntity<String> deleteSubject(
            @PathVariable Integer subjectCode) {
        subjectService.deleteSubject(subjectCode);
        return ResponseEntity.ok("Subject deleted successfully");
    }
}