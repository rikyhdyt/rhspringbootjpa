package com.bcafinance.rhspringbootjpa.controllers;/*
Created by IntelliJ IDEA 2022.2.3 (Community Edition)
Build #IC-222.4345.14, built on October 5, 2022
@Author Kyoto a.k.a Riky Hidayat
Java Developer
Created on 09/12/2022
@Last Modified 09/12/2022 13:45
Version 1.0
*/

import com.bcafinance.rhspringbootjpa.handler.ResourceNotFoundException;
import com.bcafinance.rhspringbootjpa.handler.ResponseHandler;
import com.bcafinance.rhspringbootjpa.models.Cars;
import com.bcafinance.rhspringbootjpa.models.Rekening;
import com.bcafinance.rhspringbootjpa.models.UjianAkhir;
import com.bcafinance.rhspringbootjpa.services.RekeningService;
import com.bcafinance.rhspringbootjpa.services.UjianAkhirService;
import com.bcafinance.rhspringbootjpa.utils.ConstantMessage;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api")
public class UjianAkhirController {

    @Getter
    public UjianAkhirService ujianAkhirService;

    @Autowired
    public UjianAkhirController(UjianAkhirService ujianAkhirService) {
        this.ujianAkhirService = ujianAkhirService;
    }

    @PostMapping("/ujianakhir/insert")
    public ResponseEntity<Object>
    saveUjianAKhir(@RequestBody UjianAkhir ujianAkhir) throws Exception {
//        if (ujianAkhir == null) throw new ResourceNotFoundException(ConstantMessage.ERROR_NO_CONTENT);
        ujianAkhirService.saveUjianAkhir(ujianAkhir);
        return new ResponseHandler().generateResponse(ConstantMessage.SUCCESS_SAVE, HttpStatus.CREATED, null, null, null);
    }

    @PostMapping("/ujianakhir/insert/batch")
    public ResponseEntity<Object>
    saveAll(@Valid @RequestBody List<UjianAkhir> listUjianAkhir
    ) throws Exception {
        ujianAkhirService.saveAllUjianAkhir(listUjianAkhir);
        return new ResponseHandler().generateResponse(ConstantMessage.SUCCESS_SAVE,HttpStatus.CREATED,null,null,null);
    }

    @GetMapping("/ujianakhir/search/{size}/{page}")
    public ResponseEntity<Object> pageFindAirport(@PathVariable("size") int size,
                                                  @PathVariable("page") int page )throws Exception {

        Pageable pageable = PageRequest.of(page,size);
        return new ResponseHandler().
                generateResponse(ConstantMessage.SUCCESS_FIND_BY,HttpStatus.OK,ujianAkhirService.pagingFindUjianAkhir(pageable),null,null);
    }

}
