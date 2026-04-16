package com.cibertec.service.impl;

import com.cibertec.client.WeatherClient;
import com.cibertec.dto.external.WeatherApiResponse;
import com.cibertec.dto.internal.WeatherResponse;
import com.cibertec.exception.CityNotFoundException;
import com.cibertec.exception.LocationNotFoundException;
import com.cibertec.mapper.WeatherMapper;
import com.cibertec.model.City;
import com.cibertec.repository.CityRepository;
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
    private final CityRepository cityRepository;

    @Value("${weather.api.api-key}")
    private String apiKey;

    @Value("${weather.api.default-lang}")
    private String lang;

    @Value("${weather.api.default-units}")
    private String units;

    @Override
    public WeatherResponse getCurrentWeatherByCityId(Long cityId) {
        log.info("Searching city by Id: {}", cityId);

        City city = cityRepository.findById(cityId)
                .orElseThrow(() -> new CityNotFoundException("City not found with id: " + cityId));

        log.info("Sending coordinates to OpenWeather API: latitude={}, longitude={}", city.getLatitude(), city.getLongitude());
        log.info("Sending default parameters: language={}, units={}", lang, units);

        WeatherApiResponse externalDTO = weatherClient.getWeatherData(city.getLatitude(), city.getLongitude(), apiKey, lang, units).getBody();

        if (externalDTO != null && externalDTO.name().isBlank())
            throw new LocationNotFoundException("No location found for coordinates: lat=" + city.getLatitude() + ", lon=" + city.getLongitude());

        WeatherResponse internalDTO = weatherMapper.toInternalDTO(externalDTO);
        log.info("Mapping succeeded! Returning DTO: {}", internalDTO);

        return internalDTO;
    }
}
