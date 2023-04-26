package com.komfortcieplny.NBPExchangeRatesViewer.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CurrencyCodeTableCTest {

    @Test
    @DisplayName("Should Return True When Valid Code Is Provided")
    void shouldReturnTrueWhenValidCodeIsProvided() {
        boolean result = CurrencyCodeTableC.findByName("USD");
        assertTrue(result);
    }

    @Test
    @DisplayName("Should Return True When Valid LowerCase Code Is Provided")
    void shouldReturnTrueWhenValidLowerCaseCodeIsProvided() {
        boolean result = CurrencyCodeTableC.findByName("usd");
        assertTrue(result);
    }

    @Test
    @DisplayName("Should Return True When Valid MixedCase Code Is Provided")
    void shouldReturnTrueWhenValidMixedCaseCodeIsProvided() {
        boolean result = CurrencyCodeTableC.findByName("uSd");
        assertTrue(result);
    }

    @Test
    @DisplayName("Should Return False When Invalid Code Is Provided")
    void shouldReturnFalseWhenInvalidCodeIsProvided() {
        boolean result = CurrencyCodeTableC.findByName("us");
        assertFalse(result);
    }
}