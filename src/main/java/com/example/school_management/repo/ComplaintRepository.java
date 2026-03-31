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

    List<Complaint> findByStudentId(Long studentId);

    List<Complaint> findByTeacherId(Long teacherId);

    List<Complaint> findByStatus(ComplaintStatus status);

    List<Complaint> findByPriority(Priority priority);

    List<Complaint> findByCategory(ComplaintCategory category);

    List<Complaint> findByStudentIdAndStatus(Long studentId, ComplaintStatus status);

    List<Complaint> findByTeacherIdAndStatus(Long teacherId, ComplaintStatus status);
}