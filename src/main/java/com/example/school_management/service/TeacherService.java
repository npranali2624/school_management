package com.example.school_management.service;

import com.example.school_management.dto.TeacherRequestDto;
import com.example.school_management.dto.TeacherResponseDto;

import java.util.List;

public interface TeacherService {

    TeacherResponseDto createTeacher(TeacherRequestDto request);

    TeacherResponseDto getTeacherById(Long id);

    List<TeacherResponseDto> getAllTeachers();

    TeacherResponseDto updateTeacher(Long id, TeacherRequestDto request);

    void deleteTeacher(Long id);
}