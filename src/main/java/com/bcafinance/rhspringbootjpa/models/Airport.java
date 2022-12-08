package com.bcafinance.rhspringbootjpa.models;

import javax.persistence.*;
import java.util.Date;

/*
Created by IntelliJ IDEA 2022.2.3 (Community Edition)
Build #IC-222.4345.14, built on October 5, 2022
@Author Kyoto a.k.a Riky Hidayat
Java Developer
Created on 05/12/2022
@Last Modified 05/12/2022 10:43
Version 1.0
*/
@Entity
@Table(name = "MstAirport")
public class Airport {

    @Id
    @Column(name = "AirportId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @NotEmpty(message = ConstantMessage.ERROR_DATA_INVALID)
    @Column(name = "AirportName", length = 75, nullable = false)
    private String name;

//    @NotEmpty(message = ConstantMessage.ERROR_DATA_INVALID)
    @Column(name = "AirportCode", length = 5, nullable = false)
    private String airportCode;

//    @NotEmpty(message = ConstantMessage.ERROR_DATA_INVALID)
    @Column(name = "CountryCode", length = 8, nullable = false)
    private String countryCode;

//    @NotEmpty(message = ConstantMessage.ERROR_DATA_INVALID)
    @Column(name = "Muncipality", length = 50, nullable = false)
    private String muncipality;

    @Column(name = "CreatedBy",nullable = false)
    private String createdBy = "1";

    @Column(name = "CreatedDate",nullable = false)
    private Date createdDate = new Date();

    @Column(name = "ModifiedBy",nullable = true)
    private String modifiedBy ;

    @Column(name = "ModifiedDate",nullable = true)
    private Date modifiedDate;

    @Column(name = "IsActive",nullable = false)
    private boolean isActive = true;

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

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
