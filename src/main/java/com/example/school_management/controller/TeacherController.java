package com.example.school_management.controller;

import com.example.school_management.dto.ApiResponse;
import com.example.school_management.dto.SubjectResponseDto;
import com.example.school_management.dto.TeacherRequestDto;
import com.example.school_management.dto.TeacherResponseDto;
import com.example.school_management.entity.Class;
import com.example.school_management.service.ClassService;
import com.example.school_management.service.SubjectService;
import com.example.school_management.service.TeacherService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/teachers")
@RequiredArgsConstructor
public class TeacherController {

    private final TeacherService teacherService;
    private final SubjectService subjectService;
    private final ClassService classService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<?>> createTeacher(
            @Valid @RequestBody TeacherRequestDto request) {
        try {
            TeacherResponseDto savedTeacher = teacherService.createTeacher(request);
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
        TeacherResponseDto teacher = teacherService.getTeacherById(id);
        return ResponseEntity.ok(ApiResponse.ok("Fetched", teacher));
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
    public ResponseEntity<ApiResponse<?>> updateTeacher(
            @PathVariable Long id,
            @Valid @RequestBody TeacherRequestDto request) {
        return ResponseEntity.ok(
                ApiResponse.ok("Updated",
                        teacherService.updateTeacher(id, request))
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
            String degreeType = teacher.getDegreeType() != null
                    ? teacher.getDegreeType().name()
                    : null;
            return ResponseEntity.ok(
                    ApiResponse.ok("Degree type fetched successfully", degreeType)
            );
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(
                    ApiResponse.error(e.getMessage())
            );
        }
    }

    @GetMapping("/subjects")
    @PreAuthorize("hasAnyRole('ADMIN','TEACHER')")
    public ResponseEntity<ApiResponse<List<SubjectResponseDto>>> getAllSubjects() {
        return ResponseEntity.ok(
                ApiResponse.ok("Fetched all subjects", subjectService.getAllSubjects())
        );
    }

    @GetMapping("/subject/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','TEACHER')")
    public ResponseEntity<ApiResponse<?>> getSubjectById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(
                    ApiResponse.ok("Fetched", subjectService.getSubjectById(id))
            );
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(ApiResponse.error(e.getMessage()));
        }
    }

    @GetMapping("/specializations")
    @PreAuthorize("hasAnyRole('ADMIN','TEACHER')")
    public ResponseEntity<ApiResponse<List<SubjectResponseDto>>> getAllSpecializations() {
        return ResponseEntity.ok(
                ApiResponse.ok("Fetched all specializations", subjectService.getAllSubjects())
        );
    }

    @GetMapping("/specializations/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','TEACHER')")
    public ResponseEntity<ApiResponse<?>> getSpecializationById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(
                    ApiResponse.ok("Fetched", subjectService.getSubjectById(id))
            );
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(ApiResponse.error(e.getMessage()));
        }
    }

    @GetMapping("/classes")
    @PreAuthorize("hasAnyRole('ADMIN','TEACHER')")
    public ResponseEntity<ApiResponse<List<Class>>> getAllClasses() {
        return ResponseEntity.ok(
                ApiResponse.ok("Fetched all classes", classService.getAllClasses())
        );
    }

    @GetMapping("/class/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','TEACHER')")
    public ResponseEntity<ApiResponse<?>> getClassById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(
                    ApiResponse.ok("Fetched", classService.getClassById(id))
            );
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(ApiResponse.error(e.getMessage()));
        }
    }
}