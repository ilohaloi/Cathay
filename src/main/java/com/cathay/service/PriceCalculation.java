package com.cathay.service;

import java.math.BigDecimal;

public interface PriceCalculation {
    BigDecimal calculatePriceChange(BigDecimal previousClose, BigDecimal currentClose);
    BigDecimal calculatePriceChangePercentage(BigDecimal previousClose, BigDecimal currentClose);
}