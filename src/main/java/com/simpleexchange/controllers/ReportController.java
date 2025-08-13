package com.simpleexchange.controllers;

import com.simpleexchange.models.Transaction;
import com.simpleexchange.services.TransactionService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/reports")
public class ReportController {
    private final TransactionService transactionService;

    public ReportController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/daily-report")
    public String dailyReport(@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date, Model model) {
        if(date == null) date = LocalDate.now();

        List<Transaction> transactions = transactionService.getDailyTransactions(date);
        Map<String, BigDecimal> summary = transactionService.getDailySummary(date);

        model.addAttribute("date", date);
        model.addAttribute("transactions", transactions);
        model.addAttribute("summary", summary);

        return "daily-report";
    }
}
