package com.cibertec.service.impl;

import com.cibertec.client.OpenWeatherClient;
import com.cibertec.dto.external.OpenWeatherExternalDTO;
import com.cibertec.dto.internal.OpenWeatherInternalDTO;
import com.cibertec.service.OpenWeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OpenWeatherServiceImpl implements OpenWeatherService {
    private final OpenWeatherClient openWeatherClient;

    @Value("${openweather.api.key}")
    private String apiKey;

    @Override
    public OpenWeatherInternalDTO getWeatherData(Double lat, Double lon, String lang) {
        OpenWeatherExternalDTO externalDTO = openWeatherClient.getWeatherData(lat, lon, apiKey, lang).getBody();
        return null;
    }
}
