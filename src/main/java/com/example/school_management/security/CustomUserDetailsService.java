package com.example.school_management.security;

import com.example.school_management.entity.Admin;
import com.example.school_management.entity.Employee;
import com.example.school_management.entity.Parent;
import com.example.school_management.repo.AdminRepository;
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
    @Autowired
    private AdminRepository adminRepository;


    @Override
    public UserDetails loadUserByUsername(String identifier) throws UsernameNotFoundException {


        var adminOpt = adminRepository.findByUsername(identifier);
        if (adminOpt.isPresent()) {
            Admin admin = adminOpt.get();
            return buildUser(admin.getUsername(), admin.getPassword(), "ADMIN");
        }


        var empOpt = employeeRepository.findByEmail(identifier)
                .or(() -> employeeRepository.findByMobile(identifier));
        if (empOpt.isPresent()) {
            Employee emp = empOpt.get();
            return buildUser(identifier, emp.getPassword(), emp.getRole().name());
        }


        var parentOpt = parentRepository.findByEmail(identifier)
                .or(() -> parentRepository.findByMobilePrimary(identifier));
        if (parentOpt.isPresent()) {
            Parent parent = parentOpt.get();
            return buildUser(identifier, parent.getPassword(), "PARENT");
        }

        throw new UsernameNotFoundException("No user found: " + identifier);
    }

    private UserDetails buildUser(String identifier, String password, String role) {
        return new User(
                identifier,
                password,
                List.of(new SimpleGrantedAuthority("ROLE_" + role))
        );
    }
}