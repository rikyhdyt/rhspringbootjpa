package com.bcafinance.rhspringbootjpa.models;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

/*
Created by IntelliJ IDEA 2022.2.3 (Community Edition)
Build #IC-222.4345.14, built on October 5, 2022
@Author Kyoto a.k.a Riky Hidayat
Java Developer
Created on 06/12/2022
@Last Modified 06/12/2022 10:21
Version 1.0
*/
@Entity
@Table(name = "MstRent")
public class Rent {

    @Id
    @Column(name = "CarId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "CarModel", length = 100, nullable = false)
    private String carModel;

    @Column(name = "CarUser", length = 100, nullable = false)
    private String carUser;

//    @Length(max = 4, message = ConstantMessage.WARNING_DATA_MANDATORY)
//    @NotEmpty(message = ConstantMessage.ERROR_DATA_INVALID)
    @Column(name = "CarNumber", length =4, nullable = false )
    private Integer carNumber;

//    @Length(max = 4, message = ConstantMessage.WARNING_DATA_MANDATORY)
//    @NotEmpty(message = ConstantMessage.ERROR_DATA_INVALID)
    @Column(name = "CarYear", length = 4, nullable = false)
    private Integer carYear;

//    @Length(max = 7, message = ConstantMessage.WARNING_DATA_MANDATORY)
//    @NotEmpty(message = ConstantMessage.ERROR_DATA_INVALID)
    @Column(name = "Cost", length = 7, nullable = false)
    private Double cost;

//    @Length(max = 4, message = ConstantMessage.WARNING_DATA_MANDATORY)
//    @NotEmpty(message = ConstantMessage.ERROR_DATA_INVALID)
    @Column(name = "Penalty", length = 4, nullable = false)
    private Double penalty;

//    @NotNull(message = ConstantMessage.ERROR_DATA_INVALID)
    @Column(name = "LoanDate", nullable = false)
    private LocalDate loanDate;

//    @NotNull(message = ConstantMessage.ERROR_DATA_INVALID)
    @Column(name = "ReturnDate", nullable = false)
    private LocalDate returnDate;

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

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public String getCarUser() {
        return carUser;
    }

    public void setCarUser(String carUser) {
        this.carUser = carUser;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(Integer carNumber) {
        this.carNumber = carNumber;
    }

    public Integer getCarYear() {
        return carYear;
    }

    public void setCarYear(Integer carYear) {
        this.carYear = carYear;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public Double getPenalty() {
        return penalty;
    }

    public void setPenalty(Double penalty) {
        this.penalty = penalty;
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

    public LocalDate getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(LocalDate loanDate) {
        this.loanDate = loanDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }
}
