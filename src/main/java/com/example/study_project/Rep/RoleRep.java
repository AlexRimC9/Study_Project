package com.example.study_project.Rep;

import com.example.study_project.Entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRep extends JpaRepository<Role,Long> {
    Role findByRole(String role);
}
