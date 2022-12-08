package com.bcafinance.rhspringbootjpa.dto;
/*
Created by IntelliJ IDEA 2022.2.3 (Community Edition)
Build #IC-222.4345.14, built on October 5, 2022
@Author Kyoto a.k.a Riky Hidayat
Java Developer
Created on 07/12/2022
@Last Modified 07/12/2022 13:57
Version 1.0
*/

public class RekeningDTO {

    private Long id;

    private String rekNumSumber;

    private String rekNumPenerima;

    private Double amount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRekNumSumber() {
        return rekNumSumber;
    }

    public void setRekNumSumber(String rekNumSumber) {
        this.rekNumSumber = rekNumSumber;
    }

    public String getRekNumPenerima() {
        return rekNumPenerima;
    }

    public void setRekNumPenerima(String rekNumPenerima) {
        this.rekNumPenerima = rekNumPenerima;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
