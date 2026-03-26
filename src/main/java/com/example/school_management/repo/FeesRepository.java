package com.example.school_management.repo;

import com.example.school_management.entity.Fees;
import com.example.school_management.enums.Standard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface FeesRepository extends JpaRepository<Fees, Long>
{
    List<Fees> findByStd(Standard std);;
    List<Fees> findByAcademicYear(String academicYear);
    List<Fees> findByStdAndAcademicYear(Standard std, String academicYear);
}