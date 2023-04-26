package com.komfortcieplny.NBPExchangeRatesViewer.validators;

import org.apache.commons.validator.GenericValidator;
import org.springframework.stereotype.Component;

@Component
public class DateValidator {
    public boolean isValid(String dateStr) {
        return GenericValidator.isDate(dateStr, "yyyy-MM-dd", true);
    }
}
