package com.example.school_management.controller;

import com.example.school_management.entity.Class;
import com.example.school_management.service.ClassService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/classes")
@RequiredArgsConstructor
public class ClassController {

    private final ClassService classService;

    @PostMapping
    public Class createClass(@Valid @RequestBody Class classEntity) {
        return classService.createClass(classEntity);
    }

    @GetMapping
    public List<Class> getAllClasses() {
        return classService.getAllClasses();
    }

    @GetMapping("/{id}")
    public Class getClassById(@PathVariable Long id) {
        return classService.getClassById(id);
    }

    @PutMapping("/{id}")
    public Class updateClass(@PathVariable Long id,
                             @Valid @RequestBody Class classEntity) {
        return classService.updateClass(id, classEntity);
    }

    @DeleteMapping("/{id}")
    public String deleteClass(@PathVariable Long id) {
        classService.deleteClass(id);
        return "Class deleted successfully";
    }
}