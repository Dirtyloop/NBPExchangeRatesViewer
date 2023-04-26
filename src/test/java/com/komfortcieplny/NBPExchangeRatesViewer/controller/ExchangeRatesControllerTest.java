package com.komfortcieplny.NBPExchangeRatesViewer.controller;

import com.komfortcieplny.NBPExchangeRatesViewer.webclient.NbpClient;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class ExchangeRatesControllerTest {

    @Autowired
    private NbpClient nbpClient;

    @Autowired
    private MockMvc mockMvc;

    private static String newLine = System.getProperty("line.separator");

    @Test
    @DisplayName("Should Return Valid Average Exchange Rate")
    void shouldReturnValidAverageExchangeRate () throws Exception {
        this.mockMvc.perform(get("/api/v1/exchangerates/average/usd/2023-04-25"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.valueOf("text/plain;charset=UTF-8")))
                .andExpect(content().string("code: USD" + newLine +
                        "effectiveDate: 2023-04-25" + newLine +
                        "average: 4.1649"));
    }

    @Test
    @DisplayName("Should Return Valid Min And Max Exchange Rate")
    void shouldReturnValidMinAndMaxExchangeRate () throws Exception {
        this.mockMvc.perform(get("/api/v1/exchangerates/minmax/usd/100"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.valueOf("text/plain;charset=UTF-8")))
                .andExpect(content().string("code: USD" + newLine +
                        "min: 4.1557" + newLine +
                        "max: 4.4888"));
    }

    @Test
    @DisplayName("Should Return Valid MaxDiff Exchange Rate")
    void shouldReturnValidMaxDiffExchangeRate () throws Exception {
        this.mockMvc.perform(get("/api/v1/exchangerates/maxdiff/usd/100"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.valueOf("text/plain;charset=UTF-8")))
                .andExpect(content().string("code: USD" + newLine +
                        "maxdiff: 0.0896"));
    }

    @Test
    @DisplayName("Should Return Illegal ParameterCode Information")
    void shouldReturnIllegalCodeInformation () throws Exception {
        this.mockMvc.perform(get("/api/v1/exchangerates/maxdiff/us/100"))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.valueOf("text/plain;charset=UTF-8")))
                .andExpect(content().string("Illegal parameter: code must be valid and you provide us"));
    }

    @Test
    @DisplayName("Should Return Illegal Parameter topCount Information")
    void shouldReturnIllegalTopCountInformation () throws Exception {
        this.mockMvc.perform(get("/api/v1/exchangerates/maxdiff/usd/300"))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.valueOf("text/plain;charset=UTF-8")))
                .andExpect(content().string("Illegal parameter: topCount must be in the range 1-255 and you provide 300"));
    }

    @Test
    @DisplayName("Should Return Illegal Parameter efficientDate Information")
    void shouldReturnIllegalEfficientDateInformation () throws Exception {
        this.mockMvc.perform(get("/api/v1/exchangerates/average/usd/2023-02-30"))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.valueOf("text/plain;charset=UTF-8")))
                .andExpect(content().string("Illegal parameter: effectiveDate must be valid and you provide 2023-02-30"));
    }
}
