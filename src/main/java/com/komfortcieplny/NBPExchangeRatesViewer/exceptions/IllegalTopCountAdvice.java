package com.komfortcieplny.NBPExchangeRatesViewer.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class IllegalTopCountAdvice {

    @ResponseBody
    @ExceptionHandler(IllegalTopCountException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String illegalTopCountHandler(IllegalTopCountException ex) {
        return ex.getMessage();
    }
}
