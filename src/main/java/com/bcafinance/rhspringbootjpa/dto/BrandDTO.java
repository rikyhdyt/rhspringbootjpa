package com.bcafinance.rhspringbootjpa.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/*
Created by IntelliJ IDEA 2022.2.3 (Community Edition)
Build #IC-222.4345.14, built on October 5, 2022
@Author Kyoto a.k.a Riky Hidayat
Java Developer
Created on 04/12/2022
@Last Modified 04/12/2022 11:06
Version 1.0
*/
@JsonIgnoreProperties(ignoreUnknown = false)
public class BrandDTO {

    private Long id;

    private String BrName;

    private String BrFrom;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrName() {
        return BrName;
    }

    public void setBrName(String brName) {
        BrName = brName;
    }

    public String getBrFrom() {
        return BrFrom;
    }

    public void setBrFrom(String brFrom) {
        BrFrom = brFrom;
    }
}
