package com.cibertec.service;

import com.cibertec.dto.internal.WeatherResponse;

public interface WeatherService {
    WeatherResponse getCurrentWeatherByCityId(Long cityId);
}
