package com.cibertec.dto.external;

import lombok.Builder;

@Builder
public record WeatherExternalResponse(
        String description,
        String icon
) {
}
