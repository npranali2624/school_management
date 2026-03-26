package com.example.school_management.repo;

import com.example.school_management.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TeacherRepo extends JpaRepository<Teacher, Long> {
    boolean existsByEmail(String email);
    boolean existsByMobile(String mobile);
    Optional<Teacher> findByEmail(String email);
    Optional<Teacher> findByMobile(String mobile);
}