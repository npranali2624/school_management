package com.example.school_management.Mapper;

import com.example.school_management.dto.AssignmentRequestDto;
import com.example.school_management.dto.AssignmentResponseDto;
import com.example.school_management.entity.Assignment;
import com.example.school_management.entity.Class;
import com.example.school_management.entity.Subject;
import com.example.school_management.entity.Teacher;
import com.example.school_management.enums.AssignmentStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class AssignmentMapper {

    public Assignment toEntity(AssignmentRequestDto dto, Teacher teacher, Class assignedClass, Subject subject) {
        Assignment assignment = new Assignment();
        applyDtoToEntity(dto, assignment, teacher, assignedClass, subject);
        assignment.setAssignedDate(LocalDateTime.now());
        assignment.setStatus(AssignmentStatus.DRAFT);
        return assignment;
    }

    public void updateEntity(AssignmentRequestDto dto, Assignment assignment, Teacher teacher, Class assignedClass, Subject subject) {
        applyDtoToEntity(dto, assignment, teacher, assignedClass, subject);
    }

    public AssignmentResponseDto toResponseDto(Assignment assignment) {
        AssignmentResponseDto dto = new AssignmentResponseDto();

        dto.setId(assignment.getId());
        dto.setTitle(assignment.getTitle());
        dto.setDescription(assignment.getDescription());
        dto.setAssignmentType(assignment.getAssignmentType());
        dto.setMarkingType(assignment.getMarkingType());
        dto.setTotalMarks(assignment.getTotalMarks());
        dto.setPassingMarks(assignment.getPassingMarks());
        dto.setAssignedDate(assignment.getAssignedDate());
        dto.setDueDate(assignment.getDueDate());
        dto.setStatus(assignment.getStatus());
        dto.setAllowLateSubmission(assignment.getAllowLateSubmission());
        dto.setLateSubmissionDueDate(assignment.getLateSubmissionDueDate());
        dto.setAllowedFileTypes(assignment.getAllowedFileTypes());

        if (assignment.getTeacher() != null) {
            dto.setTeacherId(assignment.getTeacher().getId());
            dto.setTeacherName(assignment.getTeacher().getFirstName() + " " + assignment.getTeacher().getLastName());
        }

        if (assignment.getAssignedClass() != null) {
            dto.setClassId(assignment.getAssignedClass().getId());
        }

        if (assignment.getSubject() != null) {
            dto.setSubjectId(assignment.getSubject().getId());
            dto.setSubjectName(assignment.getSubject().getSubjectName());
        }

        return dto;
    }

    public List<AssignmentResponseDto> toResponseDtoList(List<Assignment> assignments) {
        return assignments.stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());
    }


    private void applyDtoToEntity(AssignmentRequestDto dto, Assignment assignment,
                                  Teacher teacher, Class assignedClass, Subject subject) {
        assignment.setTitle(dto.getTitle());
        assignment.setDescription(dto.getDescription());
        assignment.setAssignmentType(dto.getAssignmentType());
        assignment.setMarkingType(dto.getMarkingType());
        assignment.setTotalMarks(dto.getTotalMarks());
        assignment.setPassingMarks(dto.getPassingMarks());
        assignment.setDueDate(dto.getDueDate());
        assignment.setAllowLateSubmission(dto.getAllowLateSubmission() != null ? dto.getAllowLateSubmission() : false);
        assignment.setLateSubmissionDueDate(Boolean.TRUE.equals(dto.getAllowLateSubmission()) ? dto.getLateSubmissionDueDate() : null);
        assignment.setAllowedFileTypes(dto.getAllowedFileTypes());
        assignment.setTeacher(teacher);
        assignment.setAssignedClass(assignedClass);
        assignment.setSubject(subject);
    }
}