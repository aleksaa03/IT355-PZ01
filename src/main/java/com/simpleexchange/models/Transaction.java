package com.simpleexchange.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Transaction {
    private Currency fromCurrency;
    private Currency toCurrency;
    private BigDecimal amountFrom;
    private BigDecimal amountTo;
    private LocalDateTime dateTime;

    public Transaction() {}

    public Transaction(Currency fromCurrency, Currency toCurrency, BigDecimal amountFrom, BigDecimal amountTo) {
        this.fromCurrency = fromCurrency;
        this.toCurrency = toCurrency;
        this.amountFrom = amountFrom;
        this.amountTo = amountTo;
        this.dateTime = LocalDateTime.now();
    }

    public Currency getFromCurrency() {
        return fromCurrency;
    }

    public void setFromCurrency(Currency fromCurrency) {
        this.fromCurrency = fromCurrency;
    }

    public Currency getToCurrency() {
        return toCurrency;
    }

    public void setToCurrency(Currency toCurrency) {
        this.toCurrency = toCurrency;
    }

    public BigDecimal getAmountFrom() {
        return amountFrom;
    }

    public void setAmountFrom(BigDecimal amountFrom) {
        this.amountFrom = amountFrom;
    }

    public BigDecimal getAmountTo() {
        return amountTo;
    }

    public void setAmountTo(BigDecimal amountTo) {
        this.amountTo = amountTo;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}