package com.cibertec.service.impl;

import com.cibertec.dto.Currency;
import com.cibertec.service.CurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CurrencyServiceImpl implements CurrencyService {

    private final RestTemplate restTemplate;

    @Override
    public Map<String, Currency> getCurrenciesByCountryName(String name) {
        String url = "https://restcountries.com/v3.1/name/" + name;


        Currency[] response = restTemplate.getForObject(url, Currency[].class);

        if (response != null && response.length > 0) {

            return Map.of("currency", response[0]);
        }

        return null;

    }

    @Override
    public Map<String, Object> convert(String from, String to, BigDecimal amount) {

        BigDecimal rate = new BigDecimal("3.75");
        BigDecimal calculation = amount.multiply(rate);


        Map<String, Object> response = new HashMap<>();
        response.put("from", from);
        response.put("to", to);
        response.put("amount", amount);
        response.put("result", calculation);

        return response;

    }
}