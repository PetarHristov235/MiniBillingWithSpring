package com.example.demo;

import java.math.BigDecimal;

public record VatPercentages(BigDecimal taxedAmountPercentage, BigDecimal percentage) {}