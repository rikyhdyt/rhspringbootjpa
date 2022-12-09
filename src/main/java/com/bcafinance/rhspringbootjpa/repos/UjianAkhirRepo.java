package com.bcafinance.rhspringbootjpa.repos;/*
Created by IntelliJ IDEA 2022.2.3 (Community Edition)
Build #IC-222.4345.14, built on October 5, 2022
@Author Kyoto a.k.a Riky Hidayat
Java Developer
Created on 09/12/2022
@Last Modified 09/12/2022 13:35
Version 1.0
*/

import com.bcafinance.rhspringbootjpa.models.Airport;
import com.bcafinance.rhspringbootjpa.models.UjianAkhir;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;

public interface UjianAkhirRepo extends JpaRepository<UjianAkhir,Long> {

    @Modifying
    @Query(value = "insert into UjianAKhir (Var1, Var2, Var3)" +
            "values(:Var1, :Var2, :Var3)", nativeQuery = true)
    void insertUjianAkhir(@Param("Var1") Integer var1, @Param("Var2") Double var2, @Param("Var3") String var3
                        );

    @Override
    Page<UjianAkhir> findAll(Pageable pageable);
}
