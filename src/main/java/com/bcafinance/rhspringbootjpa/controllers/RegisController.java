package com.bcafinance.rhspringbootjpa.controllers;
/*
Created by IntelliJ IDEA 2022.2.3 (Community Edition)
Build #IC-222.4345.14, built on October 5, 2022
@Author Kyoto a.k.a Riky Hidayat
Java Developer
Created on 04/12/2022
@Last Modified 04/12/2022 20:23
Version 1.0
*/
import com.bcafinance.rhspringbootjpa.configuration.ConfigProperties;
import com.bcafinance.rhspringbootjpa.core.SMTPCore;
import com.bcafinance.rhspringbootjpa.dto.CarsDTO;
import com.bcafinance.rhspringbootjpa.handler.ResourceNotFoundException;
import com.bcafinance.rhspringbootjpa.handler.ResponseHandler;
import com.bcafinance.rhspringbootjpa.models.Cars;
import com.bcafinance.rhspringbootjpa.models.Registers;
import com.bcafinance.rhspringbootjpa.services.RegisService;
import com.bcafinance.rhspringbootjpa.utils.ConstantMessage;
import lombok.Getter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("api")

public class RegisController {

    @Getter
    private RegisService regisService;

    public RegisController(){
    }

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    public RegisController(RegisService regisService){
        this.regisService = regisService;
    }

    @PostMapping("/registrasi")
    public ResponseEntity<Object>
    saveRegisters(@RequestBody Registers registers) throws Exception {
        if(registers==null)throw new ResourceNotFoundException(ConstantMessage.ERROR_NO_CONTENT);
        String[] strArr = new String[registers.size()];
        strArr[0] = registers.getEmail();
        String token =
                registers.getCreatedDate().toString().substring(17,19)+registers.getPostCode().toString().substring(1,3);

        registers.setToken(token);
        regisService.saveRegis(registers);
//        Date tanggal = new Date();

        System.out.println(System.getProperty("user.dir"));
        SMTPCore sc = new SMTPCore();
        ConfigProperties.getEmailPassword();
        String s = "coba";
        System.out.println(sc.sendMailWithAttachment(strArr,
                "EMAIL AUTHENTICATION","TOKEN REGISTRATION : "+"http://localhost:8080/api/registrasi/"+token,
                "SSL",
                new String[] {ResourceUtils.getFile("classpath:\\data\\sample.docx").getAbsolutePath()}));


        return new ResponseHandler().generateResponse(ConstantMessage.SUCCESS_SEND_EMAIL, HttpStatus.CREATED,null,null,null);
    }

    @GetMapping("/registrasi/{token}")
    public ResponseEntity<Object> getToken(@PathVariable("token") String token) throws Exception {
        Registers registers = regisService.findByToken(token);

        if (registers != null) {
            return new ResponseHandler().
                    generateResponse(ConstantMessage.SUCCESS_FIND_TOKEN, HttpStatus.OK,"",null,null);

        } else {
            throw new ResourceNotFoundException(ConstantMessage.WARNING_EMAIL_NOT_FOUND);
        }}}

