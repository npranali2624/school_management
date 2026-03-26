package com.example.school_management.service.Impl;

import com.example.school_management.dto.StudentRequestDto;
import com.example.school_management.dto.StudentResponseDto;
import com.example.school_management.entity.Student;
import com.example.school_management.exception.ResourceNotFoundException;
import com.example.school_management.exception.DuplicateResourceException;
import com.example.school_management.repo.StudentRepository;
import com.example.school_management.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    @Override
    public StudentResponseDto admitStudent(StudentRequestDto request) {

        // Check duplicate aadhar
        if (studentRepository.existsByAadharNo(request.getAadharNo())) {
            throw new DuplicateResourceException(
                    "Student with Aadhar " + request.getAadharNo() + " already exists"
            );
        }

        // Map DTO → Entity
        Student student = mapToEntity(request);
        student.setActive(true);

        // Save to DB
        Student saved = studentRepository.save(student);

        // Map Entity → Response DTO
        return mapToResponseDto(saved);
    }

    @Override
    public List<StudentResponseDto> getAllStudents() {
        return studentRepository.findAll()
                .stream()
                .map(this::mapToResponseDto)
                .toList();
    }

    @Override
    public StudentResponseDto getStudentById(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Student not found with ID: " + id
                ));
        return mapToResponseDto(student);
    }

    @Override
    public StudentResponseDto getStudentByRollNumber(String rollNumber) {
        Student student = studentRepository.findByRollNumber(rollNumber)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Student not found with Roll Number: " + rollNumber
                ));
        return mapToResponseDto(student);
    }

    @Override
    public StudentResponseDto updateStudent(Long id, StudentRequestDto request) {
        Student existing = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Student not found with ID: " + id
                ));

        // Update fields
        updateEntityFromDto(existing, request);

        Student updated = studentRepository.save(existing);
        return mapToResponseDto(updated);
    }

    @Override
    public void toggleStudentStatus(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Student not found with ID: " + id
                ));
        // flip true → false OR false → true
        student.setActive(!student.isActive());
        studentRepository.save(student);
    }

    @Override
    public void deleteStudent(Long id) {
        if (!studentRepository.existsById(id)) {
            throw new ResourceNotFoundException(
                    "Student not found with ID: " + id
            );
        }
        studentRepository.deleteById(id);
    }

    // ── Private Mapper Methods ──────────────────

    private Student mapToEntity(StudentRequestDto dto) {
        return Student.builder()
                .firstName(dto.getFirstName())
                .middleName(dto.getMiddleName())
                .lastName(dto.getLastName())
                .gender(dto.getGender())
                .dob(dto.getDob())
                .bloodGroup(dto.getBloodGroup())
                .aadharNo(dto.getAadharNo())
                .nationality(dto.getNationality())
                .religion(dto.getReligion())
                .category(dto.getCategory())
                .fatherFirstName(dto.getFatherFirstName())
                .fatherMiddleName(dto.getFatherMiddleName())
                .fatherLastName(dto.getFatherLastName())
                .fatherOccupation(dto.getFatherOccupation())
                .motherFirstName(dto.getMotherFirstName())
                .motherMiddleName(dto.getMotherMiddleName())
                .motherLastName(dto.getMotherLastName())
                .motherOccupation(dto.getMotherOccupation())
                .guardianFirstName(dto.getGuardianFirstName())
                .guardianMiddleName(dto.getGuardianMiddleName())
                .guardianLastName(dto.getGuardianLastName())
                .guardianRelation(dto.getGuardianRelation())
                .mobilePrimary(dto.getMobilePrimary())
                .mobileAlternate(dto.getMobileAlternate())
                .email(dto.getEmail())
                .addressLine1(dto.getAddressLine1())
                .previousSchool(dto.getPreviousSchool())
                .previousPercentage(dto.getPreviousPercentage())
                .birthCertificate_url(dto.getBirthCertificate_url())
                .aadharPhoto_url(dto.getAadharPhoto_url())
                .previousMarksheet_url(dto.getPreviousMarksheet_url())
                .passportPhoto_url(dto.getPassportPhoto_url())
                .leavingCertificate_url(dto.getLeavingCertificate_url())
                .casteCertificate_url(dto.getCasteCertificate_url())
                .incomeCertificate_url(dto.getIncomeCertificate_url())
                .build();
    }

    private void updateEntityFromDto(Student student, StudentRequestDto dto) {
        student.setFirstName(dto.getFirstName());
        student.setMiddleName(dto.getMiddleName());
        student.setLastName(dto.getLastName());
        student.setGender(dto.getGender());
        student.setDob(dto.getDob());
        student.setBloodGroup(dto.getBloodGroup());
        student.setNationality(dto.getNationality());
        student.setReligion(dto.getReligion());
        student.setCategory(dto.getCategory());
        student.setFatherFirstName(dto.getFatherFirstName());
        student.setFatherMiddleName(dto.getFatherMiddleName());
        student.setFatherLastName(dto.getFatherLastName());
        student.setFatherOccupation(dto.getFatherOccupation());
        student.setMotherFirstName(dto.getMotherFirstName());
        student.setMotherMiddleName(dto.getMotherMiddleName());
        student.setMotherLastName(dto.getMotherLastName());
        student.setMotherOccupation(dto.getMotherOccupation());
        student.setGuardianFirstName(dto.getGuardianFirstName());
        student.setGuardianMiddleName(dto.getGuardianMiddleName());
        student.setGuardianLastName(dto.getGuardianLastName());
        student.setGuardianRelation(dto.getGuardianRelation());
        student.setMobilePrimary(dto.getMobilePrimary());
        student.setMobileAlternate(dto.getMobileAlternate());
        student.setEmail(dto.getEmail());
        student.setAddressLine1(dto.getAddressLine1());
        student.setPreviousSchool(dto.getPreviousSchool());
        student.setPreviousPercentage(dto.getPreviousPercentage());
        student.setBirthCertificate_url(dto.getBirthCertificate_url());
        student.setAadharPhoto_url(dto.getAadharPhoto_url());
        student.setPreviousMarksheet_url(dto.getPreviousMarksheet_url());
        student.setPassportPhoto_url(dto.getPassportPhoto_url());
        student.setLeavingCertificate_url(dto.getLeavingCertificate_url());
        student.setCasteCertificate_url(dto.getCasteCertificate_url());
        student.setIncomeCertificate_url(dto.getIncomeCertificate_url());
    }

    private StudentResponseDto mapToResponseDto(Student student) {
        StudentResponseDto dto = new StudentResponseDto();
        dto.setId(student.getId());
        dto.setRollNumber(student.getRollNumber());
        dto.setAadharNo(student.getAadharNo());
        dto.setGender(student.getGender());
        dto.setDob(student.getDob());
        dto.setBloodGroup(student.getBloodGroup());
        dto.setNationality(student.getNationality());
        dto.setReligion(student.getReligion());
        dto.setCategory(student.getCategory());
        dto.setMobilePrimary(student.getMobilePrimary());
        dto.setMobileAlternate(student.getMobileAlternate());
        dto.setEmail(student.getEmail());
        dto.setPreviousSchool(student.getPreviousSchool());
        dto.setPreviousPercentage(student.getPreviousPercentage());
        dto.setActive(student.isActive());
        return dto;
    }
}