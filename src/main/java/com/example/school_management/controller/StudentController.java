package com.example.school_management.controller;

import com.example.school_management.dto.ApiResponse;
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

    @PostMapping
    public ResponseEntity<ApiResponse<StudentResponseDto>> admitStudent(
            @Valid @RequestBody StudentRequestDto request) {

        StudentResponseDto response = studentService.admitStudent(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.ok("Student admitted successfully", response));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<StudentResponseDto>>> getAllStudents() {

        List<StudentResponseDto> students = studentService.getAllStudents();

        return ResponseEntity.ok(
                ApiResponse.ok("Students fetched successfully", students)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<StudentResponseDto>> getStudentById(
            @PathVariable Long id) {

        StudentResponseDto student = studentService.getStudentById(id);

        return ResponseEntity.ok(
                ApiResponse.ok("Student fetched successfully", student)
        );
    }

    @GetMapping("/roll/{rollNumber}")
    public ResponseEntity<ApiResponse<StudentResponseDto>> getStudentByRollNumber(
            @PathVariable Integer rollNumber) {

        StudentResponseDto student = studentService.getStudentByRollNumber(rollNumber);

        return ResponseEntity.ok(
                ApiResponse.ok("Student fetched successfully", student)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<StudentResponseDto>> updateStudent(
            @PathVariable Long id,
            @Valid @RequestBody StudentRequestDto request) {

        StudentResponseDto updated = studentService.updateStudent(id, request);

        return ResponseEntity.ok(
                ApiResponse.ok("Student updated successfully", updated)
        );
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<ApiResponse<String>> toggleStatus(@PathVariable Long id) {

        studentService.toggleStudentStatus(id);

        return ResponseEntity.ok(
                ApiResponse.ok("Student status updated successfully", null)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteStudent(@PathVariable Long id) {

        studentService.deleteStudent(id);

        return ResponseEntity.ok(
                ApiResponse.ok("Student deleted successfully", null)
        );
    }

    @GetMapping("/profile")
    public ResponseEntity<ApiResponse<String>> profile() {

        return ResponseEntity.ok(
                ApiResponse.ok("Student profile fetched", "Student Profile")
        );
    }
    @GetMapping("/dashboard")
    public ResponseEntity<ApiResponse<String>> studentDashboard() {

        return ResponseEntity.ok(
                ApiResponse.ok("Dashboard fetched", "Student & Parent Dashboard")
        );
    }
}