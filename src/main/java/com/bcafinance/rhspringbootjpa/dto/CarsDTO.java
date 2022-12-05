package com.bcafinance.rhspringbootjpa.dto;

import com.bcafinance.rhspringbootjpa.models.Brands;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.ManyToOne;

/*
Created by IntelliJ IDEA 2022.2.3 (Community Edition)
Build #IC-222.4345.14, built on October 5, 2022
@Author Kyoto a.k.a Riky Hidayat
Java Developer
Created on 02/12/2022
@Last Modified 02/12/2022 09:48
Version 1.0
*/
@JsonIgnoreProperties(ignoreUnknown = false)
public class CarsDTO {

    private Long id;

    private String CarNum;

    private String CarType;

    @ManyToOne
    private Brands carBrand;

//    private Brands brands;

//    private String BrName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCarNum() {
        return CarNum;
    }

    public void setCarNum(String carNum) {
        CarNum = carNum;
    }

    public String getCarType() {
        return CarType;
    }

    public void setCarType(String carType) {
        CarType = carType;
    }

    public Brands getCarBrand() {
        return carBrand;
    }

    public void setCarBrand(Brands carBrand) {
        this.carBrand = carBrand;
    }

    //    public Brands getBrands() {
//        return brands;
//    }
//
//    public void setBrands(Brands brands) {
//        this.brands = brands;
//    }

//    public String getBrName() {
//        return BrName;
//    }
//
//    public void setBrName(String brName) {
//        BrName = brName;
//    }
}
