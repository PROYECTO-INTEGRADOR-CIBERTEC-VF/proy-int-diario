package com.cibertec.dto.external;

import lombok.Builder;

@Builder
public record WeatherApiResponse(
        WeatherConditionApiResponse[] weather,
        WeatherMetricsApiResponse main,
        WindMetricsApiResponse wind,
        String name
) {
}
