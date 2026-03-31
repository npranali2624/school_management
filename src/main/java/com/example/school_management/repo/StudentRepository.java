package com.example.school_management.repo;

import com.example.school_management.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    Optional<Student> findByRollNumber(Integer rollNumber);

    Optional<Student> findByAadharNo(String aadharNo);

    boolean existsByAadharNo(String aadharNo);

    boolean existsByRollNumber(Integer rollNumber);

    List<Student> findAllByIsActiveTrue();

    List<Student> findAllByIsActiveFalse();
}
