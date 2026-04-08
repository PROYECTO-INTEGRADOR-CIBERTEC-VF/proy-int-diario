package com.cibertec.dto.external;

import lombok.Builder;

@Builder
public record OpenWeatherExternalDTO(
        WeatherDTO[] weather,
        MainDTO main,
        WindDTO wind,
        String name
) {
}
