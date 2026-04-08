package com.cibertec.dto.external;

import lombok.Builder;

@Builder
public record WeatherDTO(
        String description,
        String icon
) {
}
