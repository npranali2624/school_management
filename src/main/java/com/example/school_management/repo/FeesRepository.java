package com.example.school_management.repo;

import com.example.school_management.entity.Fees;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface FeesRepository extends JpaRepository<Fees, Long>
{
    List<Fees> findByStd(String std);
    List<Fees> findByAcademicYear(String academicYear);
    List<Fees> findByStdAndAcademicYear(String std, String academicYear);
}