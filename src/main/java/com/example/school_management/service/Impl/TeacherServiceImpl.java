package com.example.school_management.service.Impl;

import com.example.school_management.Mapper.TeacherMapper;
import com.example.school_management.dto.TeacherRequestDto;
import com.example.school_management.dto.TeacherResponseDto;
import com.example.school_management.entity.Teacher;
import com.example.school_management.enums.Role;
import com.example.school_management.repo.TeacherRepo;
import com.example.school_management.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepo teacherRepo;
    private final TeacherMapper teacherMapper;


    @Override
    public TeacherResponseDto createTeacher(TeacherRequestDto request) {

        if (teacherRepo.existsByEmail(request.getEmail())) {
            throw new DataIntegrityViolationException("Email already exists");
        }

        if (teacherRepo.existsByMobile(request.getMobile())) {
            throw new DataIntegrityViolationException("Mobile already exists");
        }


        Teacher teacher = teacherMapper.toEntity(request);


        teacher.setRole(Role.TEACHER);
        teacher.setActive(true);

        return teacherMapper.toResponseDto(teacherRepo.save(teacher));
    }


    @Override
    public TeacherResponseDto getTeacherById(Long id) {
        Teacher teacher = teacherRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Teacher not found with id: " + id));

        return teacherMapper.toResponseDto(teacher);
    }


    @Override
    public List<TeacherResponseDto> getAllTeachers() {
        return teacherMapper.toResponseDtoList(teacherRepo.findAll());
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


        teacherMapper.updateEntity(request, existing);

        return teacherMapper.toResponseDto(teacherRepo.save(existing));
    }


    @Override
    public void deleteTeacher(Long id) {
        if (!teacherRepo.existsById(id)) {
            throw new RuntimeException("Teacher not found with id: " + id);
        }
        teacherRepo.deleteById(id);
    }
}