package com.example.school_management.Mapper;

import com.example.school_management.dto.SubjectRequestDto;
import com.example.school_management.dto.SubjectResponseDto;
import com.example.school_management.entity.Subject;
import com.example.school_management.entity.Teacher;
import com.example.school_management.enums.SubjectType;
import com.example.school_management.repo.TeacherRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class SubjectMapper {

    private final TeacherRepo teacherRepository;

    public Subject toEntity(SubjectRequestDto dto) {
        Subject subject = new Subject();
        applyDtoToEntity(dto, subject);
        return subject;
    }

    public void updateEntity(SubjectRequestDto dto, Subject subject) {
        applyDtoToEntity(dto, subject);
    }

    public SubjectResponseDto toResponseDto(Subject subject) {
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

    public List<SubjectResponseDto> toResponseDtoList(List<Subject> subjects) {
        return subjects.stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());
    }

    private void applyDtoToEntity(SubjectRequestDto dto, Subject subject) {
        subject.setSubjectName(dto.getSubjectName());
        subject.setSubjectCode(dto.getSubjectCode());
        subject.setWeeklyHours(dto.getWeeklyHours());
        subject.setSubjectType(dto.getSubjectType());
        subject.setTheoryMarks(dto.getTheoryMarks());
        subject.setInternalMarks(dto.getInternalMarks());
        subject.setPracticalMarks(dto.getPracticalMarks());
        subject.setProjectMarks(dto.getProjectMarks());
        subject.setOralMarks(dto.getOralMarks());
        subject.setTotalMarks(dto.getTotalMarks());
        subject.setPassingMarks(dto.getPassingMarks());

        subject.setStandard(
                dto.getSubjectType() == SubjectType.COMPULSORY ? null : dto.getStandard());

        if (dto.getSubjectTeacherId() != null) {
            Teacher teacher = teacherRepository.findById(dto.getSubjectTeacherId())
                    .orElseThrow(() -> new RuntimeException(
                            "Teacher not found with id: " + dto.getSubjectTeacherId()));
            subject.setSubjectTeacher(teacher);
        } else {
            subject.setSubjectTeacher(null);
        }
    }
}