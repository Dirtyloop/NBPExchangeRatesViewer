package com.komfortcieplny.NBPExchangeRatesViewer.service;

import org.springframework.stereotype.Service;

@Service
public class ExchangeRatesService {
    public String getAverageExchangeRate(String code, String effectiveDate) {
        return nbpClient.getAverageExchangeRate(code, effectiveDate);
}
