package com.example.school_management.service.Impl;

import com.example.school_management.dto.ComplaintRequestDto;
import com.example.school_management.dto.ComplaintResponseDto;
import com.example.school_management.entity.Complaint;
import com.example.school_management.entity.Student;
import com.example.school_management.entity.Teacher;
import com.example.school_management.enums.ComplaintCategory;
import com.example.school_management.enums.ComplaintStatus;
import com.example.school_management.enums.Priority;
import com.example.school_management.repo.ComplaintRepository;
import com.example.school_management.repo.StudentRepository;
import com.example.school_management.repo.TeacherRepo;
import com.example.school_management.service.ComplaintService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ComplaintServiceImpl implements ComplaintService {

    private final ComplaintRepository complaintRepository;
    private final StudentRepository studentRepository;
    private final TeacherRepo teacherRepository;

    @Override
    public ComplaintResponseDto createComplaint(ComplaintRequestDto request) {

        Complaint complaint = Complaint.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .category(request.getCategory())
                .priority(request.getPriority() != null
                        ? request.getPriority() : Priority.MEDIUM)
                .supportedMedia(request.getSupportedMedia())
                .status(ComplaintStatus.PENDING)
                .build();

        if (request.getStudentId() != null) {
            Student student = studentRepository.findById(request.getStudentId())
                    .orElseThrow(() -> new RuntimeException("Student not found"));
            complaint.setStudent(student);
        }


        if (request.getTeacherId() != null) {
            Teacher teacher = teacherRepository.findById(request.getTeacherId())
                    .orElseThrow(() -> new RuntimeException("Teacher not found"));
            complaint.setTeacher(teacher);
        }

        return mapToResponse(complaintRepository.save(complaint));
    }

    @Override
    public List<ComplaintResponseDto> getAllComplaints() {
        return complaintRepository.findAll()
                .stream().map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ComplaintResponseDto getComplaintById(Long id) {
        return mapToResponse(complaintRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Complaint not found")));
    }

    @Override
    public List<ComplaintResponseDto> getComplaintsByStudent(Long studentId) {
        return complaintRepository.findByStudentId(studentId)
                .stream().map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<ComplaintResponseDto> getComplaintsByTeacher(Long teacherId) {
        return complaintRepository.findByTeacherId(teacherId)
                .stream().map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<ComplaintResponseDto> getComplaintsByStatus(ComplaintStatus status) {
        return complaintRepository.findByStatus(status)
                .stream().map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<ComplaintResponseDto> getComplaintsByPriority(Priority priority) {
        return complaintRepository.findByPriority(priority)
                .stream().map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<ComplaintResponseDto> getComplaintsByCategory(ComplaintCategory category) {
        return complaintRepository.findByCategory(category)
                .stream().map(this::mapToResponse)
                .collect(Collectors.toList());
    }
    @Override
    public ComplaintResponseDto updateStatus(Long id, ComplaintStatus status) {
        Complaint complaint = complaintRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Complaint not found"));
        complaint.setStatus(status);
        return mapToResponse(complaintRepository.save(complaint));
    }

    @Override
    public ComplaintResponseDto resolveComplaint(Long id, String resolutionComment,
                                                 String resolvedBy) {
        Complaint complaint = complaintRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Complaint not found"));
        complaint.setStatus(ComplaintStatus.RESOLVED);
        complaint.setResolutionComment(resolutionComment);
        complaint.setResolvedBy(resolvedBy);
        complaint.setResolvedAt(Instant.now());
        return mapToResponse(complaintRepository.save(complaint));
    }

    @Override
    public void deleteComplaint(Long id) {
        if (!complaintRepository.existsById(id))
            throw new RuntimeException("Complaint not found");
        complaintRepository.deleteById(id);
    }

    private ComplaintResponseDto mapToResponse(Complaint c) {
        return ComplaintResponseDto.builder()
                .id(c.getId())
                .title(c.getTitle())
                .description(c.getDescription())
                .category(c.getCategory())
                .status(c.getStatus())
                .priority(c.getPriority())
                .supportedMedia(c.getSupportedMedia())        // ← fixed: removed extra (
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
}