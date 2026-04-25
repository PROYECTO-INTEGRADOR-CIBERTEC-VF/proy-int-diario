package com.cibertec.mapper;

import com.cibertec.dto.internal.CityResponse;
import com.cibertec.model.City;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface CityMapper {

    @Mappings({
            @Mapping(target = "country", source = "country.name"),
            @Mapping(target = "state", source = "state.name")
    })
    CityResponse toDTO(City city);
}
