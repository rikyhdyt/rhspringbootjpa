package com.bcafinance.rhspringbootjpa.repos;/*
Created by IntelliJ IDEA 2022.2.3 (Community Edition)
Build #IC-222.4345.14, built on October 5, 2022
@Author Kyoto a.k.a Riky Hidayat
Java Developer
Created on 05/12/2022
@Last Modified 05/12/2022 10:54
Version 1.0
*/

import com.bcafinance.rhspringbootjpa.models.Airport;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AirportRepo extends JpaRepository<Airport, Long> {

    Page<Airport> findAirportByNameContaining(String name, Pageable pageable);
    Page<Airport> findAirportByNameContainingOrMuncipalityContaining(String name, String muncipality, Pageable pageable);
//    Page<Airport> findAll(Pageable pageable);
}
