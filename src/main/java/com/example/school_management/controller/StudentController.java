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
@RequestMapping("/api/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;


    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<StudentResponseDto>> admitStudent(
            @Valid @RequestBody StudentRequestDto request) {

        StudentResponseDto response = studentService.admitStudent(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.ok("Student admitted successfully", response));
    }


    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN','TEACHER')")
    public ResponseEntity<ApiResponse<List<StudentResponseDto>>> getAllStudents() {

        List<StudentResponseDto> students = studentService.getAllStudents();

        return ResponseEntity.ok(
                ApiResponse.ok("Students fetched successfully", students)
        );
    }


    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','TEACHER')")
    public ResponseEntity<ApiResponse<StudentResponseDto>> getStudentById(
            @PathVariable Long id) {

        StudentResponseDto student = studentService.getStudentById(id);

        return ResponseEntity.ok(
                ApiResponse.ok("Student fetched successfully", student)
        );
    }


    @GetMapping("/roll/{rollNumber}")
    @PreAuthorize("hasAnyRole('ADMIN','TEACHER')")
    public ResponseEntity<ApiResponse<StudentResponseDto>> getStudentByRollNumber(
            @PathVariable Integer rollNumber) {

        StudentResponseDto student = studentService.getStudentByRollNumber(rollNumber);

        return ResponseEntity.ok(
                ApiResponse.ok("Student fetched successfully", student)
        );
    }


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


    @PatchMapping("/{id}/status")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<String>> toggleStatus(@PathVariable Long id) {

        studentService.toggleStudentStatus(id);

        return ResponseEntity.ok(
                ApiResponse.ok("Student status updated successfully", null)
        );
    }


    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<String>> deleteStudent(@PathVariable Long id) {

        studentService.deleteStudent(id);

        return ResponseEntity.ok(
                ApiResponse.ok("Student deleted successfully", null)
        );
    }


    @GetMapping("/profile")
    @PreAuthorize("hasAnyRole('STUDENT','PARENT')")
    public ResponseEntity<ApiResponse<String>> profile() {

        return ResponseEntity.ok(
                ApiResponse.ok("Student profile fetched", "Student Profile")
        );
    }


    @GetMapping("/dashboard")
    @PreAuthorize("hasAnyRole('STUDENT','PARENT')")
    public ResponseEntity<ApiResponse<String>> studentDashboard() {

        return ResponseEntity.ok(
                ApiResponse.ok("Dashboard fetched", "Student & Parent Dashboard")
        );
    }


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