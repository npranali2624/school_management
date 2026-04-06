package com.example.school_management.service.Impl;

import com.example.school_management.Mapper.TeacherMapper;
import com.example.school_management.dto.TeacherRequestDto;
import com.example.school_management.dto.TeacherResponseDto;
import com.example.school_management.entity.Teacher;
import com.example.school_management.enums.Role;
import com.example.school_management.repo.TeacherRepo;
import com.example.school_management.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepo teacherRepo;
    private final TeacherMapper teacherMapper;

<<<<<<< HEAD
    private TeacherResponseDto toDto(Teacher teacher) {
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
=======
>>>>>>> pranali

    @Override
    public TeacherResponseDto createTeacher(TeacherRequestDto request) {

        if (teacherRepo.existsByEmail(request.getEmail())) {
            throw new DataIntegrityViolationException("Email already exists");
        }

        if (teacherRepo.existsByMobile(request.getMobile())) {
            throw new DataIntegrityViolationException("Mobile already exists");
        }

<<<<<<< HEAD
        Teacher teacher = new Teacher();
        teacher.setFirstName(request.getFirstName());
        teacher.setMiddleName(request.getMiddleName());
        teacher.setLastName(request.getLastName());
        teacher.setEmail(request.getEmail());
        teacher.setMobile(request.getMobile());
        teacher.setPassword(passwordEncoder.encode(request.getPassword()));
        teacher.setGender(request.getGender());
        teacher.setDob(request.getDob());
        teacher.setAddressLine1(request.getAddressLine1());
        teacher.setAddressLine2(request.getAddressLine2());
        teacher.setCity(request.getCity());
        teacher.setState(request.getState());
        teacher.setPincode(request.getPincode());
        teacher.setAadharNo(request.getAadharNo());
        teacher.setPanNo(request.getPanNo());
        teacher.setDegreeType(request.getDegreeType());
        teacher.setCustomDegreeName(request.getCustomDegreeName());
        teacher.setJoiningDate(request.getJoiningDate());
        teacher.setYearsOfExperience(request.getYearsOfExperience());
        teacher.setPreviousSchool(request.getPreviousSchool());
        teacher.setAadharPhotourl(request.getAadharPhotourl());
        teacher.setPanPhotourl(request.getPanPhotourl());
        teacher.setDegreeCertificateurl(request.getDegreeCertificateurl());
        teacher.setResignationLetterurl(request.getResignationLetterurl());
        teacher.setResumeurl(request.getResumeurl());
        teacher.setRole(request.getRole());
=======
>>>>>>> pranali

        Teacher teacher = teacherMapper.toEntity(request);


        teacher.setRole(Role.TEACHER);
        teacher.setActive(true);

        return teacherMapper.toResponseDto(teacherRepo.save(teacher));
    }


    @Override
    public TeacherResponseDto getTeacherById(Long id) {
        Teacher teacher = teacherRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Teacher not found with id: " + id));

        return teacherMapper.toResponseDto(teacher);
    }


    @Override
    public List<TeacherResponseDto> getAllTeachers() {
        return teacherMapper.toResponseDtoList(teacherRepo.findAll());
    }


    @Override
    public TeacherResponseDto updateTeacher(Long id, TeacherRequestDto request) {

        Teacher existing = teacherRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Teacher not found with id: " + id));

        if (request.getEmail() != null &&
                !existing.getEmail().equals(request.getEmail()) &&
                teacherRepo.existsByEmail(request.getEmail())) {
            throw new DataIntegrityViolationException("Email already exists");
        }

        if (request.getMobile() != null &&
                !existing.getMobile().equals(request.getMobile()) &&
                teacherRepo.existsByMobile(request.getMobile())) {
            throw new DataIntegrityViolationException("Mobile already exists");
        }


        teacherMapper.updateEntity(request, existing);

        return teacherMapper.toResponseDto(teacherRepo.save(existing));
    }


    @Override
    public void deleteTeacher(Long id) {
        if (!teacherRepo.existsById(id)) {
            throw new RuntimeException("Teacher not found with id: " + id);
        }
        teacherRepo.deleteById(id);
    }
}