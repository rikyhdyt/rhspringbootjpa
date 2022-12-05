package com.bcafinance.rhspringbootjpa.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.bcafinance.rhspringbootjpa.utils.ConstantMessage;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/*
Created by IntelliJ IDEA 2022.2.3 (Community Edition)
Build #IC-222.4345.14, built on October 5, 2022
@Author Kyoto a.k.a Riky Hidayat
Java Developer
Created on 02/12/2022
@Last Modified 02/12/2022 19:21
Version 1.0
*/
@Entity
@Table(name = "MstCategory")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CatId")
    private Long id;

    @Length(max = 25, message = ConstantMessage.WARNING_NAME_MAX_LENGTH)
    @NotEmpty(message = ConstantMessage.WARNING_CAT_NAME_MANDATORY)
    @Column(name = "CatName", length = 25, nullable = false)
    private String catName;

    @Length(max = 50, message = ConstantMessage.WARNING_DESC_MAX_LENGTH)
    @NotEmpty(message = ConstantMessage.WARNING_DESC_NAME_MANDATORY)
    @Column(name = "CatDesc", length = 50, nullable = false)
    private String catDesc;

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

    @ManyToMany(mappedBy = "category")
    @JsonBackReference
    private Set<Cars> carlist = new HashSet<Cars>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public String getCatDesc() {
        return catDesc;
    }

    public void setCatDesc(String catDesc) {
        this.catDesc = catDesc;
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

    public Set<Cars> getCarlist() {
        return carlist;
    }

    public void setCarlist(Set<Cars> carlist) {
        this.carlist = carlist;
    }
//    public Category(){}
}
