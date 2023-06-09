package com.komfortcieplny.NBPExchangeRatesViewer.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CurrencyCodeTableATest {

    @Test
    @DisplayName("Should Return True When Valid Code Is Provided")
    void shouldReturnTrueWhenValidCodeIsProvided() {
        boolean result = CurrencyCodeTableA.findByName("USD");
        assertTrue(result);
    }

    @Test
    @DisplayName("Should Return True When Valid LowerCase Code Is Provided")
    void shouldReturnTrueWhenValidLowerCaseCodeIsProvided() {
        boolean result = CurrencyCodeTableA.findByName("usd");
        assertTrue(result);
    }

    @Test
    @DisplayName("Should Return True When Valid MixedCase Code Is Provided")
    void shouldReturnTrueWhenValidMixedCaseCodeIsProvided() {
        boolean result = CurrencyCodeTableA.findByName("uSd");
        assertTrue(result);
    }

    @Test
    @DisplayName("Should Return False When Invalid Code Is Provided")
    void shouldReturnFalseWhenInvalidCodeIsProvided() {
        boolean result = CurrencyCodeTableA.findByName("us");
        assertFalse(result);
    }
}