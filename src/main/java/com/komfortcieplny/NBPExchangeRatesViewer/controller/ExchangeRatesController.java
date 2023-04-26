package com.komfortcieplny.NBPExchangeRatesViewer.controller;

import com.komfortcieplny.NBPExchangeRatesViewer.exceptions.IllegalCurrencyCodeException;
import com.komfortcieplny.NBPExchangeRatesViewer.exceptions.IllegalEffectiveDateException;
import com.komfortcieplny.NBPExchangeRatesViewer.exceptions.IllegalTopCountException;
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
    public String getAverageExchangeRete(@PathVariable String code, @PathVariable String effectiveDate) throws IllegalCurrencyCodeException, IllegalEffectiveDateException {
        return exchangeRatesService.getAverageExchangeRate(code, effectiveDate);
    }

    @GetMapping("/minmax/{code}/{topCount}")
    public String getMaxAndMinAverageExchangeRetes(@PathVariable String code, @PathVariable Long topCount) throws IllegalCurrencyCodeException, IllegalTopCountException {
        return exchangeRatesService.getMaxAndMinAverageExchangeRates(code, topCount);
    }

    @GetMapping("/maxdiff/{code}/{topCount}")
    public String getMaxDiffExchangeRetes(@PathVariable String code, @PathVariable Long topCount) throws IllegalCurrencyCodeException, IllegalTopCountException {
        return exchangeRatesService.getMaxDiffExchangeRates(code, topCount);
    }

}
