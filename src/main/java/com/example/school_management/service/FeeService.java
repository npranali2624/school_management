package com.example.school_management.service;

import com.example.school_management.dto.FeesRequestDto;
import com.example.school_management.dto.FeesResponseDto;
import com.example.school_management.entity.Fees;
import com.example.school_management.Mapper.FeesMapper;
import com.example.school_management.enums.Standard;
import com.example.school_management.repo.FeesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FeeService
{
    private final FeesRepository feesRepository;
    private final FeesMapper feesMapper;

    @Transactional
    public FeesResponseDto createFees(FeesRequestDto requestDTO) {
        Fees fees = feesMapper.toEntity(requestDTO);
        Fees saved = feesRepository.save(fees);
        return feesMapper.toResponseDTO(saved);
    }
    public FeesResponseDto getFeesById(Long id) {
        Fees fees = feesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Fees record not found with id: " + id));
        return feesMapper.toResponseDTO(fees);
    }
    public List<FeesResponseDto> getAllFees() {
        return feesRepository.findAll()
                .stream()
                .map(feesMapper::toResponseDTO)
                .toList();
    }
    public List<FeesResponseDto> getFeesByStd(Standard std) {
        return feesRepository.findByStd(std)
                .stream()
                .map(feesMapper::toResponseDTO)
                .toList();
    }
    public List<FeesResponseDto> getFeesByStdAndYear(Standard std, String academicYear) {
        return feesRepository.findByStdAndAcademicYear(std, academicYear)
                .stream()
                .map(feesMapper::toResponseDTO)
                .toList();
    }
    @Transactional
    public FeesResponseDto markAsPaid(Long id) {
        Fees fees = feesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Fees record not found with id: " + id));

        if (fees.isPaid()) {
            throw new RuntimeException("Fees with id " + id + " are already marked as paid");
        }

        fees.markAsPaid();
        Fees saved = feesRepository.save(fees);
        return feesMapper.toResponseDTO(saved);
    }
    @Transactional
    public void deleteFees(Long id) {
        if (!feesRepository.existsById(id)) {
            throw new RuntimeException("Fees record not found with id: " + id);
        }
        feesRepository.deleteById(id);
    }
}