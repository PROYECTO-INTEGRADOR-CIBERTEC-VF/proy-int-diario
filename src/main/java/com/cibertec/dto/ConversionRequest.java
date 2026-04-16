package com.cibertec.dto;

import java.math.BigDecimal;

public record ConversionRequest(String from, String to, BigDecimal amount) {}