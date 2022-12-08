package com.bcafinance.rhspringbootjpa.services;

import com.bcafinance.rhspringbootjpa.dto.RekeningDTO;
import com.bcafinance.rhspringbootjpa.handler.ResourceNotFoundException;
import com.bcafinance.rhspringbootjpa.models.Rekening;
import com.bcafinance.rhspringbootjpa.repos.RekeningRepo;
import com.bcafinance.rhspringbootjpa.utils.ConstantMessage;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

/*
Created by IntelliJ IDEA 2022.2.3 (Community Edition)
Build #IC-222.4345.14, built on October 5, 2022
@Author Kyoto a.k.a Riky Hidayat
Java Developer
Created on 07/12/2022
@Last Modified 07/12/2022 10:54
Version 1.0
*/
@Service
@Transactional
public class RekeningService {

    @Getter
    private RekeningRepo rekeningRepo;

    @Autowired
    public RekeningService(RekeningRepo rekeningRepo){this.rekeningRepo=rekeningRepo;}

    @Transactional(rollbackFor = Exception.class)
    public void saveRekening(Rekening rekening) throws Exception{
        if (rekening.getRekNum()==null)throw new DataIntegrityViolationException(ConstantMessage.ERROR_DATA_INVALID);
        if (rekening.getBalance()==null)throw new DataIntegrityViolationException(ConstantMessage.ERROR_DATA_INVALID);

        Optional<Rekening> rekByNum = rekeningRepo.findByRekNum(rekening.getRekNum());
        if (rekByNum.isPresent())
        {
            throw new ResourceNotFoundException(ConstantMessage.WARNING_REK_EXIST);
        }
        rekeningRepo.save(rekening);
    }

    @Transactional(rollbackFor = Exception.class)
    public void saveRekeningQuery(Rekening rekening) throws Exception{
        rekeningRepo.insertRekening(rekening.getRekNum(), rekening.getBalance(),
                "Riky",new Date(), true);
        rekeningRepo.save(rekening);
    }

    public Optional<Rekening> validationRekNumAndBalance(String rekNum, Double balance)
    {
        return rekeningRepo.findByRekNumAndBalanceLessThanEqual(rekNum, balance);
    }

    @Transactional(rollbackFor = Exception.class)
    public void updateRekening(RekeningDTO transfer) throws Exception{

        Optional<Rekening> rekSumber = rekeningRepo.findByRekNum(transfer.getRekNumSumber());
        if (rekSumber.isEmpty()){
            throw new ResourceNotFoundException(ConstantMessage.WARNING_REK_SUMBER_NOT_EXIST);
        }
        Optional<Rekening> cekSaldo = rekeningRepo.findByRekNumAndBalanceLessThanEqual(transfer.getRekNumSumber(), transfer.getAmount());
        if (cekSaldo.isPresent()){
            throw new ResourceNotFoundException(ConstantMessage.WARNING_CEK_FAILED);
        }
        Optional<Rekening> rekPenerima = rekeningRepo.findByRekNum(transfer.getRekNumPenerima());
        if (rekPenerima.isEmpty()){
            throw new ResourceNotFoundException(ConstantMessage.WARNING_REK_PENERIMA_NOT_EXIST);
        }else {
            try {
                rekSumber.get().setBalance(rekSumber.get().getBalance()-transfer.getAmount());
                rekPenerima.get().setBalance(rekPenerima.get().getBalance()+transfer.getAmount());
            }catch (Exception e){
                throw new ResourceNotFoundException(ConstantMessage.WARNING_FAIL_TRANSACTION);
            }

        }

    }


}
