package com.simpleexchange.models;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class DailyReport {
    private LocalDate date;
    private int totalTransactions;
    private BigDecimal totalAmountInBase;
    private List<Transaction> transactions;

    public DailyReport() {}

    public DailyReport(LocalDate date, List<Transaction> transactions) {
        this.date = date;
        this.transactions = transactions;
        this.totalTransactions = transactions.size();
        this.totalAmountInBase = transactions.stream()
                .map(Transaction::getAmountTo)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getTotalTransactions() {
        return totalTransactions;
    }

    public void setTotalTransactions(int totalTransactions) {
        this.totalTransactions = totalTransactions;
    }

    public BigDecimal getTotalAmountInBase() {
        return totalAmountInBase;
    }

    public void setTotalAmountInBase(BigDecimal totalAmountInBase) {
        this.totalAmountInBase = totalAmountInBase;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }
}
