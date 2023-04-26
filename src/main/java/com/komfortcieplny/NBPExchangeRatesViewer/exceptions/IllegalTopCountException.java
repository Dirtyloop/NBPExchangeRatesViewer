package com.komfortcieplny.NBPExchangeRatesViewer.exceptions;

public class IllegalTopCountException extends Throwable {
    public IllegalTopCountException(Long topCount) {
        super(String.format("Illegal parameter: topCount must be in the range 1-255 and you provide %d", topCount));
    }
}
