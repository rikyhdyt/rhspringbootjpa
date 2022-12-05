package com.bcafinance.rhspringbootjpa.models;
/*
Created by IntelliJ IDEA 2022.2.3 (Community Edition)
Build #IC-222.4345.14, built on October 5, 2022
@Author Kyoto a.k.a Riky Hidayat
Java Developer
Created on 30/11/2022
@Last Modified 30/11/2022 14:13
Version 1.0
*/

import com.bcafinance.rhspringbootjpa.utils.ConstantMessage;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name= "MstCars")

public class Cars {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CarsId")
    private Long id;

//    @NotEmpty(message = ConstantMessage.WARNING_CARNUM_NAME_MANDATORY)
    @Column(name = "CarNum",length = 15 ,nullable = false,unique = true)
    private String carNum;

//    @NotEmpty(message = ConstantMessage.WARNING_CARTYPE_NAME_MANDATORY)
    @Column(name = "CarType",length = 50 ,nullable = false)
    private String carType;

//    @NotEmpty(message = ConstantMessage.WARNING_CARBRAND_NAME_MANDATORY)
    @ManyToOne
//    @JoinColumn(name = "CarBrand")
    private Brands carBrand;

//    @NotEmpty(message = ConstantMessage.WARNING_CARCOLOR_NAME_MANDATORY)
    @Column(name = "CarColor",length = 50 ,nullable = false)
    private String carColor;

//    @NotNull(message = ConstantMessage.WARNING_CARYEAR_NAME_MANDATORY)
    @Column(name = "CarYear",length = 4 ,nullable = false)
    private Integer carYear;

    @ManyToMany
    @JoinTable(
            name ="CarsCategory",
            joinColumns = @JoinColumn(name = "CarsId", referencedColumnName = "CarsId"),
            inverseJoinColumns =@JoinColumn(name = "CatId", referencedColumnName = "CatId")
    )
    private Set<Category> category = new HashSet<Category>();

    @Column(name = "CreatedBy",nullable = true)
    private String createdBy = "1";

    //    @Column(name = "CreatedDate",nullable = true, columnDefinition = "datetime2(7) DEFAULT GETDATE() ")
    @Column(name = "CreatedDate",nullable = true)
    private Date createdDate = new Date();//JANGAN GUNAKAN columnDefinition untuk set default kolom, langsung set di variabel nya saja.

    @Column(name = "ModifiedBy",nullable = true)
    private String modifiedBy ;

    @Column(name = "ModifiedDate",nullable = true)
    private Date modifiedDate;

    @Column(name = "IsActive",nullable = true)
    private boolean isActive = true;

    public Cars(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCarNum() {
        return carNum;
    }

    public void setCarNum(String carNum) {
        this.carNum = carNum;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public Brands getCarBrand() {
        return carBrand;
    }

    public void setCarBrand(Brands carBrand) {
        this.carBrand = carBrand;
    }

    public String getCarColor() {
        return carColor;
    }

    public void setCarColor(String carColor) {
        this.carColor = carColor;
    }

    public Integer getCarYear() {
        return carYear;
    }

    public void setCarYear(Integer carYear) {
        this.carYear = carYear;
    }

    public Set<Category> getCategory() {
        return category;
    }

    public void setCategory(Set<Category> category) {
        this.category = category;
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
