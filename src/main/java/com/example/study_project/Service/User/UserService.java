package com.example.study_project.Service.User;

import com.example.study_project.Entity.Role;
import com.example.study_project.Entity.User;

import java.util.List;

public interface UserService {
    abstract User saveUser(User user);

    User survey(String firstName, String secondName, int age, String imageUrl);

    Role saveRole(Role role);

    void addRoleToUser(String login,String roleName);

    User getUser(String login);

    List<User> getUsers();

    User register(String username,String password);

}
