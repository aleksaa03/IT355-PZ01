package com.simpleexchange.controllers;

import com.simpleexchange.models.Currency;
import com.simpleexchange.services.CurrencyService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/currencies")
public class CurrencyController {
    private final CurrencyService currencyService;

    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @GetMapping
    public String showCurrencies(Model model) {
        model.addAttribute("currencies", currencyService.getAllCurrencies());
        model.addAttribute("currency", new Currency());
        return "currencies";
    }

    @PostMapping("/add")
    public String addCurrency(@ModelAttribute Currency currency) {
        currencyService.addCurrency(currency);
        return "redirect:/currencies";
    }
}
