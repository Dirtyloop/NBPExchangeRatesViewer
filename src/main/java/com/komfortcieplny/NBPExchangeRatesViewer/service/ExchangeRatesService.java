package com.komfortcieplny.NBPExchangeRatesViewer.service;

import com.komfortcieplny.NBPExchangeRatesViewer.model.ExchangeRates;
import com.komfortcieplny.NBPExchangeRatesViewer.model.Rate;
import com.komfortcieplny.NBPExchangeRatesViewer.webclient.NbpClient;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ExchangeRatesService {

    private final NbpClient nbpClient;

    public ExchangeRatesService(NbpClient nbpClient) {
        this.nbpClient = nbpClient;
    }

    public String getAverageExchangeRate(String code, String effectiveDate) {
        return getAverage(nbpClient.getAverageExchangeRate(code, effectiveDate));
    }

    public String getMaxAndMinAverageExchangeRates(String code, Long topCount) {
        return getMinMax(nbpClient.getMaxAndMinAverageExchangeRate(code, topCount));
    }

    private String getAverage(ExchangeRates exchangeRates) {
        return "code: " + exchangeRates.code() +
                "<br>effectiveDate: " + exchangeRates.rates().get(0).effectiveDate() +
                "<br>average: " + exchangeRates.rates().get(0).mid();
    }

    private String getMinMax(ExchangeRates exchangeRates) {
        return "code: " + exchangeRates.code() +
                "<br>min: " + getMin(exchangeRates.rates()) +
                "<br>max: " + getMax(exchangeRates.rates());
    }

    private double getMin(List<Rate> rates) {
        return rates
                .stream()
                .mapToDouble(Rate::mid)
                .min().orElseThrow(NoSuchElementException::new);
    }

    private double getMax(List<Rate> rates) {
        return rates
                .stream()
                .mapToDouble(Rate::mid)
                .max().orElseThrow(NoSuchElementException::new);
    }
}
