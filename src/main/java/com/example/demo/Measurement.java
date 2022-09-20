package com.example.demo;


import com.example.demo.entity.User;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record Measurement(LocalDateTime start, LocalDateTime end, BigDecimal value, User user) {

}