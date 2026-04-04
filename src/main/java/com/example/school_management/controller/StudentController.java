package com.example.school_management.controller;

import com.example.school_management.dto.ApiResponse;
import com.example.school_management.dto.StudentRequestDto;
import com.example.school_management.dto.StudentResponseDto;
import com.example.school_management.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    // 🔐 ADMIN only (admit student)
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<StudentResponseDto>> admitStudent(
            @Valid @RequestBody StudentRequestDto request) {

        StudentResponseDto response = studentService.admitStudent(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.ok("Student admitted successfully", response));
    }

    // 🔐 ADMIN + TEACHER
    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN','TEACHER')")
    public ResponseEntity<ApiResponse<List<StudentResponseDto>>> getAllStudents() {

        List<StudentResponseDto> students = studentService.getAllStudents();

        return ResponseEntity.ok(
                ApiResponse.ok("Students fetched successfully", students)
        );
    }

    // 🔐 ADMIN + TEACHER
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','TEACHER')")
    public ResponseEntity<ApiResponse<StudentResponseDto>> getStudentById(
            @PathVariable Long id) {

        StudentResponseDto student = studentService.getStudentById(id);

        return ResponseEntity.ok(
                ApiResponse.ok("Student fetched successfully", student)
        );
    }

    // 🔐 ADMIN + TEACHER
    @GetMapping("/roll/{rollNumber}")
    @PreAuthorize("hasAnyRole('ADMIN','TEACHER')")
    public ResponseEntity<ApiResponse<StudentResponseDto>> getStudentByRollNumber(
            @PathVariable Integer rollNumber) {

        StudentResponseDto student = studentService.getStudentByRollNumber(rollNumber);

        return ResponseEntity.ok(
                ApiResponse.ok("Student fetched successfully", student)
        );
    }

    // 🔐 ADMIN only
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<StudentResponseDto>> updateStudent(
            @PathVariable Long id,
            @Valid @RequestBody StudentRequestDto request) {

        StudentResponseDto updated = studentService.updateStudent(id, request);

        return ResponseEntity.ok(
                ApiResponse.ok("Student updated successfully", updated)
        );
    }

    // 🔐 ADMIN only
    @PatchMapping("/{id}/status")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<String>> toggleStatus(@PathVariable Long id) {

        studentService.toggleStudentStatus(id);

        return ResponseEntity.ok(
                ApiResponse.ok("Student status updated successfully", null)
        );
    }

    // 🔐 ADMIN only
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<String>> deleteStudent(@PathVariable Long id) {

        studentService.deleteStudent(id);

        return ResponseEntity.ok(
                ApiResponse.ok("Student deleted successfully", null)
        );
    }

    // 🔐 STUDENT + PARENT
    @GetMapping("/profile")
    @PreAuthorize("hasAnyRole('STUDENT','PARENT')")
    public ResponseEntity<ApiResponse<String>> profile() {

        return ResponseEntity.ok(
                ApiResponse.ok("Student profile fetched", "Student Profile")
        );
    }

    // 🔐 STUDENT + PARENT
    @GetMapping("/dashboard")
    @PreAuthorize("hasAnyRole('STUDENT','PARENT')")
    public ResponseEntity<ApiResponse<String>> studentDashboard() {

        return ResponseEntity.ok(
                ApiResponse.ok("Dashboard fetched", "Student & Parent Dashboard")
        );
    }

    // 🔐 ADMIN + TEACHER + STUDENT
    @GetMapping("/{id}/blood-group")
    @PreAuthorize("hasAnyRole('ADMIN','TEACHER','STUDENT')")
    public ResponseEntity<ApiResponse<String>> getBloodGroupByStudentId(
            @PathVariable Long id) {

        StudentResponseDto student = studentService.getStudentById(id);

        return ResponseEntity.ok(
                ApiResponse.ok("Blood group fetched successfully",
                        student.getBloodGroup() != null ? student.getBloodGroup().name() : null)
        );
    }

    // 🔐 ADMIN + TEACHER + STUDENT
    @GetMapping("/{id}/religion")
    @PreAuthorize("hasAnyRole('ADMIN','TEACHER','STUDENT')")
    public ResponseEntity<ApiResponse<String>> getReligionByStudentId(
            @PathVariable Long id) {

        StudentResponseDto student = studentService.getStudentById(id);

        return ResponseEntity.ok(
                ApiResponse.ok("Religion fetched successfully",
                        student.getReligion() != null ? student.getReligion().name() : null)
        );
    }

    // 🔐 ADMIN + TEACHER + STUDENT
    @GetMapping("/{id}/category")
    @PreAuthorize("hasAnyRole('ADMIN','TEACHER','STUDENT')")
    public ResponseEntity<ApiResponse<String>> getCategoryByStudentId(
            @PathVariable Long id) {

        StudentResponseDto student = studentService.getStudentById(id);

        return ResponseEntity.ok(
                ApiResponse.ok("Category fetched successfully",
                        student.getCategory() != null ? student.getCategory().name() : null)
        );
    }

    // 🔐 ADMIN + TEACHER + STUDENT
    @GetMapping("/{id}/standard")
    @PreAuthorize("hasAnyRole('ADMIN','TEACHER','STUDENT')")
    public ResponseEntity<ApiResponse<String>> getStandardByStudentId(
            @PathVariable Long id) {

        StudentResponseDto student = studentService.getStudentById(id);

        return ResponseEntity.ok(
                ApiResponse.ok("Standard fetched successfully",
                        student.getStandard() != null ? student.getStandard().name() : null)
        );
    }

    // 🔐 ADMIN + TEACHER + STUDENT
    @GetMapping("/{id}/division")
    @PreAuthorize("hasAnyRole('ADMIN','TEACHER','STUDENT')")
    public ResponseEntity<ApiResponse<String>> getDivisionByStudentId(
            @PathVariable Long id) {

        StudentResponseDto student = studentService.getStudentById(id);

        return ResponseEntity.ok(
                ApiResponse.ok("Division fetched successfully",
                        student.getDivision() != null ? student.getDivision().name() : null)
        );
    }
}