package com.example.study_project.Rep;

import com.example.study_project.Entity.Passwords;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PasswordsRep extends JpaRepository<Passwords,String> {
    Passwords findByUsernameAndUsed(String username,boolean used);
    List<Passwords> findAll();
}
