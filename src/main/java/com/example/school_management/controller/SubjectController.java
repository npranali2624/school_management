package com.example.school_management.controller;

import com.example.school_management.dto.ApiResponse;
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

    @PostMapping
    public ResponseEntity<ApiResponse<SubjectResponseDto>> createSubject(
            @Valid @RequestBody SubjectRequestDto request) {

        SubjectResponseDto response = subjectService.createSubject(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.ok("Subject created successfully", response));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<SubjectResponseDto>>> getAllSubjects() {

        List<SubjectResponseDto> response = subjectService.getAllSubjects();

        return ResponseEntity.ok(
                ApiResponse.ok("Subjects fetched successfully", response)
        );
    }

    @GetMapping("/{subjectCode}")
    public ResponseEntity<ApiResponse<SubjectResponseDto>> getSubjectByCode(
            @PathVariable Integer subjectCode) {

        SubjectResponseDto response = subjectService.getSubjectByCode(subjectCode);

        return ResponseEntity.ok(
                ApiResponse.ok("Subject fetched successfully", response)
        );
    }

    @PutMapping("/{subjectCode}")
    public ResponseEntity<ApiResponse<SubjectResponseDto>> updateSubject(
            @PathVariable Integer subjectCode,
            @Valid @RequestBody SubjectRequestDto request) {

        SubjectResponseDto response =
                subjectService.updateSubject(subjectCode, request);

        return ResponseEntity.ok(
                ApiResponse.ok("Subject updated successfully", response)
        );
    }

    @DeleteMapping("/{subjectCode}")
    public ResponseEntity<ApiResponse<String>> deleteSubject(
            @PathVariable Integer subjectCode) {

        subjectService.deleteSubject(subjectCode);

        return ResponseEntity.ok(
                ApiResponse.ok("Subject deleted successfully", null)
        );
    }
}