package com.example.school_management.repo;

import com.example.school_management.entity.Complaint;
import com.example.school_management.enums.ComplaintCategory;
import com.example.school_management.enums.ComplaintStatus;
import com.example.school_management.enums.Priority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ComplaintRepository extends JpaRepository<Complaint, Long> {

    // Find all complaints by student
    List<Complaint> findByStudentId(Long studentId);

    // Find all complaints by teacher
    List<Complaint> findByTeacherId(Long teacherId);

    // Find by status
    List<Complaint> findByStatus(ComplaintStatus status);

    // Find by priority
    List<Complaint> findByPriority(Priority priority);

    // Find by category
    List<Complaint> findByCategory(ComplaintCategory category);

    // Find by student + status
    List<Complaint> findByStudentIdAndStatus(Long studentId, ComplaintStatus status);

    // Find by teacher + status
    List<Complaint> findByTeacherIdAndStatus(Long teacherId, ComplaintStatus status);
}