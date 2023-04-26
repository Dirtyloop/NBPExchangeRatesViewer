package com.komfortcieplny.NBPExchangeRatesViewer.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class IllegalCurrencyCodeAdvice {

    @ResponseBody
    @ExceptionHandler(IllegalCurrencyCodeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String innegalCurrencyCodeHandler(IllegalCurrencyCodeException ex) {
        return ex.getMessage();
    }
}
