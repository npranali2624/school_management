package com.example.school_management.controller;

import com.example.school_management.dto.StudentRequestDto;
import com.example.school_management.dto.StudentResponseDto;
import com.example.school_management.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    // ── POST /api/students ───────────────────────
    // Admit a new student
    @PostMapping
    public ResponseEntity<StudentResponseDto> admitStudent(
            @Valid @RequestBody StudentRequestDto request) {

        StudentResponseDto response = studentService.admitStudent(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // ── GET /api/students ────────────────────────
    // Get all students
    @GetMapping
    public ResponseEntity<List<StudentResponseDto>> getAllStudents() {
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    // ── GET /api/students/{id} ───────────────────
    // Get student by ID
    @GetMapping("/{id}")
    public ResponseEntity<StudentResponseDto> getStudentById(
            @PathVariable Long id) {

        return ResponseEntity.ok(studentService.getStudentById(id));
    }

    // ── GET /api/students/roll/{rollNumber} ──────
    // Get student by roll number
    @GetMapping("/roll/{rollNumber}")
    public ResponseEntity<StudentResponseDto> getStudentByRollNumber(
            @PathVariable Integer rollNumber) {

        return ResponseEntity.ok(studentService.getStudentByRollNumber(rollNumber));
    }

    // ── PUT /api/students/{id} ───────────────────
    // Update student details
    @PutMapping("/{id}")
    public ResponseEntity<StudentResponseDto> updateStudent(
            @PathVariable Long id,
            @Valid @RequestBody StudentRequestDto request) {

        return ResponseEntity.ok(studentService.updateStudent(id, request));
    }

    // ── PATCH /api/students/{id}/status ─────────
    // Toggle active/inactive status
    @PatchMapping("/{id}/status")
    public ResponseEntity<String> toggleStatus(@PathVariable Long id) {
        studentService.toggleStudentStatus(id);
        return ResponseEntity.ok("Student status updated successfully");
    }

    // ── DELETE /api/students/{id} ────────────────
    // Permanently delete student
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.ok("Student deleted successfully");
    }

    @GetMapping("/profile")
    public ResponseEntity<String> profile() {
        return ResponseEntity.ok("Student Profile");
    }
}
