package com.simpleexchange.services;

import com.simpleexchange.models.Currency;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

@Service
public class ExchangeService {
    private final CurrencyService currencyService;

    public ExchangeService(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    public BigDecimal convert(String fromCode, String toCode, BigDecimal amount) {
        if (fromCode.equals(toCode)) {
            return amount;
        }

        Currency base = currencyService.getBaseCurrency();
        BigDecimal amountInBase;

        if (fromCode.equals(base.getCode())) {
            BigDecimal rateTo = currencyService.getExchangeRate(toCode);
            amountInBase = amount.divide(rateTo, 4, RoundingMode.HALF_UP);
        } else if (toCode.equals(base.getCode())) {
            BigDecimal rateFrom = currencyService.getExchangeRate(fromCode);
            amountInBase = amount.multiply(rateFrom);
        } else {
            BigDecimal rateFrom = currencyService.getExchangeRate(fromCode);
            BigDecimal rateTo = currencyService.getExchangeRate(toCode);
            BigDecimal inBase = amount.multiply(rateFrom);
            amountInBase = inBase.divide(rateTo, 4, RoundingMode.HALF_UP);
        }

        return amountInBase;
    }
}
