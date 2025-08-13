package com.simpleexchange.services;

import com.simpleexchange.models.Transaction;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TransactionService {
    private final CurrencyService currencyService;
    private final List<Transaction> transactions = new ArrayList<>();

    public TransactionService(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    public Transaction makeTransaction(String fromCode, String toCode, BigDecimal amount, BigDecimal convertedAmount) {
        Transaction tx = new Transaction(currencyService.getCurrency(fromCode), currencyService.getCurrency(toCode), amount, convertedAmount);
        transactions.add(tx);

        return tx;
    }

    public List<Transaction> getDailyTransactions(LocalDate date) {
        return transactions.stream()
                .filter(tx -> tx.getDateTime().toLocalDate().equals(date))
                .collect(Collectors.toList());
    }

    public Map<String, BigDecimal> getDailySummary(LocalDate date) {
        List<Transaction> dailyTx = getDailyTransactions(date);

        Map<String, BigDecimal> totals = new HashMap<>();
        for (Transaction tx : dailyTx) {
            totals.merge(tx.getFromCurrency().getCode(), tx.getAmountFrom(), BigDecimal::add);
            totals.merge(tx.getToCurrency().getCode(), tx.getAmountTo(), BigDecimal::add);
        }
        return totals;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }
}
