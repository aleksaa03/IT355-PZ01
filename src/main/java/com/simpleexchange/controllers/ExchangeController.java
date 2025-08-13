package com.simpleexchange.controllers;

import com.simpleexchange.models.Transaction;
import com.simpleexchange.services.CurrencyService;
import com.simpleexchange.services.ExchangeService;
import com.simpleexchange.services.TransactionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

@Controller
@RequestMapping("/")
public class ExchangeController {
    private final ExchangeService exchangeService;
    private final TransactionService transactionService;
    private final CurrencyService currencyService;

    public ExchangeController(ExchangeService exchangeService, TransactionService transactionService, CurrencyService currencyService) {
        this.exchangeService = exchangeService;
        this.transactionService = transactionService;
        this.currencyService = currencyService;
    }

    @GetMapping
    public String home(Model model) {
        model.addAttribute("baseCurrency", currencyService.getBaseCurrency());
        model.addAttribute("exchangeRates", currencyService.getAllCurrencies());

        return "index";
    }

    @PostMapping("/convert")
    public String convert(@RequestParam String from, @RequestParam String to,
                          @RequestParam BigDecimal amount, Model model) {

        BigDecimal converted = exchangeService.convert(from, to, amount);
        Transaction tx = transactionService.makeTransaction(from, to, amount, converted);

        model.addAttribute("transaction", tx);
        return "result";
    }
}
