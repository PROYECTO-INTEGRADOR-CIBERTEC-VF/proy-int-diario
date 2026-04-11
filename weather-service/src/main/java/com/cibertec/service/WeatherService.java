package com.cibertec.service;

import com.cibertec.dto.internal.WeatherResponse;

public interface WeatherService {
    WeatherResponse getWeatherData(Double lat, Double lon, String lang);
}
