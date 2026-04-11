package com.cibertec.service.impl;

import com.cibertec.client.WeatherClient;
import com.cibertec.dto.external.WeatherApiResponse;
import com.cibertec.dto.internal.WeatherResponse;
import com.cibertec.exception.LocationNotFoundException;
import com.cibertec.mapper.WeatherMapper;
import com.cibertec.service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WeatherServiceImpl implements WeatherService {
    private final WeatherClient weatherClient;
    private final WeatherMapper weatherMapper;

    @Value("${openweather.api.key}")
    private String apiKey;

    @Override
    public WeatherResponse getWeatherData(Double lat, Double lon, String lang) {
        WeatherApiResponse externalDTO = weatherClient.getWeatherData(lat, lon, apiKey, lang).getBody();

        if (externalDTO != null && externalDTO.name().isBlank())
            throw new LocationNotFoundException("No location found for coordinates: lat=" + lat + ", lon=" + lon);

        return weatherMapper.toInternalDTO(externalDTO);
    }
}
