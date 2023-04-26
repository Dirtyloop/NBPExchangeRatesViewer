package com.komfortcieplny.NBPExchangeRatesViewer.exceptions;

public class IllegalEffectiveDateException extends Throwable {
    public IllegalEffectiveDateException(String effectiveDate) {
        super(String.format("Illegal parameter: effectiveDate must be valid and you provide %s", effectiveDate));
    }
}
