package com.bcafinance.rhspringbootjpa.controllers;

import com.bcafinance.rhspringbootjpa.dto.RekeningDTO;
import com.bcafinance.rhspringbootjpa.handler.ResourceNotFoundException;
import com.bcafinance.rhspringbootjpa.handler.ResponseHandler;
import com.bcafinance.rhspringbootjpa.models.Cars;
import com.bcafinance.rhspringbootjpa.models.Rekening;
import com.bcafinance.rhspringbootjpa.services.RekeningService;
import com.bcafinance.rhspringbootjpa.utils.ConstantMessage;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

/*
Created by IntelliJ IDEA 2022.2.3 (Community Edition)
Build #IC-222.4345.14, built on October 5, 2022
@Author Kyoto a.k.a Riky Hidayat
Java Developer
Created on 07/12/2022
@Last Modified 07/12/2022 11:11
Version 1.0
*/
@RestController
@RequestMapping("api/")
public class RekeningController {

    @Getter
    public RekeningService rekeningService;

    @Autowired
    public RekeningController(RekeningService rekeningService) {
        this.rekeningService = rekeningService;
    }

    @PostMapping("/rekening/post")
    public ResponseEntity<Object>
    saveRekening(@RequestBody Rekening rekening) throws Exception {
        if (rekening == null) throw new ResourceNotFoundException(ConstantMessage.ERROR_NO_CONTENT);
        rekeningService.saveRekening(rekening);
        return new ResponseHandler().generateResponse(ConstantMessage.SUCCESS_SAVE, HttpStatus.CREATED, null, null, null);
    }

    @PostMapping("/rekening/insert/query")
    public ResponseEntity<Object>
    saveRekeningQuery(@RequestBody Rekening rekening) throws Exception {
        if (rekening == null) throw new ResourceNotFoundException(ConstantMessage.ERROR_NO_CONTENT);
        rekeningService.saveRekeningQuery(rekening);
        return new ResponseHandler().generateResponse(ConstantMessage.SUCCESS_SAVE, HttpStatus.CREATED, null, null, null);
    }

    @GetMapping("/rekening/ceksaldo")
    public ResponseEntity<Object> cekValidationRekAndBalance(@RequestParam String rekNum, @RequestParam Double balance)throws Exception {

        Optional<Rekening> rekening = rekeningService.validationRekNumAndBalance(rekNum, balance);
        return new ResponseHandler().
                generateResponse(ConstantMessage.SUCCESS_CEK_BY,HttpStatus.OK,"",null,null);
    }

    @PutMapping("/rekening/transfer")
    public ResponseEntity<Object> transferDana(@RequestBody RekeningDTO rekeningDTO )throws Exception{
       rekeningService.updateRekening(rekeningDTO);
        return new ResponseHandler().
                generateResponse(ConstantMessage.SUCCESS_TRANSFER,HttpStatus.OK,"",null,null);
    }

}
