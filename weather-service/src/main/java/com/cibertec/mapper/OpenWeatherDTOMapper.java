package com.cibertec.mapper;

import com.cibertec.dto.external.OpenWeatherExternalDTO;
import com.cibertec.dto.internal.OpenWeatherInternalDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface OpenWeatherDTOMapper {
    @Mappings({
            @Mapping(target = "ubicacion", source = "name"),
            @Mapping(target = "clima", source = "weather.description"),
            @Mapping(target = "icono_clima", source = "weather.icon"),
            @Mapping(target = "temperatura", source = "main.temp"),
            @Mapping(target = "sensacion_termica", source = "main.feels_like"),
            @Mapping(target = "humedad", source = "main.humidity"),
            @Mapping(target = "velocidad_viento", source = "wind.speed")
    })
    OpenWeatherInternalDTO toInternalDTO(OpenWeatherExternalDTO externalDTO);
}
