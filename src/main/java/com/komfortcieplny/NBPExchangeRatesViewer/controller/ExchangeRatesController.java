package com.komfortcieplny.NBPExchangeRatesViewer.controller;

import com.komfortcieplny.NBPExchangeRatesViewer.service.ExchangeRatesService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/exchangerates")
public class ExchangeRatesController {
    private final ExchangeRatesService exchangeRatesService;

    public ExchangeRatesController(ExchangeRatesService exchangeRatesService) {
        this.exchangeRatesService = exchangeRatesService;
    }

    @GetMapping("/average/{code}/{effectiveDate}")
    public String getAverageExchangeRete(@PathVariable String code, @PathVariable String effectiveDate) {
        return exchangeRatesService.getAverageExchangeRate(code, effectiveDate);
    }
}
