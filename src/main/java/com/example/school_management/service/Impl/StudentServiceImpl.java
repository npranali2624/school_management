package com.example.school_management.service.Impl;

import com.example.school_management.dto.StudentRequestDto;
import com.example.school_management.dto.StudentResponseDto;
import com.example.school_management.entity.Parent;
import com.example.school_management.entity.Student;
import com.example.school_management.exception.ResourceNotFoundException;
import com.example.school_management.exception.DuplicateResourceException;
import com.example.school_management.repo.StudentRepository;
import com.example.school_management.service.StudentService;
import org.springframework.security.crypto.password.PasswordEncoder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public StudentResponseDto admitStudent(StudentRequestDto request) {

        if (studentRepository.existsByAadharNo(request.getAadharNo())) {
            throw new DuplicateResourceException(
                    "Student with Aadhar " + request.getAadharNo() + " already exists"
            );
        }

        Student student = mapToEntity(request);
        student.setActive(true);

        Student saved = studentRepository.save(student);

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
    public StudentResponseDto getStudentByRollNumber(Integer rollNumber) {
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

    // ─── MAPPER METHODS ─────────────────────────────────────────────

    private Parent buildParent(StudentRequestDto dto) {

        Parent parent = new Parent();

        // FIX: Map and encode the password during creation
        if (dto.getPassword() != null && !dto.getPassword().isBlank()) {
            parent.setPassword(passwordEncoder.encode(dto.getPassword()));
        } else {
            // This prevents the Jakarta Validation error by catching it before saving
            throw new IllegalArgumentException("Password is required for Parent account");
        }

        // Father
        parent.setFatherFirstName(dto.getFatherFirstName());
        parent.setFatherMiddleName(dto.getFatherMiddleName());
        parent.setFatherLastName(dto.getFatherLastName());
        parent.setFatherOccupation(dto.getFatherOccupation());

        // Mother
        parent.setMotherFirstName(dto.getMotherFirstName());
        parent.setMotherMiddleName(dto.getMotherMiddleName());
        parent.setMotherLastName(dto.getMotherLastName());
        parent.setMotherOccupation(dto.getMotherOccupation());

        // Guardian
        parent.setGuardianFirstName(dto.getGuardianFirstName());
        parent.setGuardianMiddleName(dto.getGuardianMiddleName());
        parent.setGuardianLastName(dto.getGuardianLastName());
        parent.setGuardianRelation(dto.getGuardianRelation());

        // Contact
        parent.setMobilePrimary(dto.getMobilePrimary());
        parent.setMobileAlternate(dto.getMobileAlternate());
        parent.setEmail(dto.getEmail());
        parent.setAddressLine1(dto.getAddressLine1());

        return parent;
    }

    private Student mapToEntity(StudentRequestDto dto) {

        Parent parent = buildParent(dto);

        return Student.builder()
                // Section 1
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
                .rollNumber(dto.getRollNumber())

                // Section 2
                .parent(parent)

                // Section 3
                .previousSchool(dto.getPreviousSchool())
                .previousPercentage(dto.getPreviousPercentage())

                // Section 4
                .birthCertificateUrl(dto.getBirthCertificateUrl())
                .aadharPhotoUrl(dto.getAadharPhotoUrl())
                .previousMarksheetUrl(dto.getPreviousMarksheetUrl())
                .passportPhotoUrl(dto.getPassportPhotoUrl())
                .leavingCertificateUrl(dto.getLeavingCertificateUrl())
                .casteCertificateUrl(dto.getCasteCertificateUrl())
                .incomeCertificateUrl(dto.getIncomeCertificateUrl())

                .build();
    }

    private void updateEntityFromDto(Student student, StudentRequestDto dto) {

        // Section 1
        student.setFirstName(dto.getFirstName());
        student.setMiddleName(dto.getMiddleName());
        student.setLastName(dto.getLastName());
        student.setGender(dto.getGender());
        student.setDob(dto.getDob());
        student.setBloodGroup(dto.getBloodGroup());
        student.setNationality(dto.getNationality());
        student.setReligion(dto.getReligion());
        student.setCategory(dto.getCategory());
        student.setRollNumber(dto.getRollNumber());

        // Section 2
        Parent parent = student.getParent() != null ? student.getParent() : new Parent();

        parent.setFatherFirstName(dto.getFatherFirstName());
        parent.setFatherMiddleName(dto.getFatherMiddleName());
        parent.setFatherLastName(dto.getFatherLastName());
        parent.setFatherOccupation(dto.getFatherOccupation());

        parent.setMotherFirstName(dto.getMotherFirstName());
        parent.setMotherMiddleName(dto.getMotherMiddleName());
        parent.setMotherLastName(dto.getMotherLastName());
        parent.setMotherOccupation(dto.getMotherOccupation());

        parent.setGuardianFirstName(dto.getGuardianFirstName());
        parent.setGuardianMiddleName(dto.getGuardianMiddleName());
        parent.setGuardianLastName(dto.getGuardianLastName());
        parent.setGuardianRelation(dto.getGuardianRelation());

        parent.setMobilePrimary(dto.getMobilePrimary());
        parent.setMobileAlternate(dto.getMobileAlternate());
        parent.setEmail(dto.getEmail());
        parent.setAddressLine1(dto.getAddressLine1());

        // Update password if provided in update request
        if (dto.getPassword() != null && !dto.getPassword().isBlank()) {
            parent.setPassword(passwordEncoder.encode(dto.getPassword()));
        }

        student.setParent(parent);

        // Section 3
        student.setPreviousSchool(dto.getPreviousSchool());
        student.setPreviousPercentage(dto.getPreviousPercentage());

        // Section 4
        student.setBirthCertificateUrl(dto.getBirthCertificateUrl());
        student.setAadharPhotoUrl(dto.getAadharPhotoUrl());
        student.setPreviousMarksheetUrl(dto.getPreviousMarksheetUrl());
        student.setPassportPhotoUrl(dto.getPassportPhotoUrl());
        student.setLeavingCertificateUrl(dto.getLeavingCertificateUrl());
        student.setCasteCertificateUrl(dto.getCasteCertificateUrl());
        student.setIncomeCertificateUrl(dto.getIncomeCertificateUrl());
    }

    private StudentResponseDto mapToResponseDto(Student student) {

        StudentResponseDto dto = new StudentResponseDto();

        // Section 1
        dto.setId(student.getId());
        dto.setFirstName(student.getFirstName());
        dto.setMiddleName(student.getMiddleName());
        dto.setLastName(student.getLastName());
        dto.setRollNumber(student.getRollNumber());
        dto.setAadharNo(student.getAadharNo());
        dto.setGender(student.getGender());
        dto.setDob(student.getDob());
        dto.setBloodGroup(student.getBloodGroup());
        dto.setNationality(student.getNationality());
        dto.setReligion(student.getReligion());
        dto.setCategory(student.getCategory());

        // Section 2
        if (student.getParent() != null) {
            Parent parent = student.getParent();

            dto.setFatherFirstName(parent.getFatherFirstName());
            dto.setFatherMiddleName(parent.getFatherMiddleName());
            dto.setFatherLastName(parent.getFatherLastName());
            dto.setFatherOccupation(parent.getFatherOccupation());

            dto.setMotherFirstName(parent.getMotherFirstName());
            dto.setMotherMiddleName(parent.getMotherMiddleName());
            dto.setMotherLastName(parent.getMotherLastName());
            dto.setMotherOccupation(parent.getMotherOccupation());

            dto.setGuardianFirstName(parent.getGuardianFirstName());
            dto.setGuardianMiddleName(parent.getGuardianMiddleName());
            dto.setGuardianLastName(parent.getGuardianLastName());
            dto.setGuardianRelation(parent.getGuardianRelation());

            dto.setMobilePrimary(parent.getMobilePrimary());
            dto.setMobileAlternate(parent.getMobileAlternate());
            dto.setEmail(parent.getEmail());
            dto.setAddressLine1(parent.getAddressLine1());
        }

        // Section 3
        dto.setPreviousSchool(student.getPreviousSchool());
        dto.setPreviousPercentage(student.getPreviousPercentage());

        // Section 5
        dto.setActive(student.isActive());

        return dto;
    }
}