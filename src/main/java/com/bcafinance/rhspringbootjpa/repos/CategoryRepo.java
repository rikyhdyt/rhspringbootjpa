package com.bcafinance.rhspringbootjpa.repos;/*
Created by IntelliJ IDEA 2022.2.3 (Community Edition)
Build #IC-222.4345.14, built on October 5, 2022
@Author Kyoto a.k.a Riky Hidayat
Java Developer
Created on 03/12/2022
@Last Modified 03/12/2022 17:44
Version 1.0
*/

import com.bcafinance.rhspringbootjpa.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepo extends JpaRepository<Category,Long> {

    Optional<Category> findByCatName(String catName);

    List<Category> findByCatNameContaining(String catName);

}
