package com.simpleexchange.services;

import com.simpleexchange.models.Currency;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class CurrencyService {
    private final Map<String, Currency> currencies = new HashMap<>();
    private final Currency baseCurrency = new Currency("RSD", "Dinar", "Srbija");

    public CurrencyService() {
        addCurrency(new Currency("EUR", "Evro", "Eurozona", new BigDecimal("117.5")));
        addCurrency(new Currency("USD", "Američki dolar", "SAD", new BigDecimal("100.05")));
        addCurrency(new Currency("CHF", "Švajcarski franak", "Švajcarska", new BigDecimal("123.2")));
    }

    public Currency getBaseCurrency() {
        return baseCurrency;
    }

    public void addCurrency(Currency currency) {
        currencies.put(currency.getCode(), currency);
    }

    public Collection<Currency> getAllCurrencies() {
        return currencies.values();
    }

    public Currency getCurrency(String code) {
        if(code.equals(baseCurrency.getCode())) return baseCurrency;
        return currencies.get(code);
    }

    public BigDecimal getExchangeRate(String code) {
        return currencies.get(code).getRateToBase();
    }
}
