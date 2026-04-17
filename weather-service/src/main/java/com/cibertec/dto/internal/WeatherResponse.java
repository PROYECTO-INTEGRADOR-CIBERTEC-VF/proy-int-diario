package com.cibertec.dto.internal;

import lombok.Builder;

@Builder
public record WeatherResponse(
        String ubicacion,
        WeatherConditionResponse[] clima,
        Double temperatura,
        Double sensacion_termica,
        Double humedad,
        Double velocidad_viento
) {
}
