package com.cibertec.dto.internal;

import lombok.Builder;

@Builder
public record OpenWeatherInternalResponse(
        String ubicacion,
        WeatherInternalResponse[] clima,
        Double temperatura,
        Double sensacion_termica,
        Double humedad,
        Double velocidad_viento
) {
}
