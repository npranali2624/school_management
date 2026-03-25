package com.example.school_management.controller;

import com.example.school_management.entity.Teacher;
import com.example.school_management.repo.TeacherRepo;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/teachers")
@RequiredArgsConstructor
public class TeacherController {

    private final TeacherRepo teacherRepository;
    private final PasswordEncoder passwordEncoder;

    @PostMapping
    public ResponseEntity<?> createTeacher(@Valid @RequestBody Teacher teacher) {
        try {
            teacher.setPassword(passwordEncoder.encode(teacher.getPassword()));
            Teacher savedTeacher = teacherRepository.save(teacher);
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