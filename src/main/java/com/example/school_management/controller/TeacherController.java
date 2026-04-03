package com.example.school_management.controller;

import com.example.school_management.dto.ApiResponse;
import com.example.school_management.dto.TeacherRequestDto;
import com.example.school_management.dto.TeacherResponseDto;
import com.example.school_management.service.TeacherService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teachers")
@RequiredArgsConstructor
public class TeacherController {

    private final TeacherService teacherService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<?>> createTeacher(
            @Valid @RequestBody TeacherRequestDto request) {
        try {
            TeacherResponseDto saved = teacherService.createTeacher(request);
            return ResponseEntity.ok(
                    ApiResponse.ok("Teacher created successfully", saved)
            );
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.badRequest().body(
                    ApiResponse.error("Something went wrong: "
                            + e.getMostSpecificCause().getMessage())
            );
        }
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','TEACHER')")
    public ResponseEntity<ApiResponse<TeacherResponseDto>> getTeacherById(
            @PathVariable Long id) {
        return ResponseEntity.ok(
                ApiResponse.ok("Fetched", teacherService.getTeacherById(id))
        );
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN','TEACHER')")
    public ResponseEntity<ApiResponse<List<TeacherResponseDto>>> getAllTeachers() {
        return ResponseEntity.ok(
                ApiResponse.ok("Fetched", teacherService.getAllTeachers())
        );
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<TeacherResponseDto>> updateTeacher(
            @PathVariable Long id,
            @Valid @RequestBody TeacherRequestDto request) {
        return ResponseEntity.ok(
                ApiResponse.ok("Updated", teacherService.updateTeacher(id, request))
        );
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<?>> deleteTeacher(@PathVariable Long id) {
        teacherService.deleteTeacher(id);
        return ResponseEntity.ok(ApiResponse.ok("Deleted", null));
    }

    @GetMapping("/dashboard")
    @PreAuthorize("hasRole('TEACHER')")
    public ResponseEntity<ApiResponse<String>> teacherDashboard() {
        return ResponseEntity.ok(
                ApiResponse.ok("Dashboard", "Teacher Dashboard")
        );
    }

    @GetMapping("/{id}/degree-type")
    public ResponseEntity<ApiResponse<String>> getDegreeTypeByTeacherId(
            @PathVariable Long id) {
        try {
            TeacherResponseDto teacher = teacherService.getTeacherById(id);
            return ResponseEntity.ok(
                    ApiResponse.ok("Degree type fetched successfully",
                            teacher.getDegreeType() != null
                                    ? teacher.getDegreeType().name() : null)
            );
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(
                    ApiResponse.error(e.getMessage())
            );
        }
    }
}