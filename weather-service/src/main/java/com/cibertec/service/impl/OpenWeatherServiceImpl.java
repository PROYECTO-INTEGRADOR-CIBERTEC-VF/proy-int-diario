package com.cibertec.service.impl;

import com.cibertec.client.OpenWeatherClient;
import com.cibertec.dto.external.OpenWeatherExternalDTO;
import com.cibertec.dto.internal.OpenWeatherInternalDTO;
import com.cibertec.exception.LocationNotFoundException;
import com.cibertec.mapper.OpenWeatherDTOMapper;
import com.cibertec.service.OpenWeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OpenWeatherServiceImpl implements OpenWeatherService {
    private final OpenWeatherClient openWeatherClient;
    private final OpenWeatherDTOMapper openWeatherDTOMapper;

    @Value("${openweather.api.key}")
    private String apiKey;

    @Override
    public OpenWeatherInternalDTO getWeatherData(Double lat, Double lon, String lang) {
        OpenWeatherExternalDTO externalDTO = openWeatherClient.getWeatherData(lat, lon, apiKey, lang).getBody();

        if (externalDTO != null && externalDTO.name().isBlank())
            throw new LocationNotFoundException("No location found for coordinates: lat=" + lat + ", lon=" + lon);

        return openWeatherDTOMapper.toInternalDTO(externalDTO);
    }
}
