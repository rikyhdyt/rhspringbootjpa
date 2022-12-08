package com.bcafinance.rhspringbootjpa.repos;
/*
Created by IntelliJ IDEA 2022.2.3 (Community Edition)
Build #IC-222.4345.14, built on October 5, 2022
@Author Kyoto a.k.a Riky Hidayat
Java Developer
Created on 06/12/2022
@Last Modified 06/12/2022 10:45
Version 1.0
*/

import com.bcafinance.rhspringbootjpa.models.Rent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentRepo extends JpaRepository<Rent, Long> {

    Page<Rent> findByCarYearGreaterThanEqualAndCostLessThanEqual(Integer carYear, Double cost, Pageable pageable);

    Page<Rent> findByCarModelContaining(Object value, Pageable pageable);

    Page<Rent> findByCarUserContaining(Object value, Pageable pageable);

    Page<Rent> findByCarNumber(Integer value, Pageable pageable);


}
