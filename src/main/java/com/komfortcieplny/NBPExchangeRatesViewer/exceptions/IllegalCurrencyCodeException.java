package com.komfortcieplny.NBPExchangeRatesViewer.exceptions;

public class IllegalCurrencyCodeException extends Throwable {
    public IllegalCurrencyCodeException(String code) {
        super(String.format("Illegal parameter: code must be valid and you provide %s", code));
    }
}
