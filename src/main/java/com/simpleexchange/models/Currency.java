package com.simpleexchange.models;

import java.math.BigDecimal;

public class Currency {
    private String code;
    private String name;
    private String country;
    private BigDecimal rateToBase;

    public Currency() {}

    public Currency(String code, String name, String country) {
        this.code = code;
        this.name = name;
        this.country = country;
    }

    public Currency(String code, String name, String country, BigDecimal rateToBase) {
        this.code = code;
        this.name = name;
        this.country = country;
        this.rateToBase = rateToBase;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public BigDecimal getRateToBase() {
        return rateToBase;
    }

    public void setRateToBase(BigDecimal rateToBase) {
        this.rateToBase = rateToBase;
    }
}
