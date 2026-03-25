package com.example.school_management.service;

import com.example.school_management.dto.StudentRequestDto;
import com.example.school_management.dto.StudentResponseDto;

import java.util.List;

public interface StudentService {

    StudentResponseDto admitStudent(StudentRequestDto request);

    List<StudentResponseDto> getAllStudents();

    StudentResponseDto getStudentById(Long id);

    StudentResponseDto getStudentByRollNumber(String rollNumber);

    StudentResponseDto updateStudent(Long id, StudentRequestDto request);

    void toggleStudentStatus(Long id);

    void deleteStudent(Long id);
}