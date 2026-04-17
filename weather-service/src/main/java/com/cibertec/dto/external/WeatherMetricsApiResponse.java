package com.cibertec.dto.external;

import lombok.Builder;

@Builder
public record WeatherMetricsApiResponse(
        Double temp,
        Double feels_like,
        Double humidity
) {
}
