package com.example.school_management.service;

import com.example.school_management.dto.ComplaintRequestDto;
import com.example.school_management.dto.ComplaintResponseDto;
import com.example.school_management.enums.ComplaintCategory;
import com.example.school_management.enums.ComplaintStatus;
import com.example.school_management.enums.Priority;
import java.util.List;

public interface ComplaintService {

    // Create complaint
    ComplaintResponseDto createComplaint(ComplaintRequestDto request);

    // Get all complaints
    List<ComplaintResponseDto> getAllComplaints();

    // Get by ID
    ComplaintResponseDto getComplaintById(Long id);

    // Get by student
    List<ComplaintResponseDto> getComplaintsByStudent(Long studentId);

    // Get by teacher
    List<ComplaintResponseDto> getComplaintsByTeacher(Long teacherId);

    // Get by status
    List<ComplaintResponseDto> getComplaintsByStatus(ComplaintStatus status);

    // Get by priority
    List<ComplaintResponseDto> getComplaintsByPriority(Priority priority);

    // Get by category
    List<ComplaintResponseDto> getComplaintsByCategory(ComplaintCategory category);

    // Update status
    ComplaintResponseDto updateStatus(Long id, ComplaintStatus status);

    // Resolve complaint
    ComplaintResponseDto resolveComplaint(Long id, String resolutionComment, String resolvedBy);

    // Delete complaint
    void deleteComplaint(Long id);
}