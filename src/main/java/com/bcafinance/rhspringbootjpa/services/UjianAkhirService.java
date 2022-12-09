package com.bcafinance.rhspringbootjpa.services;

import com.bcafinance.rhspringbootjpa.models.Airport;
import com.bcafinance.rhspringbootjpa.models.Cars;
import com.bcafinance.rhspringbootjpa.models.Rekening;
import com.bcafinance.rhspringbootjpa.models.UjianAkhir;
import com.bcafinance.rhspringbootjpa.repos.RekeningRepo;
import com.bcafinance.rhspringbootjpa.repos.UjianAkhirRepo;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/*
Created by IntelliJ IDEA 2022.2.3 (Community Edition)
Build #IC-222.4345.14, built on October 5, 2022
@Author Kyoto a.k.a Riky Hidayat
Java Developer
Created on 09/12/2022
@Last Modified 09/12/2022 13:40
Version 1.0
*/
@Service
@Transactional
public class UjianAkhirService {

    @Getter
    private UjianAkhirRepo ujianAkhirRepo;

    @Autowired
    public UjianAkhirService(UjianAkhirRepo ujianAkhirRepo){this.ujianAkhirRepo=ujianAkhirRepo;}

    @Transactional(rollbackFor = Exception.class)
    public void saveUjianAkhir(UjianAkhir ujianAkhir) throws Exception{
        ujianAkhirRepo.insertUjianAkhir(ujianAkhir.getVar1(), ujianAkhir.getVar2(),ujianAkhir.getVar3());
    }

    @Transactional(rollbackFor = {Exception.class})
    public void saveAllUjianAkhir(List<UjianAkhir> ls)
    {
        ujianAkhirRepo.saveAll(ls);
    }

    public Iterable<UjianAkhir> pagingFindUjianAkhir(Pageable pageable)
    {
        return ujianAkhirRepo.findAll(pageable);
    }


}
