package com.example.school_management.repo;

import com.example.school_management.entity.FinanceOfficer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FinanceOfficerRepository extends JpaRepository<FinanceOfficer, Long> {

    Optional<FinanceOfficer> findByEmail(String email);
}