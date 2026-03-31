package com.example.school_management.service.Impl;

import com.example.school_management.entity.Subject;
import com.example.school_management.entity.Teacher;
import com.example.school_management.repo.SubjectRepository;
import com.example.school_management.repo.TeacherRepo;
import com.example.school_management.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepo teacherRepo;
    private final PasswordEncoder passwordEncoder;
    private final SubjectRepository subjectRepository;

    @Override
    public Teacher createTeacher(Teacher teacher) {
        if (teacherRepo.existsByEmail(teacher.getEmail())) {
            throw new DataIntegrityViolationException("Email already exists");
        }
        if (teacherRepo.existsByMobile(teacher.getMobile())) {
            throw new DataIntegrityViolationException("Mobile already exists");
        }

        teacher.setPassword(passwordEncoder.encode(teacher.getPassword()));

        if (teacher.getSpecialization() != null && teacher.getSpecialization().getId() != null) {
            Subject specialization = subjectRepository.findById(
                    teacher.getSpecialization().getId()
            ).orElseThrow(() -> new RuntimeException("Subject not found"));
            teacher.setSpecialization(specialization);
        }

        return teacherRepo.save(teacher);
    }

    @Override
    public Teacher getTeacherById(Long id) {
        return teacherRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Teacher not found with id: " + id));
    }

    @Override
    public List<Teacher> getAllTeachers() {
        return teacherRepo.findAll();
    }

    @Override
    public Teacher updateTeacher(Long id, Teacher updatedTeacher) {
        Teacher existing = teacherRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Teacher not found with id: " + id));


        if (!existing.getEmail().equals(updatedTeacher.getEmail()) &&
                teacherRepo.existsByEmail(updatedTeacher.getEmail())) {
            throw new DataIntegrityViolationException("Email already exists");
        }

        // Check mobile uniqueness only if changed
        if (!existing.getMobile().equals(updatedTeacher.getMobile()) &&
                teacherRepo.existsByMobile(updatedTeacher.getMobile())) {
            throw new DataIntegrityViolationException("Mobile already exists");
        }

        existing.setEmail(updatedTeacher.getEmail());
        existing.setMobile(updatedTeacher.getMobile());

        // Only re-encode if password was actually changed
        if (updatedTeacher.getPassword() != null && !updatedTeacher.getPassword().isBlank()) {
            existing.setPassword(passwordEncoder.encode(updatedTeacher.getPassword()));
        }

        if (updatedTeacher.getSpecialization() != null && updatedTeacher.getSpecialization().getId() != null) {
            Subject specialization = subjectRepository.findById(
                    updatedTeacher.getSpecialization().getId()
            ).orElseThrow(() -> new RuntimeException("Subject not found"));
            existing.setSpecialization(specialization);
        }

        return teacherRepo.save(existing);
    }

    @Override
    public void deleteTeacher(Long id) {
        if (!teacherRepo.existsById(id)) {
            throw new RuntimeException("Teacher not found with id: " + id);
        }
        teacherRepo.deleteById(id);
    }
}