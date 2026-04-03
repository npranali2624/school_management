package com.example.school_management.Mapper;

import com.example.school_management.dto.ComplaintRequestDto;
import com.example.school_management.dto.ComplaintResponseDto;
import com.example.school_management.entity.Complaint;
import com.example.school_management.entity.Student;
import com.example.school_management.entity.Teacher;
import com.example.school_management.enums.ComplaintStatus;
import com.example.school_management.enums.Priority;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ComplaintMapper {

    // ComplaintRequestDto → New Complaint entity (for create)
    public Complaint toEntity(ComplaintRequestDto dto, Student student, Teacher teacher) {
        Complaint complaint = Complaint.builder()
                .title(dto.getTitle())
                .description(dto.getDescription())
                .category(dto.getCategory())
                .priority(dto.getPriority() != null ? dto.getPriority() : Priority.MEDIUM)
                .supportedMedia(dto.getSupportedMedia())
                .status(ComplaintStatus.PENDING)
                .build();

        complaint.setStudent(student);
        complaint.setTeacher(teacher);
        return complaint;
    }

    // Complaint entity → ComplaintResponseDto
    public ComplaintResponseDto toResponseDto(Complaint c) {
        return ComplaintResponseDto.builder()
                .id(c.getId())
                .title(c.getTitle())
                .description(c.getDescription())
                .category(c.getCategory())
                .status(c.getStatus())
                .priority(c.getPriority())
                .supportedMedia(c.getSupportedMedia())
                .resolutionComment(c.getResolutionComment())
                .resolvedBy(c.getResolvedBy())
                .resolvedAt(c.getResolvedAt())
                .studentId(c.getStudent() != null ? c.getStudent().getId() : null)
                .studentName(c.getStudent() != null ?
                        c.getStudent().getFirstName() + " " + c.getStudent().getLastName() : null)
                .teacherId(c.getTeacher() != null ? c.getTeacher().getId() : null)
                .teacherName(c.getTeacher() != null ?
                        c.getTeacher().getFirstName() + " " + c.getTeacher().getLastName() : null)
                .createdAt(c.getCreatedAt())
                .updatedAt(c.getUpdatedAt())
                .build();
    }

    // List<Complaint> → List<ComplaintResponseDto>
    public List<ComplaintResponseDto> toResponseDtoList(List<Complaint> complaints) {
        return complaints.stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());
    }
}