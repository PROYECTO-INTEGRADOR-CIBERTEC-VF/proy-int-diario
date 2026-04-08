package com.cibertec.mapper;

import com.cibertec.dto.external.WeatherExternalDTO;
import com.cibertec.dto.internal.WeatherInternalDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface WeatherDTOMapper {
    @Mappings({
            @Mapping(target = "estado", source = "description"),
            @Mapping(target = "icono", source = "icon")
    })
    WeatherInternalDTO toInternalDTO(WeatherExternalDTO externalDTO);
}
