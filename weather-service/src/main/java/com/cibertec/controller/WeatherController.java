package com.cibertec.controller;

import com.cibertec.service.OpenWeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/weather")
public class WeatherController {
    private final OpenWeatherService openWeatherService;

    @GetMapping
    public ResponseEntity<?> obtenerDatosDeClima(
            @RequestParam Double lat,
            @RequestParam Double lon,
            @RequestParam(defaultValue = "es") String lang
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(openWeatherService.getWeatherData(lat, lon, lang));
    }
}
