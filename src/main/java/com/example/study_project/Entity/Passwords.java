package com.example.study_project.Entity;


import lombok.Data;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "password")
public class Passwords {


    private String username;
    @Id
    private String password;
    private boolean used;
}
