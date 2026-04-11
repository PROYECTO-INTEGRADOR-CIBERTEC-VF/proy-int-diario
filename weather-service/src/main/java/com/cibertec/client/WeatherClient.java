package com.cibertec.client;

import com.cibertec.dto.external.WeatherApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "weatherApiClient", url = "https://api.openweathermap.org")
public interface WeatherClient {

    @GetMapping(
            value = "/data/2.5/weather?units=metric",
            produces = { "application/json" }
    )
    ResponseEntity<WeatherApiResponse> getWeatherData(
            @RequestParam Double lat,
            @RequestParam Double lon,
            @RequestParam String appid,
            @RequestParam String lang
    );
}
