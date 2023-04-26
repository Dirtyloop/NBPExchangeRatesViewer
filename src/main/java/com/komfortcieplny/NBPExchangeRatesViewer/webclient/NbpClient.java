package com.komfortcieplny.NBPExchangeRatesViewer.webclient;

import com.komfortcieplny.NBPExchangeRatesViewer.exceptions.IllegalCurrencyCodeException;
import com.komfortcieplny.NBPExchangeRatesViewer.model.CurrencyCodeTableA;
import com.komfortcieplny.NBPExchangeRatesViewer.model.ExchangeRates;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class NbpClient {

    private static final String NBP_URL = "https://api.nbp.pl/api/exchangerates/rates/";
    private final RestTemplate restTemplate = new RestTemplate();

    public ExchangeRates getAverageExchangeRate(String code, String effectiveDate) throws IllegalCurrencyCodeException {
        if(!CurrencyCodeTableA.findByName(code)) {
            throw new IllegalCurrencyCodeException(code);
        }
        return getMethod("/a/{code}/{effectiveDate}/?format=json", ExchangeRates.class, code, effectiveDate);
    }

    public ExchangeRates getMaxAndMinAverageExchangeRate(String code, Long topCount) throws IllegalCurrencyCodeException {
        if(!CurrencyCodeTableA.findByName(code)) {
            throw new IllegalCurrencyCodeException(code);
        }
        return getMethod("/a/{code}/last/{topCount}/?format=json", ExchangeRates.class, code, topCount);
    }

    public ExchangeRates getMaxDiffExchangeRate(String code, Long topCount) throws IllegalCurrencyCodeException {
        if(!CurrencyCodeTableA.findByName(code)) {
            throw new IllegalCurrencyCodeException(code);
        }
        return getMethod("/c/{code}/last/{topCount}/?format=json", ExchangeRates.class, code, topCount);
    }

    private <T> T getMethod(String url, Class<T> responseType, Object... objects) {
        return restTemplate.getForObject(NBP_URL + url, responseType, objects);
    }
}
