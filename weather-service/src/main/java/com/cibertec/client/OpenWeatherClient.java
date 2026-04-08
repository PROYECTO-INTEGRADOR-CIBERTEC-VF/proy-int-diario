package com.cibertec.client;

import com.cibertec.dto.external.OpenWeatherExternalDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "OPClient", url = "https://api.openweathermap.org")
public interface OpenWeatherClient {

    @GetMapping(
            value = "/data/2.5/weather?units=metric",
            produces = { "application/json" }
    )
    ResponseEntity<OpenWeatherExternalDTO> getWeatherData(
            @RequestParam Double lat,
            @RequestParam Double lon,
            @RequestParam String appid,
            @RequestParam String lang
    );
}
