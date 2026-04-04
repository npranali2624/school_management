package com.example.school_management.service.Impl;

import com.example.school_management.dto.TeacherRequestDto;
import com.example.school_management.dto.TeacherResponseDto;
import com.example.school_management.entity.Subject;
import com.example.school_management.entity.Teacher;
import com.example.school_management.repo.SubjectRepository;
import com.example.school_management.repo.TeacherRepo;
import com.example.school_management.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepo teacherRepo;
    private final PasswordEncoder passwordEncoder;
    private final SubjectRepository subjectRepository;

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

    @Override
    public TeacherResponseDto createTeacher(TeacherRequestDto request) {
        if (teacherRepo.existsByEmail(request.getEmail())) {
            throw new DataIntegrityViolationException("Email already exists");
        }
        if (teacherRepo.existsByMobile(request.getMobile())) {
            throw new DataIntegrityViolationException("Mobile already exists");
        }

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

        if (request.getSubjectId() != null) {
            Subject subject = subjectRepository.findById(request.getSubjectId())
                    .orElseThrow(() -> new RuntimeException("Subject not found"));
            teacher.setSubject(subject);
        }

        if (request.getSpecializationId() != null) {
            Subject specialization = subjectRepository.findById(request.getSpecializationId())
                    .orElseThrow(() -> new RuntimeException("Specialization not found"));
            teacher.setSpecialization(specialization);
        }

        return toDto(teacherRepo.save(teacher));
    }

    @Override
    public TeacherResponseDto getTeacherById(Long id) {
        Teacher teacher = teacherRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Teacher not found with id: " + id));
        return toDto(teacher);
    }

    @Override
    public List<TeacherResponseDto> getAllTeachers() {
        return teacherRepo.findAll()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
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

        if (request.getFirstName() != null) existing.setFirstName(request.getFirstName());
        if (request.getMiddleName() != null) existing.setMiddleName(request.getMiddleName());
        if (request.getLastName() != null) existing.setLastName(request.getLastName());
        if (request.getEmail() != null) existing.setEmail(request.getEmail());
        if (request.getMobile() != null) existing.setMobile(request.getMobile());
        if (request.getGender() != null) existing.setGender(request.getGender());
        if (request.getDob() != null) existing.setDob(request.getDob());
        if (request.getAddressLine1() != null) existing.setAddressLine1(request.getAddressLine1());
        if (request.getAddressLine2() != null) existing.setAddressLine2(request.getAddressLine2());
        if (request.getCity() != null) existing.setCity(request.getCity());
        if (request.getState() != null) existing.setState(request.getState());
        if (request.getPincode() != null) existing.setPincode(request.getPincode());
        if (request.getAadharNo() != null) existing.setAadharNo(request.getAadharNo());
        if (request.getPanNo() != null) existing.setPanNo(request.getPanNo());
        if (request.getDegreeType() != null) existing.setDegreeType(request.getDegreeType());
        if (request.getCustomDegreeName() != null) existing.setCustomDegreeName(request.getCustomDegreeName());
        if (request.getJoiningDate() != null) existing.setJoiningDate(request.getJoiningDate());
        if (request.getYearsOfExperience() != null) existing.setYearsOfExperience(request.getYearsOfExperience());
        if (request.getPreviousSchool() != null) existing.setPreviousSchool(request.getPreviousSchool());
        if (request.getAadharPhotourl() != null) existing.setAadharPhotourl(request.getAadharPhotourl());
        if (request.getPanPhotourl() != null) existing.setPanPhotourl(request.getPanPhotourl());
        if (request.getDegreeCertificateurl() != null) existing.setDegreeCertificateurl(request.getDegreeCertificateurl());
        if (request.getResignationLetterurl() != null) existing.setResignationLetterurl(request.getResignationLetterurl());
        if (request.getResumeurl() != null) existing.setResumeurl(request.getResumeurl());

        if (request.getPassword() != null && !request.getPassword().isBlank()) {
            existing.setPassword(passwordEncoder.encode(request.getPassword()));
        }

        if (request.getSubjectId() != null) {
            Subject subject = subjectRepository.findById(request.getSubjectId())
                    .orElseThrow(() -> new RuntimeException("Subject not found"));
            existing.setSubject(subject);
        }

        if (request.getSpecializationId() != null) {
            Subject specialization = subjectRepository.findById(request.getSpecializationId())
                    .orElseThrow(() -> new RuntimeException("Specialization not found"));
            existing.setSpecialization(specialization);
        }

        return toDto(teacherRepo.save(existing));
    }

    @Override
    public void deleteTeacher(Long id) {
        if (!teacherRepo.existsById(id)) {
            throw new RuntimeException("Teacher not found with id: " + id);
        }
        teacherRepo.deleteById(id);
    }
}