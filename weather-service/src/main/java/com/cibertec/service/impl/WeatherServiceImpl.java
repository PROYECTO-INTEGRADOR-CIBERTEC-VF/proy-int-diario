package com.cibertec.service.impl;

import com.cibertec.client.WeatherClient;
import com.cibertec.dto.external.WeatherApiResponse;
import com.cibertec.dto.internal.WeatherResponse;
import com.cibertec.exception.LocationNotFoundException;
import com.cibertec.mapper.WeatherMapper;
import com.cibertec.service.WeatherService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class WeatherServiceImpl implements WeatherService {
    private final WeatherClient weatherClient;
    private final WeatherMapper weatherMapper;

    @Value("${openweather.api.key}")
    private String apiKey;

    @Value("${openweather.api.lang}")
    private String lang;

    @Value("${openweather.api.units}")
    private String units;

    @Override
    public WeatherResponse getCurrentWeatherByCoordinates(Double lat, Double lon) {
        log.info("Sending coordinates to OpenWeather API: latitude={}, longitude={}", lat, lon);
        log.info("Sending default parameters: language={}, units={}", lang, units);

        WeatherApiResponse externalDTO = weatherClient.getWeatherData(lat, lon, apiKey, lang, units).getBody();

        if (externalDTO != null && externalDTO.name().isBlank())
            throw new LocationNotFoundException("No location found for coordinates: lat=" + lat + ", lon=" + lon);

        WeatherResponse internalDTO = weatherMapper.toInternalDTO(externalDTO);
        log.info("Mapping succeeded! Returning DTO: {}", internalDTO);

        return internalDTO;
    }
}
