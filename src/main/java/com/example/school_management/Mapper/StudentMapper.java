package com.example.school_management.Mapper;

import com.example.school_management.dto.StudentRequestDto;
import com.example.school_management.dto.StudentResponseDto;
import com.example.school_management.entity.Parent;
import com.example.school_management.entity.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class StudentMapper {

    private final PasswordEncoder passwordEncoder;

    // StudentRequestDto → New Student entity (for create)
    public Student toEntity(StudentRequestDto dto) {
        Parent parent = buildParent(dto);

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
                .rollNumber(dto.getRollNumber())
                .standard(dto.getStandard())
                .division(dto.getDivision())
                .parent(parent)
                .previousSchool(dto.getPreviousSchool())
                .previousPercentage(dto.getPreviousPercentage())
                .birthCertificateUrl(dto.getBirthCertificateUrl())
                .aadharPhotoUrl(dto.getAadharPhotoUrl())
                .previousMarksheetUrl(dto.getPreviousMarksheetUrl())
                .passportPhotoUrl(dto.getPassportPhotoUrl())
                .leavingCertificateUrl(dto.getLeavingCertificateUrl())
                .casteCertificateUrl(dto.getCasteCertificateUrl())
                .incomeCertificateUrl(dto.getIncomeCertificateUrl())
                .build();
    }

    // StudentRequestDto → Existing Student entity (for update)
    public void updateEntity(Student student, StudentRequestDto dto) {
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
        student.setStandard(dto.getStandard());
        student.setDivision(dto.getDivision());

        Parent parent = student.getParent() != null ? student.getParent() : new Parent();
        applyParentFields(dto, parent);

        if (dto.getPassword() != null && !dto.getPassword().isBlank()) {
            parent.setPassword(passwordEncoder.encode(dto.getPassword()));
        }

        student.setParent(parent);

        student.setPreviousSchool(dto.getPreviousSchool());
        student.setPreviousPercentage(dto.getPreviousPercentage());
        student.setBirthCertificateUrl(dto.getBirthCertificateUrl());
        student.setAadharPhotoUrl(dto.getAadharPhotoUrl());
        student.setPreviousMarksheetUrl(dto.getPreviousMarksheetUrl());
        student.setPassportPhotoUrl(dto.getPassportPhotoUrl());
        student.setLeavingCertificateUrl(dto.getLeavingCertificateUrl());
        student.setCasteCertificateUrl(dto.getCasteCertificateUrl());
        student.setIncomeCertificateUrl(dto.getIncomeCertificateUrl());
    }

    // Student entity → StudentResponseDto
    public StudentResponseDto toResponseDto(Student student) {
        StudentResponseDto dto = new StudentResponseDto();

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
        dto.setStandard(student.getStandard());
        dto.setDivision(student.getDivision());

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
            dto.setEmergencyContactName(parent.getEmergencyContactName());
            dto.setEmergencyContactNumber(parent.getEmergencyContactNumber());

            dto.setAddressLine1(parent.getAddressLine1());
            dto.setAddressLine2(parent.getAddressLine2());
            dto.setCity(parent.getCity());
            dto.setState(parent.getState());
            dto.setPincode(parent.getPincode());
        }

        dto.setPreviousSchool(student.getPreviousSchool());
        dto.setPreviousPercentage(student.getPreviousPercentage());
        dto.setActive(student.isActive());

        return dto;
    }

    // List<Student> → List<StudentResponseDto>
    public List<StudentResponseDto> toResponseDtoList(List<Student> students) {
        return students.stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());
    }

    // -------------------------------------------------------------------------
    // Private helpers
    // -------------------------------------------------------------------------

    private Parent buildParent(StudentRequestDto dto) {
        if (dto.getPassword() == null || dto.getPassword().isBlank()) {
            throw new IllegalArgumentException("Password is required for Parent account");
        }

        Parent parent = new Parent();
        parent.setPassword(passwordEncoder.encode(dto.getPassword()));
        applyParentFields(dto, parent);
        return parent;
    }

    private void applyParentFields(StudentRequestDto dto, Parent parent) {
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
        parent.setEmergencyContactName(dto.getEmergencyContactName());
        parent.setEmergencyContactNumber(dto.getEmergencyContactNumber());

        parent.setAddressLine1(dto.getAddressLine1());
        parent.setAddressLine2(dto.getAddressLine2());
        parent.setCity(dto.getCity());
        parent.setState(dto.getState());
        parent.setPincode(dto.getPincode());
    }
}