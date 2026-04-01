package com.example.school_management.service.Impl;

import com.example.school_management.dto.FinanceOfficerRequestDto;
import com.example.school_management.dto.FinanceOfficerResponseDto;
import com.example.school_management.entity.FinanceOfficer;
import com.example.school_management.enums.Role;
import com.example.school_management.repo.FinanceOfficerRepository;
import com.example.school_management.service.FinanceOfficerService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FinanceOfficerServiceImpl implements FinanceOfficerService {

    private final FinanceOfficerRepository financeOfficerRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public FinanceOfficerResponseDto createFinanceOfficer(FinanceOfficerRequestDto dto) {
        FinanceOfficer officer = mapToEntity(dto);
        officer.setPassword(passwordEncoder.encode(dto.getPassword()));
        officer.setRole(Role.FINANCE);
        officer.setActive(true);
        return mapToDto(financeOfficerRepository.save(officer));
    }


    @Override
    public List<FinanceOfficerResponseDto> getAllFinanceOfficers() {
        return financeOfficerRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .toList();
    }


    @Override
    public FinanceOfficerResponseDto getFinanceOfficerById(Long id) {
        return mapToDto(findById(id));
    }


    @Override
    public FinanceOfficerResponseDto updateFinanceOfficer(Long id, FinanceOfficerRequestDto dto) {
        FinanceOfficer officer = findById(id);

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


        if (dto.getPassword() != null && !dto.getPassword().isBlank()) {
            officer.setPassword(passwordEncoder.encode(dto.getPassword()));
        }

        return mapToDto(financeOfficerRepository.save(officer));
    }


    @Override
    public void toggleStatus(Long id) {
        FinanceOfficer officer = findById(id);
        officer.setActive(!officer.isActive());
        financeOfficerRepository.save(officer);
    }


    @Override
    public void deleteFinanceOfficer(Long id) {
        financeOfficerRepository.delete(findById(id));
    }


    private FinanceOfficer findById(Long id) {
        return financeOfficerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Finance Officer not found with id: " + id));
    }

    private FinanceOfficer mapToEntity(FinanceOfficerRequestDto dto) {
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
                .build();
    }

    private FinanceOfficerResponseDto mapToDto(FinanceOfficer officer) {
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
        dto.setActive(officer.isActive());
        dto.setRole(officer.getRole().name());
        return dto;
    }
}
