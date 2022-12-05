package com.bcafinance.rhspringbootjpa.repos;
/*
Created by IntelliJ IDEA 2022.2.3 (Community Edition)
Build #IC-222.4345.14, built on October 5, 2022
@Author Kyoto a.k.a Riky Hidayat
Java Developer
Created on 04/12/2022
@Last Modified 04/12/2022 20:11
Version 1.0
*/

import com.bcafinance.rhspringbootjpa.models.Customers;
import com.bcafinance.rhspringbootjpa.models.Registers;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RegisRepo extends JpaRepository<Registers, Long> {

    Optional<Registers> findByToken(String token);


}
