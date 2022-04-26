package com.example.study_project.Service.User;

import com.example.study_project.Entity.Role;
import com.example.study_project.Entity.User;
import com.example.study_project.Rep.RoleRep;
import com.example.study_project.Rep.UserRep;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserRep userRep;
    private final RoleRep roleRep;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = userRep.findByUsername(login);
        if(user == null) {
            log.error("Юзер не найден в БД");
            throw new UsernameNotFoundException("Юзер не найден в БД");
        }else{
            log.info("Юзер найден в бд: {}",login);
        }
        Collection<SimpleGrantedAuthority> authorties = new ArrayList<>();
        user.getRole().forEach(role -> authorties.add(new SimpleGrantedAuthority(role.getRole())));
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorties);
    }

    @Override
    public User saveUser(User user) {
        log.info("Сохранили нового пользователя {} в базу данных", user.getFirstName());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRep.save(user);
    }

    @Override
    public User register(String username, String password){
        log.info("Новый пользователь {} зарегистрировался в базе данных", username);
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
         return userRep.save(user);
    }


//    @Override
//    public User survey(User user) {
//        log.info("Добавление данных пользователя {} прошло успешно", user.getUsername());
//
//    }

    @Override
    public Role saveRole(Role role) {
        log.info("Сохранение новой роли {} в базу данных",role.getRole());
        return roleRep.save(role);
    }

    @Override
    public void addRoleToUser(String login, String roleName) {
        User user = userRep.findByUsername(login);
        Role role = roleRep.findByRole(roleName);
        log.info("Добавили роль {} для пользователя {}",role.getRole(),user.getFirstName());
        user.getRole().add(role);
    }

    @Override
    public User getUser(String login) {
        log.info("Получили пользователя {}", login);
        return userRep.findByUsername(login);
    }

    @Override
    public List<User> getUsers() {
        log.info("Получили всех пользователей");
        return userRep.findAll();
    }


}