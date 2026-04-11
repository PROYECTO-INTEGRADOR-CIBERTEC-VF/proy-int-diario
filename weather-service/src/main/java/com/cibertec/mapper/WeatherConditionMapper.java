package com.cibertec.mapper;

import com.cibertec.dto.external.WeatherConditionApiResponse;
import com.cibertec.dto.internal.WeatherConditionResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface WeatherConditionMapper {
    @Mappings({
            @Mapping(target = "estado", source = "description"),
            @Mapping(target = "icono", source = "icon")
    })
    WeatherConditionResponse toInternalDTO(WeatherConditionApiResponse externalDTO);
}
