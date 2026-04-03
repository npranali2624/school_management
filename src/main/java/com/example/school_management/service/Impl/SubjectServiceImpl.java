package com.example.school_management.service.Impl;

import com.example.school_management.dto.SubjectRequestDto;
import com.example.school_management.dto.SubjectResponseDto;
import com.example.school_management.entity.Subject;
import com.example.school_management.entity.Teacher;
import com.example.school_management.enums.SubjectType;
import com.example.school_management.repo.SubjectRepository;
import com.example.school_management.repo.TeacherRepo;
import com.example.school_management.service.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubjectServiceImpl implements SubjectService {

    private final SubjectRepository subjectRepository;
    private final TeacherRepo teacherRepository;

    private Subject mapToEntity(SubjectRequestDto request) {
        Subject subject = new Subject();
        subject.setSubjectName(request.getSubjectName());
        subject.setSubjectCode(request.getSubjectCode());
        subject.setWeeklyHours(request.getWeeklyHours());
        subject.setSubjectType(request.getSubjectType());
        subject.setTheoryMarks(request.getTheoryMarks());
        subject.setInternalMarks(request.getInternalMarks());
        subject.setPracticalMarks(request.getPracticalMarks());
        subject.setProjectMarks(request.getProjectMarks());
        subject.setOralMarks(request.getOralMarks());
        subject.setTotalMarks(request.getTotalMarks());
        subject.setPassingMarks(request.getPassingMarks());


        if (request.getSubjectTeacherId() != null) {
            Teacher teacher = teacherRepository.findById(request.getSubjectTeacherId())
                    .orElseThrow(() -> new RuntimeException(
                            "Teacher not found with id: " + request.getSubjectTeacherId()));
            subject.setSubjectTeacher(teacher);
        }

        if (request.getSubjectType() == SubjectType.COMPULSORY) {
            subject.setStandard(null);
        } else {
            subject.setStandard(request.getStandard());
        }
        return subject;
    }

    private SubjectResponseDto mapToResponseDto(Subject subject) {
        SubjectResponseDto response = new SubjectResponseDto();
        response.setId(subject.getId());
        response.setSubjectName(subject.getSubjectName());
        response.setSubjectCode(subject.getSubjectCode());
        response.setStandard(subject.getStandard());
        response.setWeeklyHours(subject.getWeeklyHours());
        response.setSubjectType(subject.getSubjectType());
        response.setTheoryMarks(subject.getTheoryMarks());
        response.setInternalMarks(subject.getInternalMarks());
        response.setPracticalMarks(subject.getPracticalMarks());
        response.setProjectMarks(subject.getProjectMarks());
        response.setOralMarks(subject.getOralMarks());
        response.setTotalMarks(subject.getTotalMarks());
        response.setPassingMarks(subject.getPassingMarks());

        if (subject.getSubjectTeacher() != null) {
            response.setSubjectTeacherId(subject.getSubjectTeacher().getId());
            response.setSubjectTeacherName(
                    subject.getSubjectTeacher().getFirstName()
                            + " " + subject.getSubjectTeacher().getLastName());
        }
        return response;
    }

    @Override
    public SubjectResponseDto createSubject(SubjectRequestDto request) {
        if (request.getSubjectType() == SubjectType.OPTIONAL
                && request.getStandard() == null) {
            throw new IllegalArgumentException(
                    "Standard is required for OPTIONAL subjects");
        }
        if (subjectRepository.existsBySubjectCode(request.getSubjectCode())) {
            throw new IllegalArgumentException(
                    "Subject with code " + request.getSubjectCode() + " already exists");
        }
        Subject saved = subjectRepository.save(mapToEntity(request));
        return mapToResponseDto(saved);
    }

    @Override
    public List<SubjectResponseDto> getAllSubjects() {
        return subjectRepository.findAll()
                .stream()
                .map(this::mapToResponseDto)
                .toList();
    }

    @Override
    public SubjectResponseDto getSubjectByCode(Integer subjectCode) {
        Subject subject = subjectRepository.findBySubjectCode(subjectCode)
                .orElseThrow(() -> new RuntimeException(
                        "Subject not found with code: " + subjectCode));
        return mapToResponseDto(subject);
    }

    @Override
    public SubjectResponseDto updateSubject(Integer subjectCode, SubjectRequestDto request) {
        Subject existing = subjectRepository.findBySubjectCode(subjectCode)
                .orElseThrow(() -> new RuntimeException(
                        "Subject not found with code: " + subjectCode));

        if (request.getSubjectType() == SubjectType.OPTIONAL
                && request.getStandard() == null) {
            throw new IllegalArgumentException(
                    "Standard is required for OPTIONAL subjects");
        }

        Subject updated = mapToEntity(request);
        updated.setId(existing.getId());
        Subject saved = subjectRepository.save(updated);
        return mapToResponseDto(saved);
    }

    @Override
    public void deleteSubject(Integer subjectCode) {
        if (!subjectRepository.existsBySubjectCode(subjectCode)) {
            throw new RuntimeException(
                    "Subject not found with code: " + subjectCode);
        }
        Subject subject = subjectRepository.findBySubjectCode(subjectCode).get();
        subjectRepository.deleteById(subject.getId());
    }

    // 🆕 Add this at the bottom inside SubjectServiceImpl class
    @Override
    public SubjectResponseDto getSubjectById(Long id) {
        Subject subject = subjectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Subject not found with id: " + id));
        return mapToResponseDto(subject);
    }
}