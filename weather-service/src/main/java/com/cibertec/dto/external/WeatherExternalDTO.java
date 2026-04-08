package com.cibertec.dto.external;

import lombok.Builder;

@Builder
public record WeatherExternalDTO(
        String description,
        String icon
) {
}
