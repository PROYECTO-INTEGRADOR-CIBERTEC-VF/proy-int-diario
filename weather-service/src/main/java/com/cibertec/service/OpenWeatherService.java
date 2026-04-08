package com.cibertec.service;

import com.cibertec.dto.internal.OpenWeatherInternalDTO;

public interface OpenWeatherService {
    OpenWeatherInternalDTO getWeatherData(Double lat, Double lon, String lang);
}
