package com.richard.reactive.bank.controllers;

import com.richard.reactive.bank.models.BankReport;
import com.richard.reactive.bank.services.BankReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BankReportController {
    @Autowired
    private BankReportService bankReportService;

    @GetMapping("/sync/bank-report/{accountId}")
    public ResponseEntity<BankReport> syncReportByAccountId(@PathVariable("accountId") Integer accountId) {
        BankReport report = bankReportService.findByAccountId(accountId);
        return new ResponseEntity<>(report, HttpStatus.OK);
    }
}
