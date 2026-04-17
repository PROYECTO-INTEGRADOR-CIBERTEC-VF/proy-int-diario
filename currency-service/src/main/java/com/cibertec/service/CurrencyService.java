package com.cibertec.service;

import org.springframework.stereotype.Service;
import com.cibertec.dto.Currency;

import java.math.BigDecimal;
import java.util.Map;

@Service
public interface CurrencyService {
    Map<String, Currency> getCurrenciesByCountryName(String name) ;
    Object convert(String from, String to, BigDecimal amount);
}
