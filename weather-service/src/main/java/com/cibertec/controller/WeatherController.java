package com.cibertec.controller;

import com.cibertec.service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/weather")
public class WeatherController {
    private final WeatherService weatherService;

    @GetMapping("/{cityId}")
    public ResponseEntity<?> obtenerDatosDeClima(
            @PathVariable Long cityId
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(weatherService.getCurrentWeatherByCityId(cityId));
    }
}
