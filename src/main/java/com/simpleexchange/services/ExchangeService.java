package com.simpleexchange.services;

import com.simpleexchange.models.Currency;
import com.simpleexchange.models.ExchangeRate;
import com.simpleexchange.models.Transaction;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

@Service
public class ExchangeService {
    private final Currency baseCurrency;
    private final Map<String, ExchangeRate> exchangeRates;
    private final List<Transaction> transactions;

    public ExchangeService() {
        baseCurrency = new Currency("RSD", "Dinar", "Srbija");
        exchangeRates = new HashMap<>();
        transactions = new ArrayList<>();

        addExchangeRate(new Currency("EUR", "Evro", "Eurozona"), new BigDecimal("117.50"));
        addExchangeRate(new Currency("USD", "Američki dolar", "SAD"), new BigDecimal("107.20"));
        addExchangeRate(new Currency("CHF", "Švajcarski franak", "Švajcarska"), new BigDecimal("119.90"));
    }

    public Currency getBaseCurrency() {
        return baseCurrency;
    }

    public void addExchangeRate(Currency currency, BigDecimal rateToBase) {
        exchangeRates.put(currency.getCode(), new ExchangeRate(currency, rateToBase));
    }

    public ExchangeRate getExchangeRate(String currencyCode) {
        return exchangeRates.get(currencyCode);
    }

    public Collection<ExchangeRate> getAllExchangeRates() {
        return exchangeRates.values();
    }

    public BigDecimal convert(String fromCode, String toCode, BigDecimal amount) {
        if (fromCode.equals(toCode)) {
            return amount;
        }

        BigDecimal amountInBase;

        if (fromCode.equals(baseCurrency.getCode())) {
            BigDecimal rateToBase = exchangeRates.get(toCode).getRateToBase();
            amountInBase = amount.divide(rateToBase, 4, RoundingMode.HALF_UP);
        } else if (toCode.equals(baseCurrency.getCode())) {
            BigDecimal rateToBase = exchangeRates.get(fromCode).getRateToBase();
            amountInBase = amount.multiply(rateToBase);
        } else {
            BigDecimal rateFrom = exchangeRates.get(fromCode).getRateToBase();
            BigDecimal rateTo = exchangeRates.get(toCode).getRateToBase();
            BigDecimal inBase = amount.multiply(rateFrom);
            amountInBase = inBase.divide(rateTo, 4, RoundingMode.HALF_UP);
        }

        return amountInBase;
    }

    public Transaction makeTransaction(String fromCode, String toCode, BigDecimal amount) {
        BigDecimal convertedAmount = convert(fromCode, toCode, amount);

        Transaction tx = new Transaction(getCurrency(fromCode), getCurrency(toCode), amount, convertedAmount);

        transactions.add(tx);
        return tx;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    private Currency getCurrency(String code) {
        if (code.equals(baseCurrency.getCode())) {
            return baseCurrency;
        }
        ExchangeRate rate = exchangeRates.get(code);
        return rate != null ? rate.getCurrency() : null;
    }
}
