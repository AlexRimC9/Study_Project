package com.example.study_project.Entity;

import lombok.Data;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;



@Data
@Entity
@Table(name = "currencies")
public class Currencies {

    @Id
    private String name;

    private double bid;

    private String DateOfOperation;
}
