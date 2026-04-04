package com.example.school_management.service.Impl;

import com.example.school_management.dto.ComplaintRequestDto;
import com.example.school_management.dto.ComplaintResponseDto;
import com.example.school_management.entity.Complaint;
import com.example.school_management.entity.Student;
import com.example.school_management.entity.Teacher;
import com.example.school_management.enums.ComplaintCategory;
import com.example.school_management.enums.ComplaintStatus;
import com.example.school_management.enums.Priority;
import com.example.school_management.Mapper.ComplaintMapper;
import com.example.school_management.repo.ComplaintRepository;
import com.example.school_management.repo.StudentRepository;
import com.example.school_management.repo.TeacherRepo;
import com.example.school_management.service.ComplaintService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ComplaintServiceImpl implements ComplaintService {

    private final ComplaintRepository complaintRepository;
    private final StudentRepository studentRepository;
    private final TeacherRepo teacherRepository;
    private final ComplaintMapper complaintMapper;

    @Override
    public ComplaintResponseDto createComplaint(ComplaintRequestDto request) {

        Student student = request.getStudentId() != null
                ? studentRepository.findById(request.getStudentId())
                .orElseThrow(() -> new RuntimeException("Student not found"))
                : null;

        Teacher teacher = request.getTeacherId() != null
                ? teacherRepository.findById(request.getTeacherId())
                .orElseThrow(() -> new RuntimeException("Teacher not found"))
                : null;

        return complaintMapper.toResponseDto(
                complaintRepository.save(complaintMapper.toEntity(request, student, teacher)));
    }

    @Override
    public List<ComplaintResponseDto> getAllComplaints() {
        return complaintMapper.toResponseDtoList(complaintRepository.findAll());
    }

    @Override
    public ComplaintResponseDto getComplaintById(Long id) {
        return complaintMapper.toResponseDto(findComplaintById(id));
    }

    @Override
    public List<ComplaintResponseDto> getComplaintsByStudent(Long studentId) {
        return complaintMapper.toResponseDtoList(complaintRepository.findByStudentId(studentId));
    }

    @Override
    public List<ComplaintResponseDto> getComplaintsByTeacher(Long teacherId) {
        return complaintMapper.toResponseDtoList(complaintRepository.findByTeacherId(teacherId));
    }

    @Override
    public List<ComplaintResponseDto> getComplaintsByStatus(ComplaintStatus status) {
        return complaintMapper.toResponseDtoList(complaintRepository.findByStatus(status));
    }

    @Override
    public List<ComplaintResponseDto> getComplaintsByPriority(Priority priority) {
        return complaintMapper.toResponseDtoList(complaintRepository.findByPriority(priority));
    }

    @Override
    public List<ComplaintResponseDto> getComplaintsByCategory(ComplaintCategory category) {
        return complaintMapper.toResponseDtoList(complaintRepository.findByCategory(category));
    }

    @Override
    public ComplaintResponseDto updateStatus(Long id, ComplaintStatus status) {
        Complaint complaint = findComplaintById(id);
        complaint.setStatus(status);
        return complaintMapper.toResponseDto(complaintRepository.save(complaint));
    }

    @Override
    public ComplaintResponseDto resolveComplaint(Long id, String resolutionComment, String resolvedBy) {
        Complaint complaint = findComplaintById(id);
        complaint.setStatus(ComplaintStatus.RESOLVED);
        complaint.setResolutionComment(resolutionComment);
        complaint.setResolvedBy(resolvedBy);
        complaint.setResolvedAt(Instant.now());
        return complaintMapper.toResponseDto(complaintRepository.save(complaint));
    }

    @Override
    public void deleteComplaint(Long id) {
        if (!complaintRepository.existsById(id))
            throw new RuntimeException("Complaint not found");
        complaintRepository.deleteById(id);
    }


    private Complaint findComplaintById(Long id) {
        return complaintRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Complaint not found"));
    }
}