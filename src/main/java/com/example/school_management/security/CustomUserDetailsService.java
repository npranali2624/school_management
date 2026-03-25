package com.example.school_management.security;



import com.example.school_management.entity.Employee;
import com.example.school_management.repo.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private EmployeeRepository repository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Employee emp = repository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return new User(
                emp.getEmail(),
                emp.getPassword(),
                List.of(new SimpleGrantedAuthority( "ROLE_"+emp.getRole().name()))
        );
    }
}