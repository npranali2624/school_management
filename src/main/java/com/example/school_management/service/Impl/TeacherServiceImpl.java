package com.example.school_management.service.Impl;

import com.example.school_management.entity.Teacher;
import com.example.school_management.repo.TeacherRepo;
import com.example.school_management.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepo teacherRepo;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Teacher createTeacher(Teacher teacher) {
        // Check unique email and mobile
        if (teacherRepo.existsByEmail(teacher.getEmail())) {
            throw new DataIntegrityViolationException("Email already exists");
        }
        if (teacherRepo.existsByMobile(teacher.getMobile())) {
            throw new DataIntegrityViolationException("Mobile already exists");
        }

        // Encode password
        teacher.setPassword(passwordEncoder.encode(teacher.getPassword()));

        // Save teacher
        return teacherRepo.save(teacher);
    }
}