package com.cibertec.dto.internal;

import lombok.Builder;

@Builder
public record WeatherInternalDTO(
        String estado,
        String icono
) {
}
