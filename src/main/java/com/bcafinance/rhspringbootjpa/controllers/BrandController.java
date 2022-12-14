package com.bcafinance.rhspringbootjpa.controllers;

import com.bcafinance.rhspringbootjpa.dto.BrandDTO;
import com.bcafinance.rhspringbootjpa.dto.CarsDTO;
import com.bcafinance.rhspringbootjpa.handler.ResourceNotFoundException;
import com.bcafinance.rhspringbootjpa.handler.ResponseHandler;
import com.bcafinance.rhspringbootjpa.models.Brands;
import com.bcafinance.rhspringbootjpa.models.Cars;
import com.bcafinance.rhspringbootjpa.services.BrandService;
import com.bcafinance.rhspringbootjpa.services.CarsService;
import com.bcafinance.rhspringbootjpa.utils.ConstantMessage;
import lombok.Getter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/*
Created by IntelliJ IDEA 2022.2.3 (Community Edition)
Build #IC-222.4345.14, built on October 5, 2022
@Author Kyoto a.k.a Riky Hidayat
Java Developer
Created on 01/12/2022
@Last Modified 01/12/2022 15:26
Version 1.0
*/
@RestController
@RequestMapping("api/")
public class BrandController {

    @Getter
    private BrandService brandService;

    public BrandController(){
    }

    @Autowired
    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/brands/insert")
    public ResponseEntity<Object>
    saveBrand(@RequestBody Brands brands) throws Exception {
        if (brands == null) throw new ResourceNotFoundException(ConstantMessage.ERROR_NO_CONTENT);
        brandService.saveBrand(brands);
        return new ResponseHandler().generateResponse(ConstantMessage.SUCCESS_SAVE, HttpStatus.CREATED, null, null, null);
    }

    @GetMapping("/brands/get/{id}")
    public ResponseEntity<Object> getBrandById(@PathVariable("id") long id) throws Exception {
        Brands brands = brandService.findByIdBrand(id);

        if (brands != null) {
            return new ResponseHandler().
                    generateResponse(ConstantMessage.SUCCESS_FIND_BY, HttpStatus.OK, brands, null, null);

        } else {
            throw new ResourceNotFoundException(ConstantMessage.WARNING_NOT_FOUND);
        }
    }

    @GetMapping("/brands/get/all")
    public ResponseEntity<Object> findAllbrands() throws Exception {

        int data = 0;
        List<Brands> lsBrands = brandService.findAllBrands();

        if (lsBrands.size() == 0) {
            throw new ResourceNotFoundException(ConstantMessage.WARNING_DATA_EMPTY);
        }

        return new ResponseHandler().
                generateResponse(ConstantMessage.SUCCESS_FIND_BY, HttpStatus.OK, lsBrands, null, null);
    }

    @GetMapping("/brands/dto/get/all")
    public ResponseEntity<Object> findAllbrandsDTO() throws Exception {

        int data = 0;
        List<Brands> lsBrands = brandService.findAllBrands();

        if (lsBrands.size() == 0) {
            throw new ResourceNotFoundException(ConstantMessage.WARNING_DATA_EMPTY);
        }
        List<BrandDTO> lsBrandsDTO = modelMapper.map(lsBrands, new TypeToken<List<BrandDTO>>(){}.getType());
        return new ResponseHandler().
                generateResponse(ConstantMessage.SUCCESS_FIND_BY, HttpStatus.OK, lsBrandsDTO, null, null);
    }

    @GetMapping("/brands/dto/get/{id}")
    public ResponseEntity<Object> getBrandByIdDTO(@PathVariable("id") long id) throws Exception {
        Brands brands = brandService.findByIdBrand(id);

        if (brands != null) {
            BrandDTO brandDTO = modelMapper.map(brands,BrandDTO.class);
            return new ResponseHandler().
                    generateResponse(ConstantMessage.SUCCESS_FIND_BY, HttpStatus.OK,brandDTO,null,null);

        } else {
            throw new ResourceNotFoundException(ConstantMessage.WARNING_NOT_FOUND);
        }
    }

    @GetMapping("/brands/dto/view/{brName}")
    public ResponseEntity<Object> getBrandContainingDTO(@PathVariable("brName") String brName) throws Exception {

        int data = 0;
        List<Brands> lsBrands = brandService.findByBrName(brName);

        if (lsBrands.size() == 0) {
            throw new ResourceNotFoundException(ConstantMessage.WARNING_DATA_EMPTY);
        }
        List<BrandDTO> lsBrandsDTO = modelMapper.map(lsBrands, new TypeToken<List<BrandDTO>>(){}.getType());
        return new ResponseHandler().
                generateResponse(ConstantMessage.SUCCESS_FIND_BY, HttpStatus.OK, lsBrands, null, null);
    }

    @PutMapping("/brands/updt")
    public ResponseEntity<Object> updateBrandsByID(@RequestBody Brands brands)throws Exception{
        brandService.updateBrandsById(brands);
        return new ResponseHandler().
                generateResponse(ConstantMessage.SUCCESS_FIND_BY,HttpStatus.OK,"",null,null);
    }

    @PostMapping("/brands/batch")
    public ResponseEntity<Object>
    saveAllBrands(@Valid @RequestBody List<Brands> listBrands
    ) throws Exception {
        brandService.saveAllBrands(listBrands);
        return new ResponseHandler().generateResponse(ConstantMessage.SUCCESS_SAVE,HttpStatus.CREATED,null,null,null);
    }

}
