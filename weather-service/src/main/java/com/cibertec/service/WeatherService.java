package com.cibertec.service;

import com.cibertec.dto.internal.WeatherResponse;

public interface WeatherService {
    WeatherResponse getCurrentWeatherByCoordinates(Double lat, Double lon);
}
