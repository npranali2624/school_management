package com.example.school_management.Mapper;

import com.example.school_management.dto.TeacherRequestDto;
import com.example.school_management.dto.TeacherResponseDto;
import com.example.school_management.entity.Class;
import com.example.school_management.entity.Subject;
import com.example.school_management.entity.Teacher;
import com.example.school_management.repo.ClassRepository;
import com.example.school_management.repo.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class TeacherMapper {

    private final SubjectRepository subjectRepository;
    private final ClassRepository classRepository;
    private final PasswordEncoder passwordEncoder;

    public Teacher toEntity(TeacherRequestDto dto) {
        Teacher teacher = new Teacher();
        applyDtoToEntity(dto, teacher);
        teacher.setPassword(passwordEncoder.encode(dto.getPassword()));
        return teacher;
    }

    public void updateEntity(TeacherRequestDto dto, Teacher teacher) {
        applyDtoToEntity(dto, teacher);
        if (dto.getPassword() != null && !dto.getPassword().isBlank()) {
            teacher.setPassword(passwordEncoder.encode(dto.getPassword()));
        }
    }

    public TeacherResponseDto toResponseDto(Teacher teacher) {
        TeacherResponseDto dto = new TeacherResponseDto();

        dto.setId(teacher.getId());
        dto.setCreatedAt(teacher.getCreatedAt());
        dto.setUpdatedAt(teacher.getUpdatedAt());
        dto.setFirstName(teacher.getFirstName());
        dto.setMiddleName(teacher.getMiddleName());
        dto.setLastName(teacher.getLastName());
        dto.setEmail(teacher.getEmail());
        dto.setMobile(teacher.getMobile());
        dto.setGender(teacher.getGender());
        dto.setDob(teacher.getDob());
        dto.setAddressLine1(teacher.getAddressLine1());
        dto.setAddressLine2(teacher.getAddressLine2());
        dto.setCity(teacher.getCity());
        dto.setState(teacher.getState());
        dto.setPincode(teacher.getPincode());
        dto.setAadharNo(teacher.getAadharNo());
        dto.setPanNo(teacher.getPanNo());
        dto.setDegreeType(teacher.getDegreeType());
        dto.setCustomDegreeName(teacher.getCustomDegreeName());
        dto.setJoiningDate(teacher.getJoiningDate());
        dto.setYearsOfExperience(teacher.getYearsOfExperience());
        dto.setActive(teacher.isActive());
        dto.setRole(teacher.getRole());
        dto.setPreviousSchool(teacher.getPreviousSchool());
        dto.setAadharPhotourl(teacher.getAadharPhotourl());
        dto.setPanPhotourl(teacher.getPanPhotourl());
        dto.setDegreeCertificateurl(teacher.getDegreeCertificateurl());
        dto.setResignationLetterurl(teacher.getResignationLetterurl());
        dto.setResumeurl(teacher.getResumeurl());

        if (teacher.getSubject() != null) {
            dto.setSubjectId(teacher.getSubject().getId());
            dto.setSubjectName(teacher.getSubject().getSubjectName());
        }

        if (teacher.getSpecialization() != null) {
            dto.setSpecializationId(teacher.getSpecialization().getId());
            dto.setSpecializationName(teacher.getSpecialization().getSubjectName());
        }

        if (teacher.getAssignedClass() != null) {
            dto.setAssignedClassId(teacher.getAssignedClass().getId());
            dto.setAssignedClassName(teacher.getAssignedClass().getClassName());
        }

        return dto;
    }

    public List<TeacherResponseDto> toResponseDtoList(List<Teacher> teachers) {
        return teachers.stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());
    }


    private void applyDtoToEntity(TeacherRequestDto dto, Teacher teacher) {
        teacher.setFirstName(dto.getFirstName());
        teacher.setMiddleName(dto.getMiddleName());
        teacher.setLastName(dto.getLastName());
        teacher.setEmail(dto.getEmail());
        teacher.setMobile(dto.getMobile());
        teacher.setGender(dto.getGender());
        teacher.setDob(dto.getDob());
        teacher.setAddressLine1(dto.getAddressLine1());
        teacher.setAddressLine2(dto.getAddressLine2());
        teacher.setCity(dto.getCity());
        teacher.setState(dto.getState());
        teacher.setPincode(dto.getPincode());
        teacher.setAadharNo(dto.getAadharNo());
        teacher.setPanNo(dto.getPanNo());
        teacher.setDegreeType(dto.getDegreeType());
        teacher.setCustomDegreeName(dto.getCustomDegreeName());
        teacher.setJoiningDate(dto.getJoiningDate());
        teacher.setYearsOfExperience(dto.getYearsOfExperience());
        teacher.setPreviousSchool(dto.getPreviousSchool());
        teacher.setAadharPhotourl(dto.getAadharPhotourl());
        teacher.setPanPhotourl(dto.getPanPhotourl());
        teacher.setDegreeCertificateurl(dto.getDegreeCertificateurl());
        teacher.setResignationLetterurl(dto.getResignationLetterurl());
        teacher.setResumeurl(dto.getResumeurl());

        Subject subject = subjectRepository.findById(dto.getSubjectId())
                .orElseThrow(() -> new RuntimeException(
                        "Subject not found with id: " + dto.getSubjectId()));
        teacher.setSubject(subject);

        Subject specialization = subjectRepository.findById(dto.getSpecializationId())
                .orElseThrow(() -> new RuntimeException(
                        "Subject not found with id: " + dto.getSpecializationId()));
        teacher.setSpecialization(specialization);

        if (dto.getAssignedClassId() != null) {
            Class assignedClass = classRepository.findById(dto.getAssignedClassId())
                    .orElseThrow(() -> new RuntimeException(
                            "Class not found with id: " + dto.getAssignedClassId()));
            teacher.setAssignedClass(assignedClass);
        }
    }
}