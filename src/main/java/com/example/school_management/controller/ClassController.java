package com.example.school_management.controller;

import com.example.school_management.dto.ApiResponse;
import com.example.school_management.entity.Class;
import com.example.school_management.service.ClassService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/classes")
@RequiredArgsConstructor
public class ClassController {

    private final ClassService classService;

    @PostMapping
    public ResponseEntity<ApiResponse<Class>> createClass(
            @Valid @RequestBody Class classEntity) {

        Class response = classService.createClass(classEntity);

        return ResponseEntity.ok(
                ApiResponse.ok("Class created successfully", response)
        );
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Class>>> getAllClasses() {

        List<Class> response = classService.getAllClasses();

        return ResponseEntity.ok(
                ApiResponse.ok("Classes fetched successfully", response)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Class>> getClassById(
            @PathVariable Long id) {

        Class response = classService.getClassById(id);

        return ResponseEntity.ok(
                ApiResponse.ok("Class fetched successfully", response)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Class>> updateClass(
            @PathVariable Long id,
            @Valid @RequestBody Class classEntity) {

        Class response = classService.updateClass(id, classEntity);

        return ResponseEntity.ok(
                ApiResponse.ok("Class updated successfully", response)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteClass(
            @PathVariable Long id) {

        classService.deleteClass(id);

        return ResponseEntity.ok(
                ApiResponse.ok("Class deleted successfully", null)
        );
    }
}