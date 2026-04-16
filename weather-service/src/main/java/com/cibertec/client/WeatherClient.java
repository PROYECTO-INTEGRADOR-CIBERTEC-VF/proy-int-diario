package com.cibertec.client;

import com.cibertec.dto.external.WeatherApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

@FeignClient(name = "weatherApiClient", url = "${weather.api.base-url}")
public interface WeatherClient {

    @GetMapping(
            value = "/weather",
            produces = { "application/json" }
    )
    ResponseEntity<WeatherApiResponse> getWeatherData(
            @RequestParam BigDecimal lat,
            @RequestParam BigDecimal lon,
            @RequestParam String appid,
            @RequestParam String lang,
            @RequestParam String units
    );
}
