package com.komfortcieplny.NBPExchangeRatesViewer.model;

public enum CurrencyCodeTableA {
    AUD,
    THB,
    BRL,
    BGN,
    CAD,
    CLP,
    CZK,
    DKK,
    EUR,
    HUF,
    HKD,
    UAH,
    ISK,
    INR,
    MYR,
    MXN,
    ILS,
    NZD,
    NOK,
    PHP,
    GBP,
    ZAR,
    RON,
    IDR,
    SGD,
    SEK,
    CHF,
    TRY,
    USD,
    KRW,
    JPY,
    CNY,
    XDR;

    public static boolean findByName(String name) {
        for (CurrencyCodeTableA currencyCode : values()) {
            if (currencyCode.name().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }
}
