package com.cibertec.mapper;

import com.cibertec.dto.external.OpenWeatherExternalResponse;
import com.cibertec.dto.internal.OpenWeatherInternalResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", uses = { WeatherDTOMapper.class })
public interface OpenWeatherDTOMapper {
    @Mappings({
            @Mapping(target = "ubicacion", source = "name"),
            @Mapping(target = "clima", source = "weather"),
            @Mapping(target = "temperatura", source = "main.temp"),
            @Mapping(target = "sensacion_termica", source = "main.feels_like"),
            @Mapping(target = "humedad", source = "main.humidity"),
            @Mapping(target = "velocidad_viento", source = "wind.speed")
    })
    OpenWeatherInternalResponse toInternalDTO(OpenWeatherExternalResponse externalDTO);
}
