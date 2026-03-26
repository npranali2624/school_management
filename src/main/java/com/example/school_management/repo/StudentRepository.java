package com.example.school_management.repo;

import com.example.school_management.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    // Find student by roll number
    Optional<Student> findByRollNumber(Integer rollNumber);

    // Find student by aadhar number
    Optional<Student> findByAadharNo(String aadharNo);

    // Check if aadhar already exists (for validation)
    boolean existsByAadharNo(String aadharNo);

    // Check if roll number already exists
    boolean existsByRollNumber(Integer rollNumber);

    // Get all active students
    List<Student> findAllByIsActiveTrue();

    // Get all inactive students
    List<Student> findAllByIsActiveFalse();
}
