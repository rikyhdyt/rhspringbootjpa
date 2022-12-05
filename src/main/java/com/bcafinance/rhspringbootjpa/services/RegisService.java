package com.bcafinance.rhspringbootjpa.services;
/*
Created by IntelliJ IDEA 2022.2.3 (Community Edition)
Build #IC-222.4345.14, built on October 5, 2022
@Author Kyoto a.k.a Riky Hidayat
Java Developer
Created on 04/12/2022
@Last Modified 04/12/2022 20:12
Version 1.0
*/

import com.bcafinance.rhspringbootjpa.handler.ResourceNotFoundException;
import com.bcafinance.rhspringbootjpa.models.Registers;
import com.bcafinance.rhspringbootjpa.repos.RegisRepo;
import com.bcafinance.rhspringbootjpa.utils.ConstantMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional

public class RegisService {

    private RegisRepo regisRepo;

    @Autowired
    public RegisService(RegisRepo regisRepo) {this.regisRepo=regisRepo;}

    public void saveRegis(Registers registers) throws Exception{
//        if (registers.getEmail()==null)throw new DataIntegrityViolationException(ConstantMessage.ERROR_DATA_INVALID);
//        if (registers.getGender()==null)throw new DataIntegrityViolationException(ConstantMessage.ERROR_DATA_INVALID);
//        if (registers.getPostCode()==null)throw new DataIntegrityViolationException(ConstantMessage.ERROR_DATA_INVALID);
        regisRepo.save(registers);
    }

    public Registers findByToken(String token) throws Exception
    {
        return regisRepo.findByToken(token).
                orElseThrow(() -> new ResourceNotFoundException(ConstantMessage.WARNING_EMAIL_NOT_FOUND));
    }
}
