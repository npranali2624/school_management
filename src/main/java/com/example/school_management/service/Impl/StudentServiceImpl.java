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

    // ✅ MAPPER METHODS

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
                .rollNumber(dto.getRollNumber())   // ✅ IMPORTANT

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

                // ✅ CORRECT METHOD NAMES
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

        // ✅ FIXED HERE ALSO
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