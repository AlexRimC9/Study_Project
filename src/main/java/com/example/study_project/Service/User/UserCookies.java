package com.example.study_project.Service.User;

import com.example.study_project.Entity.User;
import com.example.study_project.Rep.PasswordsRep;
import com.example.study_project.Rep.RoleRep;
import com.example.study_project.Rep.UserRep;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserCookies {

    private final UserRep userRep;




    public String choiceCity(String city) {
        User user = userRep.findByUsername((String) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        user.setCity(city);
        userRep.save(user);
        return city;
    }

    public String choiceLanguage(String language) {
        User user = userRep.findByUsername((String) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        user.setCity(language);
        userRep.save(user);
        return language;
    }
}
