package com.example.study_project;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@EnableJpaRepositories
@SpringBootApplication
public class StudyProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudyProjectApplication.class, args);
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    CommandLineRunner run(UserService userService) {
//        return args -> {
//            userService.saveRole(new Role(null,"ROLE_USER"));
//            userService.saveRole(new Role(null,"ROLE_MANAGER"));
//            userService.saveRole(new Role(null,"ROLE_ADMIN"));
//            userService.saveRole(new Role(null,"ROLE_SUPER_ADMIN"));
//
//            userService.saveUser(new User(null,"user","alex1","bobrov","1234",23,"none",new ArrayList<>()));
//            userService.saveUser(new User(null,"manager","alex2","bobrov","1234",23,"none",new ArrayList<>()));
//            userService.saveUser(new User(null,"admin","alex3","bobrov","1234",23,"none",new ArrayList<>()));
//            userService.saveUser(new User(null,"super_admin","alex4","bobrov","1234",23,"none",new ArrayList<>()));
//
//
//            userService.addRoleToUser("test", "ROLE_USER");
//            userService.addRoleToUser("user","ROLE_MANAGER");
//            userService.addRoleToUser("manager","ROLE_MANAGER");
//            userService.addRoleToUser("admin","ROLE_ADMIN");
//            userService.addRoleToUser("super_admin","ROLE_SUPER_ADMIN");
//            userService.addRoleToUser("super_admin","ROLE_ADMIN");
//            userService.addRoleToUser("super_admin","ROLE_USER");
//
//
//        };
//    }
}
