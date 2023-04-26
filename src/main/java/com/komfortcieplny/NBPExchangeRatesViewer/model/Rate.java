package com.komfortcieplny.NBPExchangeRatesViewer.model;

import java.time.LocalDate;

public record Rate(String no, LocalDate effectiveDate, double mid, double bid, double ask) {
}
