package com.cibertec.dto.external;

import lombok.Builder;

@Builder
public record OpenWeatherExternalDTO(
        WeatherExternalDTO[] weather,
        MainDTO main,
        WindDTO wind,
        String name
) {
}
