package com.example.school_management.service.Impl;

import com.example.school_management.dto.StudentRequestDto;
import com.example.school_management.dto.StudentResponseDto;
import com.example.school_management.entity.Student;
import com.example.school_management.exception.DuplicateResourceException;
import com.example.school_management.exception.ResourceNotFoundException;
import com.example.school_management.Mapper.StudentMapper;
import com.example.school_management.repo.StudentRepository;
import com.example.school_management.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    @Override
    public StudentResponseDto admitStudent(StudentRequestDto request) {
        if (studentRepository.existsByAadharNo(request.getAadharNo())) {
            throw new DuplicateResourceException(
                    "Student with Aadhar " + request.getAadharNo() + " already exists");
        }

        Student student = studentMapper.toEntity(request);
        student.setActive(true);

        return studentMapper.toResponseDto(studentRepository.save(student));
    }

    @Override
    public List<StudentResponseDto> getAllStudents() {
        return studentMapper.toResponseDtoList(studentRepository.findAll());
    }

    @Override
    public StudentResponseDto getStudentById(Long id) {
        return studentMapper.toResponseDto(findById(id));
    }

    @Override
    public StudentResponseDto getStudentByRollNumber(Integer rollNumber) {
        Student student = studentRepository.findByRollNumber(rollNumber)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Student not found with Roll Number: " + rollNumber));
        return studentMapper.toResponseDto(student);
    }

    @Override
    public StudentResponseDto updateStudent(Long id, StudentRequestDto request) {
        Student existing = findById(id);
        studentMapper.updateEntity(existing, request);
        return studentMapper.toResponseDto(studentRepository.save(existing));
    }

    @Override
    public void toggleStudentStatus(Long id) {
        Student student = findById(id);
        student.setActive(!student.isActive());
        studentRepository.save(student);
    }

    @Override
    public void deleteStudent(Long id) {
        if (!studentRepository.existsById(id)) {
            throw new ResourceNotFoundException("Student not found with ID: " + id);
        }
        studentRepository.deleteById(id);
    }

    // -------------------------------------------------------------------------
    // Private helpers
    // -------------------------------------------------------------------------

    private Student findById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Student not found with ID: " + id));
    }
}