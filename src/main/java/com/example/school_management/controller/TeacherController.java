package com.example.school_management.controller;

import com.example.school_management.entity.Teacher;
import com.example.school_management.service.TeacherService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/teachers")
@RequiredArgsConstructor
public class TeacherController {

    private final TeacherService teacherService;

    @PostMapping
    public ResponseEntity<?> createTeacher(@Valid @RequestBody Teacher teacher) {
        try {
            Teacher savedTeacher = teacherService.createTeacher(teacher);
            return ResponseEntity.ok(savedTeacher);
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity
                    .badRequest()
                    .body("Something went wrong while saving: " + e.getMostSpecificCause().getMessage());
        }
    }

    @GetMapping("/dashboard")
    public String teacherDashboard() {
        return "Teacher Dashboard";
    }
}