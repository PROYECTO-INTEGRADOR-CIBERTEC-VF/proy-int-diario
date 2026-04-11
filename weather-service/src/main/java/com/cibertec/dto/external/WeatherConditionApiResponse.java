package com.cibertec.dto.external;

import lombok.Builder;

@Builder
public record WeatherConditionApiResponse(
        String description,
        String icon
) {
}
