package com.cibertec.service;

import com.cibertec.dto.internal.OpenWeatherInternalResponse;

public interface OpenWeatherService {
    OpenWeatherInternalResponse getWeatherData(Double lat, Double lon, String lang);
}
