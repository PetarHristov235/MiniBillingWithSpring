package com.example.demo;


import java.math.BigDecimal;

public record VatPercentage(BigDecimal taxedAmountPercentage, BigDecimal percentage) {

}