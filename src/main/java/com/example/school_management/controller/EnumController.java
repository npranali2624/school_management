package com.example.school_management.controller;

import com.example.school_management.enums.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/api/enums")
public class EnumController {


    @GetMapping("/subject-types")
    public ResponseEntity<List<Map<String, String>>> getSubjectTypes() {
        List<Map<String, String>> list = new ArrayList<>();
        for (SubjectType type : SubjectType.values()) {
            Map<String, String> map = new LinkedHashMap<>();
            map.put("value", type.name());
            map.put("displayName", type.name());
            list.add(map);
        }
        return ResponseEntity.ok(list);
    }


    @GetMapping("/religions")
    public ResponseEntity<List<Map<String, String>>> getReligions() {
        List<Map<String, String>> list = new ArrayList<>();
        for (Religion religion : Religion.values()) {
            Map<String, String> map = new LinkedHashMap<>();
            map.put("value", religion.name());
            map.put("displayName", religion.getDisplayName());
            list.add(map);
        }
        return ResponseEntity.ok(list);
    }

    @GetMapping("/genders")
    public ResponseEntity<List<Map<String, String>>> getGenders() {
        List<Map<String, String>> list = new ArrayList<>();
        for (Gender gender : Gender.values()) {
            Map<String, String> map = new LinkedHashMap<>();
            map.put("value", gender.name());
            map.put("displayName", gender.name());
            list.add(map);
        }
        return ResponseEntity.ok(list);
    }


    @GetMapping("/divisions")
    public ResponseEntity<List<Map<String, String>>> getDivisions() {
        List<Map<String, String>> list = new ArrayList<>();
        for (Division division : Division.values()) {
            Map<String, String> map = new LinkedHashMap<>();
            map.put("value", division.name());
            map.put("displayName", division.name());
            list.add(map);
        }
        return ResponseEntity.ok(list);
    }

    @GetMapping("/degree-types")
    public ResponseEntity<List<Map<String, String>>> getDegreeTypes() {
        List<Map<String, String>> list = new ArrayList<>();
        for (DegreeType degreeType : DegreeType.values()) {
            Map<String, String> map = new LinkedHashMap<>();
            map.put("value", degreeType.name());
            map.put("displayName", degreeType.getDisplayName());
            list.add(map);
        }
        return ResponseEntity.ok(list);
    }

    @GetMapping("/categories")
    public ResponseEntity<List<Map<String, String>>> getCategories() {
        List<Map<String, String>> list = new ArrayList<>();
        for (Category category : Category.values()) {
            Map<String, String> map = new LinkedHashMap<>();
            map.put("value", category.name());
            map.put("displayName", category.getDisplayName());
            list.add(map);
        }
        return ResponseEntity.ok(list);
    }


    @GetMapping("/standards")
    public ResponseEntity<List<Map<String, String>>> getStandards() {
        List<Map<String, String>> list = new ArrayList<>();
        for (Standard standard : Standard.values()) {
            Map<String, String> map = new LinkedHashMap<>();
            map.put("value", standard.getValue());
            map.put("displayName", standard.getValue());
            list.add(map);
        }
        return ResponseEntity.ok(list);
    }


    @GetMapping("/blood-groups")
    public ResponseEntity<List<Map<String, String>>> getBloodGroups() {
        List<Map<String, String>> list = new ArrayList<>();
        for (BloodGroup bloodGroup : BloodGroup.values()) {
            Map<String, String> map = new LinkedHashMap<>();
            map.put("value", bloodGroup.name());
            map.put("displayName", bloodGroup.getDisplayName());
            list.add(map);
        }
        return ResponseEntity.ok(list);
    }
}