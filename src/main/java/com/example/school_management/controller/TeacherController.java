package com.example.school_management.controller;

import com.example.school_management.dto.ApiResponse;
import com.example.school_management.entity.Teacher;
import com.example.school_management.service.TeacherService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teachers")
@RequiredArgsConstructor
public class TeacherController {

    private final TeacherService teacherService;

    @PostMapping
    public ResponseEntity<ApiResponse<?>> createTeacher(
            @Valid @RequestBody Teacher teacher) {
        try {
            Teacher savedTeacher = teacherService.createTeacher(teacher);
            return ResponseEntity.ok(
                    ApiResponse.ok("Teacher created successfully", savedTeacher)
            );
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.badRequest().body(
                    ApiResponse.error("Something went wrong while saving: "
                            + e.getMostSpecificCause().getMessage())
            );
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> getTeacherById(
            @PathVariable Long id) {
        try {
            Teacher teacher = teacherService.getTeacherById(id);
            return ResponseEntity.ok(
                    ApiResponse.ok("Teacher fetched successfully", teacher)
            );
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(
                    ApiResponse.error(e.getMessage())
            );
        }
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Teacher>>> getAllTeachers() {

        List<Teacher> teachers = teacherService.getAllTeachers();

        return ResponseEntity.ok(
                ApiResponse.ok("Teachers fetched successfully", teachers)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> updateTeacher(
            @PathVariable Long id,
            @Valid @RequestBody Teacher teacher) {
        try {
            Teacher updated = teacherService.updateTeacher(id, teacher);
            return ResponseEntity.ok(
                    ApiResponse.ok("Teacher updated successfully", updated)
            );
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.badRequest().body(
                    ApiResponse.error("Something went wrong while updating: "
                            + e.getMostSpecificCause().getMessage())
            );
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(
                    ApiResponse.error(e.getMessage())
            );
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> deleteTeacher(
            @PathVariable Long id) {
        try {
            teacherService.deleteTeacher(id);
            return ResponseEntity.ok(
                    ApiResponse.ok("Teacher deleted successfully", null)
            );
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(
                    ApiResponse.error(e.getMessage())
            );
        }
    }

    @GetMapping("/dashboard")
    public ResponseEntity<ApiResponse<String>> teacherDashboard() {
        return ResponseEntity.ok(
                ApiResponse.ok("Dashboard fetched", "Teacher Dashboard")
        );
    }
}