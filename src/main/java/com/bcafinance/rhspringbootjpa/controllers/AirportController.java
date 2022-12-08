package com.bcafinance.rhspringbootjpa.controllers;

import com.bcafinance.rhspringbootjpa.dto.AirportDTO;
import com.bcafinance.rhspringbootjpa.handler.ResourceNotFoundException;
import com.bcafinance.rhspringbootjpa.handler.ResponseHandler;
import com.bcafinance.rhspringbootjpa.models.Airport;
import com.bcafinance.rhspringbootjpa.services.AirportService;
import com.bcafinance.rhspringbootjpa.utils.ConstantMessage;
import com.bcafinance.rhspringbootjpa.utils.CsvReader;
import lombok.Getter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
Created on 05/12/2022
@Last Modified 05/12/2022 10:42
Version 1.0
*/
@RestController
@RequestMapping("api/")
public class AirportController {

    @Getter
    private AirportService airportService;

    @Autowired
    private ModelMapper modelMapper;

    private List<Airport> lsAirport = new ArrayList<Airport>();

    @Autowired
    public AirportController(AirportService airportService){this.airportService=airportService;}

    @PostMapping("/airport/insrt")
    public ResponseEntity<Object>
    uploadAirport(@Valid @RequestParam("demoFile") MultipartFile multipartFile) throws Exception {
        try{
            if(CsvReader.isCsv(multipartFile))
            {
                airportService.saveAirport(multipartFile);
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

    @GetMapping("/airport/dto/get/all")
    public ResponseEntity<Object> findAllAirportDTO()throws Exception {

        List<Airport> lsAirport = airportService.findAllAirport();

        if(lsAirport.size()!=0)
        {
            List<AirportDTO> lsAirportDTO = modelMapper.map(lsAirport, new TypeToken<List<AirportDTO>>() {}.getType());

            return new ResponseHandler().
                    generateResponse(ConstantMessage.SUCCESS_FIND_BY,HttpStatus.OK,lsAirportDTO,null,null);
        }
        throw new ResourceNotFoundException(ConstantMessage.WARNING_DATA_EMPTY);
    }

    @GetMapping("/airport/search/dto/{size}/{page}")
    public ResponseEntity<Object> pageFindAirportByNameDTO(@RequestParam String name,
                                                           @PathVariable("size") int size,
                                                           @PathVariable("page") int page )throws Exception {

        Pageable pageable = PageRequest.of(page,size);
        return new ResponseHandler().
                generateResponse(ConstantMessage.SUCCESS_FIND_BY,HttpStatus.OK,airportService.pagingFindAirportByName(name, pageable),null,null);
    }

    @GetMapping("/airport/view/dto/{size}/{page}")
    public ResponseEntity<Object> pageFindAirportByNameOrMuncipalityDTO(@RequestParam String name, @RequestParam String muncipality,
                                                           @PathVariable("size") int size,
                                                           @PathVariable("page") int page )throws Exception {

        Pageable pageable = PageRequest.of(page,size);
        return new ResponseHandler().
                generateResponse(ConstantMessage.SUCCESS_FIND_BY,HttpStatus.OK,airportService.pagingFindAirportByNameOrMuncipality(name,muncipality, pageable),null,null);
    }

    @GetMapping("/airport/paging/dto/{size}/{page}")
    public ResponseEntity<Object> pageFindAllDTO(         @PathVariable("size") int size,
                                                                        @PathVariable("page") int page )throws Exception {

        Pageable pageable = PageRequest.of(page,size);
        return new ResponseHandler().
                generateResponse(ConstantMessage.SUCCESS_FIND_BY,HttpStatus.OK,airportService.pagingFindAallAirport(pageable),null,null);
    }

    @GetMapping("/airport/sorting/dto/{size}/{page}/{sort}")
    public ResponseEntity<Object> pageSortByNameDTO(@RequestParam String name,
                                                    @PathVariable("size") int size,
                                                    @PathVariable("page") int page,
                                                    @PathVariable("sort") String sortz)throws Exception {

        Pageable pageable;
        if(sortz.equalsIgnoreCase("desc"))
        {
            pageable = PageRequest.of(page,size, Sort.by("id").descending());
        }
        else
        {
            pageable = PageRequest.of(page,size, Sort.by("id"));//default asc
        }

        return new ResponseHandler().
                generateResponse(ConstantMessage.SUCCESS_FIND_BY,HttpStatus.OK,airportService.pagingFindAirportByName(name, pageable),null,null);
    }
}
