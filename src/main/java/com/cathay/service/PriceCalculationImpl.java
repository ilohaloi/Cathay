package com.cathay.service;

import com.cathay.service.PriceCalculation;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class PriceCalculationImpl implements PriceCalculation {

    @Override
    public BigDecimal calculatePriceChange(BigDecimal previousClose, BigDecimal currentClose) {
        return currentClose.subtract(previousClose);
    }

    @Override
    public BigDecimal calculatePriceChangePercentage(BigDecimal previousClose, BigDecimal currentClose) {
        if (previousClose.compareTo(BigDecimal.ZERO) == 0) {
            throw new ArithmeticException("前一價格不得為零");
        }
        BigDecimal change = currentClose.subtract(previousClose);
        return change.divide(previousClose, 4, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(100));
    }
}