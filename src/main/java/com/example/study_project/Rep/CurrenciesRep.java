package com.example.study_project.Rep;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.study_project.Entity.Currencies;
import org.springframework.data.jpa.repository.Query;

public interface CurrenciesRep extends JpaRepository<Currencies,String> {

    Currencies findByName(String name);
    @Query(value = "select * from Currencies where name Like %fragmentName%",
        nativeQuery = true)
    Currencies findByNameLikeFragmentName(String fragmentName);

}
