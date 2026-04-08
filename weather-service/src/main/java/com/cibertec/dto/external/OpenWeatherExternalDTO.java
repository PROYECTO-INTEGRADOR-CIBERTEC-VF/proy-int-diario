package com.cibertec.dto.external;

import lombok.Builder;

@Builder
public record OpenWeatherExternalDTO(
        WeatherExternalDTO[] weather,
        MainExternalDTO main,
        WindExternalDTO wind,
        String name
) {
}
