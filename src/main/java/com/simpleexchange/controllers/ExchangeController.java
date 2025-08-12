package com.simpleexchange.controllers;

import com.simpleexchange.models.Transaction;
import com.simpleexchange.services.ExchangeService;
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

    public ExchangeController(ExchangeService exchangeService) {
        this.exchangeService = exchangeService;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("baseCurrency", exchangeService.getBaseCurrency());
        model.addAttribute("exchangeRates", exchangeService.getAllExchangeRates());

        return "index";
    }

    @PostMapping("/convert")
    public String convert(@RequestParam String from, @RequestParam String to, @RequestParam BigDecimal amount, Model model) {
        Transaction tx = exchangeService.makeTransaction(from, to, amount);
        model.addAttribute("transaction", tx);
        return "result";
    }

    @GetMapping("/transactions")
    public String transactions(Model model) {
        model.addAttribute("transactions", exchangeService.getTransactions());
        return "transactions";
    }
}
