package com.bcafinance.rhspringbootjpa.services;/*
Created by IntelliJ IDEA 2022.2.3 (Community Edition)
Build #IC-222.4345.14, built on October 5, 2022
@Author Kyoto a.k.a Riky Hidayat
Java Developer
Created on 05/12/2022
@Last Modified 05/12/2022 10:57
Version 1.0
*/

import com.bcafinance.rhspringbootjpa.models.Airport;
import com.bcafinance.rhspringbootjpa.repos.AirportRepo;
import com.bcafinance.rhspringbootjpa.utils.CsvReader;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@Transactional
public class AirportService {

    @Getter
    private AirportRepo airportRepo;

    @Autowired
    public AirportService(AirportRepo airportRepo){this.airportRepo=airportRepo;}

    @Transactional (rollbackFor = Exception.class)
    public List<Airport> saveAirport(MultipartFile multipartFile) throws Exception
    {
        try{
            List<Airport> lsAirport = CsvReader.csvToAirportData(multipartFile.getInputStream());
            return airportRepo.saveAll(lsAirport);
        }catch (Exception e)
        {
            throw new Exception(e.getMessage());
        }
    }

    public List<Airport> findAllAirport()
    {
        return (List<Airport>)airportRepo.findAll();
    }

    public Iterable<Airport> pagingFindAirportByName(String name, Pageable pageable)
    {
        return airportRepo.findAirportByNameContaining(name,pageable);
    }

    public Iterable<Airport> pagingFindAirportByNameOrMuncipality(String name, String muncipality, Pageable pageable)
    {
        return airportRepo.findAirportByNameContainingOrMuncipalityContaining(name,muncipality, pageable);
    }

    public Iterable<Airport> pagingFindAallAirport(Pageable pageable)
    {
        return airportRepo.findAll(pageable);
    }

}
