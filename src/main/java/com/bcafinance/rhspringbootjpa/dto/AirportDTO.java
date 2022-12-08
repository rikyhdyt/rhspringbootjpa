package com.bcafinance.rhspringbootjpa.dto;

/*
Created by IntelliJ IDEA 2022.2.3 (Community Edition)
Build #IC-222.4345.14, built on October 5, 2022
@Author Kyoto a.k.a Riky Hidayat
Java Developer
Created on 05/12/2022
@Last Modified 05/12/2022 13:27
Version 1.0
*/

public class AirportDTO {

    private Long id;

    private String name;

    private String airportCode;

    private String countryCode;

    private String muncipality;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAirportCode() {
        return airportCode;
    }

    public void setAirportCode(String airportCode) {
        this.airportCode = airportCode;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getMuncipality() {
        return muncipality;
    }

    public void setMuncipality(String muncipality) {
        this.muncipality = muncipality;
    }
}
