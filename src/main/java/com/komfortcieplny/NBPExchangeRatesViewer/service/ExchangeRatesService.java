package com.komfortcieplny.NBPExchangeRatesViewer.service;

import com.komfortcieplny.NBPExchangeRatesViewer.model.ExchangeRates;
import com.komfortcieplny.NBPExchangeRatesViewer.webclient.NbpClient;
import org.springframework.stereotype.Service;

@Service
public class ExchangeRatesService {

    private final NbpClient nbpClient;

    public ExchangeRatesService(NbpClient nbpClient) {
        this.nbpClient = nbpClient;
    }

    public String getAverageExchangeRate(String code, String effectiveDate) {
        return getAverage(nbpClient.getAverageExchangeRate(code, effectiveDate));
    }

    private String getAverage(ExchangeRates exchangeRates) {
        return "code: " + exchangeRates.code() +
                "<br>effectiveDate: " + exchangeRates.rates().get(0).effectiveDate() +
                "<br>average: " + exchangeRates.rates().get(0).mid();
    }
}
