package com.cibertec.dto.external;

import lombok.Builder;

@Builder
public record OpenWeatherExternalResponse(
        WeatherExternalResponse[] weather,
        MainExternalResponse main,
        WindExternalResponse wind,
        String name
) {
}
