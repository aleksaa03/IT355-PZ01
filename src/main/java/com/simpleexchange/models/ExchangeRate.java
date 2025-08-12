package com.simpleexchange.models;

import java.math.BigDecimal;

public class ExchangeRate {
    private Currency currency;
    private BigDecimal rateToBase;

    public ExchangeRate() {}

    public ExchangeRate(Currency currency, BigDecimal rateToBase) {
        this.currency = currency;
        this.rateToBase = rateToBase;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public BigDecimal getRateToBase() {
        return rateToBase;
    }

    public void setRateToBase(BigDecimal rateToBase) {
        this.rateToBase = rateToBase;
    }
}