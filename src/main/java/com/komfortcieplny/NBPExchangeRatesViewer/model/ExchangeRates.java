package com.komfortcieplny.NBPExchangeRatesViewer.model;

import java.util.List;

public record ExchangeRates(String table, String currency, String code, List<Rate> rates) {
}
