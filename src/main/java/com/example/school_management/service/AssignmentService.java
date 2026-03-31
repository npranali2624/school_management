package com.example.school_management.service;

import com.example.school_management.dto.AssignmentRequestDto;
import com.example.school_management.dto.AssignmentResponseDto;

import java.util.List;

public interface AssignmentService {

    AssignmentResponseDto createAssignment(AssignmentRequestDto requestDto);

    AssignmentResponseDto updateAssignment(Long id, AssignmentRequestDto requestDto);

    AssignmentResponseDto getAssignmentById(Long id);

    List<AssignmentResponseDto> getAllAssignments();

    void deleteAssignment(Long id);

    AssignmentResponseDto publishAssignment(Long id);

    AssignmentResponseDto cancelAssignment(Long id);
}