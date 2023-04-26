package com.komfortcieplny.NBPExchangeRatesViewer.service;

import com.komfortcieplny.NBPExchangeRatesViewer.exceptions.IllegalCurrencyCodeException;
import com.komfortcieplny.NBPExchangeRatesViewer.exceptions.IllegalEffectiveDateException;
import com.komfortcieplny.NBPExchangeRatesViewer.exceptions.IllegalTopCountException;
import com.komfortcieplny.NBPExchangeRatesViewer.model.ExchangeRates;
import com.komfortcieplny.NBPExchangeRatesViewer.model.Rate;
import com.komfortcieplny.NBPExchangeRatesViewer.webclient.NbpClient;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ExchangeRatesService {

    private static String newLine = System.getProperty("line.separator");
    private final NbpClient nbpClient;

    public ExchangeRatesService(NbpClient nbpClient) {
        this.nbpClient = nbpClient;
    }

    public String getAverageExchangeRate(String code, String effectiveDate) throws IllegalCurrencyCodeException, IllegalEffectiveDateException {
        return getAverageString(nbpClient.getAverageExchangeRate(code, effectiveDate));
    }

    public String getMaxAndMinAverageExchangeRates(String code, Long topCount) throws IllegalCurrencyCodeException, IllegalTopCountException {
        return getMinMaxString(nbpClient.getMaxAndMinAverageExchangeRate(code, topCount));
    }

    public String getMaxDiffExchangeRates(String code, Long topCount) throws IllegalCurrencyCodeException, IllegalTopCountException {
        return getMaxDiffString(nbpClient.getMaxDiffExchangeRate(code, topCount));
    }

    private String getAverageString(ExchangeRates exchangeRates) {
        return "code: " + exchangeRates.code() + newLine +
                "effectiveDate: " + exchangeRates.rates().get(0).effectiveDate() + newLine +
                "average: " + exchangeRates.rates().get(0).mid();
    }

    private String getMinMaxString(ExchangeRates exchangeRates) {
        return "code: " + exchangeRates.code() + newLine +
                "min: " + getMin(exchangeRates.rates()) + newLine +
                "max: " + getMax(exchangeRates.rates());
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

    private String getMaxDiffString(ExchangeRates exchangeRates) {
        return "code: " + exchangeRates.code() + newLine +
                "maxdiff: " + getMaxDiff(exchangeRates.rates());
    }

    private BigDecimal getMaxDiff(List<Rate> rates) {
        MathContext mc = new MathContext(3);
        return BigDecimal
                .valueOf(rates
                        .stream()
                        .mapToDouble(v -> v.ask() - v.bid())
                        .max().orElseThrow(NoSuchElementException::new))
                .round(mc);
    }
}
