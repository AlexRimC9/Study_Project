package com.example.study_project.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Data
@Entity
@Table(name = "user_list" , schema = "public")
@NoArgsConstructor
@AllArgsConstructor
public class User  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Long idUsers;

    @Column
    private String username;

    @Column
    private String firstName;

    @Column
    private String secondName;

    @Column
    private String password;
    @Column
    private String age;

    @Column
    private String imageUrl;

    @Column
    private String language;

    @Column
    private String city;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_role",
            joinColumns = {@JoinColumn(name = "id_user")},
            inverseJoinColumns = {@JoinColumn(name = "id_role")}
    )
    private Collection<Role> role = new ArrayList<>();
}
