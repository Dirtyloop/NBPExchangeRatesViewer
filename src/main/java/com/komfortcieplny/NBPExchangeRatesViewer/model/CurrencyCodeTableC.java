package com.komfortcieplny.NBPExchangeRatesViewer.model;

public enum CurrencyCodeTableC {
    AUD,
    CAD,
    CZK,
    DKK,
    EUR,
    HUF,
    NOK,
    GBP,
    SEK,
    CHF,
    USD,
    JPY,
    XDR;

    public static boolean findByName(String name) {
        for (CurrencyCodeTableC currencyCode : values()) {
            if (currencyCode.name().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }
}
