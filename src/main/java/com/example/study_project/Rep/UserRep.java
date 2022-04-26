package com.example.study_project.Rep;

import com.example.study_project.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRep extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
