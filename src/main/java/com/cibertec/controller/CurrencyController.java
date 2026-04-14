package com.cibertec.controller;

import com.cibertec.service.CurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.util.Map;

@RestController
@RequestMapping("/currency")
@RequiredArgsConstructor
public class CurrencyController {

    private final CurrencyService currencyService;


    @PostMapping("/convert")
    public ResponseEntity<?> convertCurrency(@RequestBody Map<String, Object> request) {
        String from = (String) request.get("from");
        String to = (String) request.get("to");
        BigDecimal amount = new BigDecimal(request.get("amount").toString());

        return ResponseEntity.ok(currencyService.convert(from, to, amount));
    }


    @GetMapping("/exchange-rate/{name}")
    public ResponseEntity<?> getExchangeRate(@PathVariable String name) {
        return ResponseEntity.ok(currencyService.getCurrenciesByCountryName(name));
    }
}