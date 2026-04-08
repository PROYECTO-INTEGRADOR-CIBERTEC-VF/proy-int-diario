package com.cibertec.dto.internal;

import lombok.Builder;

@Builder
public record OpenWeatherInternalDTO(
        String ubicacion,
        WeatherInternalDTO[] clima,
        Double temperatura,
        Double sensacion_termica,
        Double humedad,
        Double velocidad_viento
) {
}
