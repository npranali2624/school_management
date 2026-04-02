package com.example.school_management.controller;

import com.example.school_management.dto.ApiResponse;
import com.example.school_management.entity.Teacher;
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
            @Valid @RequestBody Teacher teacher) {
        try {
            Teacher savedTeacher = teacherService.createTeacher(teacher);
            return ResponseEntity.ok(
                    ApiResponse.ok("Teacher created successfully", savedTeacher)
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
    public ResponseEntity<ApiResponse<?>> getTeacherById(@PathVariable Long id) {
        Teacher teacher = teacherService.getTeacherById(id);
        return ResponseEntity.ok(ApiResponse.ok("Fetched", teacher));
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN','TEACHER')")
    public ResponseEntity<ApiResponse<List<Teacher>>> getAllTeachers() {
        return ResponseEntity.ok(
                ApiResponse.ok("Fetched", teacherService.getAllTeachers())
        );
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<?>> updateTeacher(
            @PathVariable Long id,
            @RequestBody Teacher teacher) {
        return ResponseEntity.ok(
                ApiResponse.ok("Updated",
                        teacherService.updateTeacher(id, teacher))
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
            Teacher teacher = teacherService.getTeacherById(id);
            return ResponseEntity.ok(
                    ApiResponse.ok("Degree type fetched successfully",
                            teacher.getDegreeType() != null ? teacher.getDegreeType().name() : null)
            );
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(
                    ApiResponse.error(e.getMessage())
            );
        }
    }
}