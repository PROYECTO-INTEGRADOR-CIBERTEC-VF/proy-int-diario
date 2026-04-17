package com.cibertec.dto.internal;

import lombok.Builder;

@Builder
public record WeatherConditionResponse(
        String estado,
        String icono
) {
}
