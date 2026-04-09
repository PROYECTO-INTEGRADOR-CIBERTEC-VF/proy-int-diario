package com.cibertec.mapper;

import com.cibertec.dto.external.WeatherExternalResponse;
import com.cibertec.dto.internal.WeatherInternalResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface WeatherDTOMapper {
    @Mappings({
            @Mapping(target = "estado", source = "description"),
            @Mapping(target = "icono", source = "icon")
    })
    WeatherInternalResponse toInternalDTO(WeatherExternalResponse externalDTO);
}
