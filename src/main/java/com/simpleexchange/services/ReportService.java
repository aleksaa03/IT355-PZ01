package com.simpleexchange.services;

import com.simpleexchange.models.DailyReport;
import com.simpleexchange.models.Transaction;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReportService {
    private final TransactionService transactionService;

    public ReportService(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    public DailyReport generateDailyReport(LocalDate date) {
        List<Transaction> dailyTx = transactionService.getTransactions().stream()
                .filter(t -> t.getDateTime().toLocalDate().equals(date))
                .collect(Collectors.toList());
        return new DailyReport(date, dailyTx);
    }
}
