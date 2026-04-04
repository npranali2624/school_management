package com.example.school_management.service.Impl;

import com.example.school_management.dto.FinanceOfficerRequestDto;
import com.example.school_management.dto.FinanceOfficerResponseDto;
import com.example.school_management.entity.FinanceOfficer;
import com.example.school_management.enums.Role;
import com.example.school_management.Mapper.FinanceOfficerMapper;
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
    private final FinanceOfficerMapper financeOfficerMapper;

    @Override
    public FinanceOfficerResponseDto createFinanceOfficer(FinanceOfficerRequestDto dto) {
        FinanceOfficer officer = financeOfficerMapper.toEntity(dto);
        officer.setPassword(passwordEncoder.encode(dto.getPassword()));
        officer.setRole(Role.FINANCE);
        officer.setActive(true);
        return financeOfficerMapper.toResponseDto(financeOfficerRepository.save(officer));
    }

    @Override
    public List<FinanceOfficerResponseDto> getAllFinanceOfficers() {
        return financeOfficerMapper.toResponseDtoList(financeOfficerRepository.findAll());
    }

    @Override
    public FinanceOfficerResponseDto getFinanceOfficerById(Long id) {
        return financeOfficerMapper.toResponseDto(findById(id));
    }

    @Override
    public FinanceOfficerResponseDto updateFinanceOfficer(Long id, FinanceOfficerRequestDto dto) {
        FinanceOfficer officer = findById(id);

        financeOfficerMapper.updateEntity(dto, officer);

        if (dto.getPassword() != null && !dto.getPassword().isBlank()) {
            officer.setPassword(passwordEncoder.encode(dto.getPassword()));
        }

        return financeOfficerMapper.toResponseDto(financeOfficerRepository.save(officer));
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
}