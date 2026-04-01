package com.example.school_management.security;

import com.example.school_management.entity.Employee;
import com.example.school_management.entity.Parent;
import com.example.school_management.repo.EmployeeRepository;
import com.example.school_management.repo.ParentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ParentRepository parentRepository;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {


        var empOpt = employeeRepository.findByEmail(email);
        if (empOpt.isPresent()) {
            Employee emp = empOpt.get();
            return buildUser(emp.getEmail(), emp.getPassword(), emp.getRole().name());
        }


        var parentOpt = parentRepository.findByEmail(email);
        if (parentOpt.isPresent()) {
            Parent parent = parentOpt.get();
            return buildUser(parent.getEmail(), parent.getPassword(), "PARENT");
        }

        throw new UsernameNotFoundException("No user found with email: " + email);
    }

    private UserDetails buildUser(String email, String password, String role) {
        return new User(
                email,
                password,
                List.of(new SimpleGrantedAuthority("ROLE_" + role))
        );
    }
}