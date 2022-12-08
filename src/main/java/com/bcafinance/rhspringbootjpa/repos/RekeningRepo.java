package com.bcafinance.rhspringbootjpa.repos;
/*
Created by IntelliJ IDEA 2022.2.3 (Community Edition)
Build #IC-222.4345.14, built on October 5, 2022
@Author Kyoto a.k.a Riky Hidayat
Java Developer
Created on 07/12/2022
@Last Modified 07/12/2022 10:52
Version 1.0
*/

import com.bcafinance.rhspringbootjpa.models.Rekening;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.Optional;

public interface RekeningRepo extends JpaRepository<Rekening, Long> {

    Optional<Rekening> findByRekNum(String rekNum);

    Optional<Rekening> findByRekNumAndBalanceLessThanEqual(String rekNum, Double balance);

    @Modifying
    @Query(value = "insert into MstRekening (RekNum, Balance, CreatedBy, CreatedDate, IsActive)" +
            "values(:RekNum, :Balance, :CreatedBy, :CreatedDate, :IsActive)", nativeQuery = true)
    void insertRekening(@Param("RekNum") String rekNum, @Param("Balance") Double balance,
                        @Param("CreatedBy") String createdBy, @Param("CreatedDate") Date createdDate,
    @Param("IsActive") boolean isActive);

}
