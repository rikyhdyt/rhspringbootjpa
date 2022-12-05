package com.bcafinance.rhspringbootjpa.dto;/*
Created by IntelliJ IDEA 2022.2.3 (Community Edition)
Build #IC-222.4345.14, built on October 5, 2022
@Author Kyoto a.k.a Riky Hidayat
Java Developer
Created on 03/12/2022
@Last Modified 03/12/2022 18:53
Version 1.0
*/

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.security.PrivateKey;

@JsonIgnoreProperties(ignoreUnknown = false)
public class CategoryDTO {

    private Long id;

    private String CatName;

    private String CatDesc;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCatName() {
        return CatName;
    }

    public void setCatName(String catName) {
        CatName = catName;
    }

    public String getCatDesc() {
        return CatDesc;
    }

    public void setCatDesc(String catDesc) {
        CatDesc = catDesc;
    }
}
