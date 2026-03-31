package com.example.school_management.repo;

import com.example.school_management.entity.Assignment;
import com.example.school_management.enums.AssignmentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AssignmentRepository extends JpaRepository<Assignment, Long> {


    List<Assignment> findByStatusAndAllowLateSubmissionFalseAndDueDateBefore(
            AssignmentStatus status, LocalDateTime now);

    List<Assignment> findByStatusAndAllowLateSubmissionTrueAndLateSubmissionDueDateBefore(
            AssignmentStatus status, LocalDateTime now);
}
