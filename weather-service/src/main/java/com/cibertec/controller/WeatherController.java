package com.cibertec.controller;

import com.cibertec.service.WeatherService;
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
    private final WeatherService weatherService;

    @GetMapping
    public ResponseEntity<?> obtenerDatosDeClima(
            @RequestParam Double lat,
            @RequestParam Double lon
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(weatherService.getCurrentWeatherByCoordinates(lat, lon));
    }
}
