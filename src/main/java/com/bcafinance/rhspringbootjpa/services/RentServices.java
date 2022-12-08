package com.bcafinance.rhspringbootjpa.services;

import com.bcafinance.rhspringbootjpa.models.Rent;
import com.bcafinance.rhspringbootjpa.repos.RentRepo;
import com.bcafinance.rhspringbootjpa.utils.CsvReaderRent;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/*
Created by IntelliJ IDEA 2022.2.3 (Community Edition)
Build #IC-222.4345.14, built on October 5, 2022
@Author Kyoto a.k.a Riky Hidayat
Java Developer
Created on 06/12/2022
@Last Modified 06/12/2022 11:02
Version 1.0
*/
@Service
@Transactional
public class RentServices {

    @Getter
    private RentRepo rentRepo;

    @Autowired
    public RentServices(RentRepo rentRepo){this.rentRepo=rentRepo;}

    @Transactional (rollbackFor = Exception.class)
    public List<Rent> saveRent(MultipartFile multipartFile) throws Exception
    {
        try{
            List<Rent> lsRent = CsvReaderRent.csvToRentData(multipartFile.getInputStream());
            return rentRepo.saveAll(lsRent);
        }catch (Exception e)
        {
            throw new Exception(e.getMessage());
        }
    }

    public Iterable<Rent> pagingFindByCarYearGreaterThanEqualOrCostLessThanEqual(Integer carYear, Double cost, Pageable pageable)
    {
        return rentRepo.findByCarYearGreaterThanEqualAndCostLessThanEqual(carYear, cost, pageable);
    }

    public Iterable<Rent> findByRent(String columnName, String value, Pageable pageable) {
        if (columnName.equalsIgnoreCase("CarModel")) {
            return rentRepo.findByCarModelContaining(value, pageable);
        } else if (columnName.equalsIgnoreCase("CarUser")) {
            return rentRepo.findByCarUserContaining(value, pageable);
        } else if (columnName.equalsIgnoreCase("CarNumber")) {
            Integer valueInt = Integer.valueOf(value);
            return rentRepo.findByCarNumber(valueInt, pageable);
        }else {
            return null;
        }
    }



}
