package com.komfortcieplny.NBPExchangeRatesViewer.webclient;

import com.komfortcieplny.NBPExchangeRatesViewer.exceptions.IllegalCurrencyCodeException;
import com.komfortcieplny.NBPExchangeRatesViewer.exceptions.IllegalEffectiveDateException;
import com.komfortcieplny.NBPExchangeRatesViewer.exceptions.IllegalTopCountException;
import com.komfortcieplny.NBPExchangeRatesViewer.model.CurrencyCodeTableA;
import com.komfortcieplny.NBPExchangeRatesViewer.model.CurrencyCodeTableC;
import com.komfortcieplny.NBPExchangeRatesViewer.model.ExchangeRates;
import com.komfortcieplny.NBPExchangeRatesViewer.validators.DateValidator;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class NbpClient {

    private static final String NBP_URL = "https://api.nbp.pl/api/exchangerates/rates/";
    private final RestTemplate restTemplate = new RestTemplate();
    private final DateValidator dateValidator;

    public NbpClient(DateValidator dateValidator) {
        this.dateValidator = dateValidator;
    }

    public ExchangeRates getAverageExchangeRate(String code, String effectiveDate) throws IllegalCurrencyCodeException, IllegalEffectiveDateException {
        if(!CurrencyCodeTableA.findByName(code)) {
            throw new IllegalCurrencyCodeException(code);
        }
        if(!dateValidator.isValid(effectiveDate)) {
            throw new IllegalEffectiveDateException(effectiveDate);
        }
        return getMethod("/a/{code}/{effectiveDate}/?format=json", ExchangeRates.class, code, effectiveDate);
    }

    public ExchangeRates getMaxAndMinAverageExchangeRate(String code, Long topCount) throws IllegalCurrencyCodeException, IllegalTopCountException {
        if(!CurrencyCodeTableA.findByName(code)) {
            throw new IllegalCurrencyCodeException(code);
        }
        if(topCount<1 || topCount > 255) {
            throw new IllegalTopCountException(topCount);
        }
        return getMethod("/a/{code}/last/{topCount}/?format=json", ExchangeRates.class, code, topCount);
    }

    public ExchangeRates getMaxDiffExchangeRate(String code, Long topCount) throws IllegalCurrencyCodeException, IllegalTopCountException {
        if(!CurrencyCodeTableC.findByName(code)) {
            throw new IllegalCurrencyCodeException(code);
        }
        if(topCount<1 || topCount > 255) {
            throw new IllegalTopCountException(topCount);
        }
        return getMethod("/c/{code}/last/{topCount}/?format=json", ExchangeRates.class, code, topCount);
    }

    private <T> T getMethod(String url, Class<T> responseType, Object... objects) {
        return restTemplate.getForObject(NBP_URL + url, responseType, objects);
    }
}
