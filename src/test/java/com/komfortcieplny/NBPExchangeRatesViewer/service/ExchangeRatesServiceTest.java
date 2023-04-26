package com.komfortcieplny.NBPExchangeRatesViewer.service;

import com.komfortcieplny.NBPExchangeRatesViewer.exceptions.IllegalCurrencyCodeException;
import com.komfortcieplny.NBPExchangeRatesViewer.exceptions.IllegalEffectiveDateException;
import com.komfortcieplny.NBPExchangeRatesViewer.model.ExchangeRates;
import com.komfortcieplny.NBPExchangeRatesViewer.model.Rate;
import com.komfortcieplny.NBPExchangeRatesViewer.webclient.NbpClient;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ExchangeRatesServiceTest {

    @InjectMocks
    private ExchangeRatesService exchangeRatesService;

    @Mock
    private NbpClient nbpClient;

    private static String newLine = System.getProperty("line.separator");

    @Test
    @DisplayName("Should Invoke nbpClient.getAverageExchangeRate")
    void nbpClientGetAverageExchangeRateSimpleTest() throws IllegalEffectiveDateException, IllegalCurrencyCodeException {

        Rate rate = new Rate("080/A/NBP/2023", LocalDate.of(2023, 04, 25), 4.1649, 0,0);
        List<Rate> rates = new ArrayList<>();
        rates.add(rate);
        ExchangeRates exchangeRates = new ExchangeRates("A", "dolar amerykański", "USD", rates);

        when(nbpClient.getAverageExchangeRate("usd", "2023-04-25")).thenReturn(exchangeRates);

        String result = exchangeRatesService.getAverageExchangeRate("usd", "2023-04-25");

        assertThat(result.equals(("code: USD" + newLine +
                "effectiveDate: 2023-04-25" + newLine +
                "average: 4.1649")));
        verify(nbpClient, times(1)).getAverageExchangeRate("usd", "2023-04-25");
    }
}
