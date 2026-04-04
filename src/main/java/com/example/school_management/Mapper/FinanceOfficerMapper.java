package com.example.school_management.Mapper;

import com.example.school_management.dto.FinanceOfficerRequestDto;
import com.example.school_management.dto.FinanceOfficerResponseDto;
import com.example.school_management.entity.FinanceOfficer;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class FinanceOfficerMapper {

    public FinanceOfficer toEntity(FinanceOfficerRequestDto dto) {
        return FinanceOfficer.builder()
                .firstName(dto.getFirstName())
                .middleName(dto.getMiddleName())
                .lastName(dto.getLastName())
                .email(dto.getEmail())
                .mobile(dto.getMobile())
                .gender(dto.getGender())
                .dob(dto.getDob())
                .addressLine1(dto.getAddressLine1())
                .addressLine2(dto.getAddressLine2())
                .city(dto.getCity())
                .state(dto.getState())
                .pincode(dto.getPincode())
                .degreeType(dto.getDegreeType())
                .customDegreeName(dto.getCustomDegreeName())
                .joiningDate(dto.getJoiningDate())
                .aadharNo(dto.getAadharNo())
                .panNo(dto.getPanNo())
                .yearsOfExperience(dto.getYearsOfExperience())
                .previousOrg(dto.getPreviousOrg())
                .aadharPhotourl(dto.getAadharPhotourl())
                .panPhotourl(dto.getPanPhotourl())
                .degreeCertificateurl(dto.getDegreeCertificateurl())
                .resignationLetterurl(dto.getResignationLetterurl())
                .resumeurl(dto.getResumeurl())
                .build();
    }

    public void updateEntity(FinanceOfficerRequestDto dto, FinanceOfficer officer) {
        officer.setFirstName(dto.getFirstName());
        officer.setMiddleName(dto.getMiddleName());
        officer.setLastName(dto.getLastName());
        officer.setEmail(dto.getEmail());
        officer.setMobile(dto.getMobile());
        officer.setGender(dto.getGender());
        officer.setDob(dto.getDob());
        officer.setAddressLine1(dto.getAddressLine1());
        officer.setAddressLine2(dto.getAddressLine2());
        officer.setCity(dto.getCity());
        officer.setState(dto.getState());
        officer.setPincode(dto.getPincode());
        officer.setDegreeType(dto.getDegreeType());
        officer.setCustomDegreeName(dto.getCustomDegreeName());
        officer.setJoiningDate(dto.getJoiningDate());
        officer.setAadharNo(dto.getAadharNo());
        officer.setPanNo(dto.getPanNo());
        officer.setYearsOfExperience(dto.getYearsOfExperience());
        officer.setPreviousOrg(dto.getPreviousOrg());
        officer.setAadharPhotourl(dto.getAadharPhotourl());
        officer.setPanPhotourl(dto.getPanPhotourl());
        officer.setDegreeCertificateurl(dto.getDegreeCertificateurl());
        officer.setResignationLetterurl(dto.getResignationLetterurl());
        officer.setResumeurl(dto.getResumeurl());
    }

    public FinanceOfficerResponseDto toResponseDto(FinanceOfficer officer) {
        FinanceOfficerResponseDto dto = new FinanceOfficerResponseDto();
        dto.setId(officer.getId());
        dto.setFirstName(officer.getFirstName());
        dto.setMiddleName(officer.getMiddleName());
        dto.setLastName(officer.getLastName());
        dto.setEmail(officer.getEmail());
        dto.setMobile(officer.getMobile());
        dto.setGender(officer.getGender());
        dto.setDob(officer.getDob());
        dto.setAddressLine1(officer.getAddressLine1());
        dto.setAddressLine2(officer.getAddressLine2());
        dto.setCity(officer.getCity());
        dto.setState(officer.getState());
        dto.setPincode(officer.getPincode());
        dto.setDegreeType(officer.getDegreeType());
        dto.setCustomDegreeName(officer.getCustomDegreeName());
        dto.setJoiningDate(officer.getJoiningDate());
        dto.setAadharNo(officer.getAadharNo());
        dto.setPanNo(officer.getPanNo());
        dto.setYearsOfExperience(officer.getYearsOfExperience());
        dto.setPreviousOrg(officer.getPreviousOrg());
        dto.setAadharPhotourl(officer.getAadharPhotourl());
        dto.setPanPhotourl(officer.getPanPhotourl());
        dto.setDegreeCertificateurl(officer.getDegreeCertificateurl());
        dto.setResignationLetterurl(officer.getResignationLetterurl());
        dto.setResumeurl(officer.getResumeurl());
        dto.setActive(officer.isActive());
        dto.setRole(officer.getRole());
        return dto;
    }

    public List<FinanceOfficerResponseDto> toResponseDtoList(List<FinanceOfficer> officers) {
        return officers.stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());
    }
}