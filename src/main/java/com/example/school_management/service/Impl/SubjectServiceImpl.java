package com.example.school_management.service.Impl;

import com.example.school_management.dto.SubjectRequestDto;
import com.example.school_management.dto.SubjectResponseDto;
import com.example.school_management.entity.Subject;
import com.example.school_management.enums.SubjectType;
import com.example.school_management.Mapper.SubjectMapper;
import com.example.school_management.repo.SubjectRepository;
import com.example.school_management.service.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubjectServiceImpl implements SubjectService {

    private final SubjectRepository subjectRepository;
    private final SubjectMapper subjectMapper;

    @Override
    public SubjectResponseDto createSubject(SubjectRequestDto request) {
        if (request.getSubjectType() == SubjectType.OPTIONAL
                && request.getStandard() == null) {
            throw new IllegalArgumentException(
                    "Standard is required for OPTIONAL subjects");
        }
        if (subjectRepository.existsBySubjectCode(request.getSubjectCode())) {
            throw new IllegalArgumentException(
                    "Subject with code " + request.getSubjectCode() + " already exists");
        }

        return subjectMapper.toResponseDto(
                subjectRepository.save(subjectMapper.toEntity(request)));
    }

    @Override
    public List<SubjectResponseDto> getAllSubjects() {
        return subjectMapper.toResponseDtoList(subjectRepository.findAll());
    }

    @Override
    public SubjectResponseDto getSubjectByCode(Integer subjectCode) {
        return subjectMapper.toResponseDto(findByCode(subjectCode));
    }

    @Override
    public SubjectResponseDto updateSubject(Integer subjectCode, SubjectRequestDto request) {
        Subject existing = findByCode(subjectCode);

        if (request.getSubjectType() == SubjectType.OPTIONAL
                && request.getStandard() == null) {
            throw new IllegalArgumentException(
                    "Standard is required for OPTIONAL subjects");
        }

        subjectMapper.updateEntity(request, existing);
        return subjectMapper.toResponseDto(subjectRepository.save(existing));
    }

    @Override
    public void deleteSubject(Integer subjectCode) {
        subjectRepository.deleteById(findByCode(subjectCode).getId());
    }

    // -------------------------------------------------------------------------
    // Private helpers
    // -------------------------------------------------------------------------

    private Subject findByCode(Integer subjectCode) {
        return subjectRepository.findBySubjectCode(subjectCode)
                .orElseThrow(() -> new RuntimeException(
                        "Subject not found with code: " + subjectCode));
    }
}