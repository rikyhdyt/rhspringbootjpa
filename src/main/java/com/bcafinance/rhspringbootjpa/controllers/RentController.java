package com.bcafinance.rhspringbootjpa.controllers;

import com.bcafinance.rhspringbootjpa.handler.ResourceNotFoundException;
import com.bcafinance.rhspringbootjpa.handler.ResponseHandler;
import com.bcafinance.rhspringbootjpa.models.Airport;
import com.bcafinance.rhspringbootjpa.models.Rent;
import com.bcafinance.rhspringbootjpa.services.AirportService;
import com.bcafinance.rhspringbootjpa.services.RentServices;
import com.bcafinance.rhspringbootjpa.utils.ConstantMessage;
import com.bcafinance.rhspringbootjpa.utils.CsvReader;
import com.bcafinance.rhspringbootjpa.utils.CsvReaderRent;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/*
Created by IntelliJ IDEA 2022.2.3 (Community Edition)
Build #IC-222.4345.14, built on October 5, 2022
@Author Kyoto a.k.a Riky Hidayat
Java Developer
Created on 06/12/2022
@Last Modified 06/12/2022 11:13
Version 1.0
*/
@RestController
@RequestMapping("api/")
public class RentController {

    @Autowired
    private RentServices rentServices;

    @Autowired
    private ModelMapper modelMapper;

    private List<Rent> lsRent = new ArrayList<Rent>();

    @Autowired
    public RentController(RentServices rentServices){this.rentServices=rentServices;}

    @PostMapping("/rent/post")
    public ResponseEntity<Object>
    uploadRent(@Valid @RequestParam("fileKey") MultipartFile multipartFile) throws Exception {
        try{
            if(CsvReaderRent.isCsv(multipartFile))
            {
                rentServices.saveRent(multipartFile);
            }
            else
            {
                throw new ResourceNotFoundException(ConstantMessage.ERROR_NOT_CSV_FILE+" -- "+multipartFile.getOriginalFilename());
            }
        }catch (Exception e)
        {
            throw new Exception(ConstantMessage.ERROR_UPLOAD_CSV+multipartFile.getOriginalFilename());
        }
        return new ResponseHandler().generateResponse(ConstantMessage.SUCCESS_SAVE,
                HttpStatus.CREATED,null,null,null);
    }

    @GetMapping("/rent/find/{size}/{page}")
    public ResponseEntity<Object> pageFindByCarYearGreaterThanEqualOrCostLessThanEqual(@RequestParam Integer carYear, @RequestParam Double cost,
                                                                        @PathVariable("size") int size,
                                                                        @PathVariable("page") int page )throws Exception {

        Pageable pageable = PageRequest.of(page,size);
        return new ResponseHandler().
                generateResponse(ConstantMessage.SUCCESS_FIND_BY,HttpStatus.OK,rentServices.pagingFindByCarYearGreaterThanEqualOrCostLessThanEqual(carYear, cost, pageable),null,null);
    }

    @GetMapping("/rent/search/{columnName}/{size}/{page}")
    public ResponseEntity<Object> findByRent(@PathVariable ("columnName") String columnName,
                                             @PathVariable("size") int size,
                                             @PathVariable("page") int page,
                                             @RequestParam String value)throws Exception {

        Pageable pageable = PageRequest.of(page,size);
        Iterable<Rent> lsRent = rentServices.findByRent(columnName, value, pageable);
        return new ResponseHandler().
                generateResponse(ConstantMessage.SUCCESS_FIND_BY,HttpStatus.OK,lsRent,null,null);
    }

}
