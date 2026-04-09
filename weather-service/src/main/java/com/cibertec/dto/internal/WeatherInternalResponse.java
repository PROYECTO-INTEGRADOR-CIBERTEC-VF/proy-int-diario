package com.cibertec.dto.internal;

import lombok.Builder;

@Builder
public record WeatherInternalResponse(
        String estado,
        String icono
) {
}
