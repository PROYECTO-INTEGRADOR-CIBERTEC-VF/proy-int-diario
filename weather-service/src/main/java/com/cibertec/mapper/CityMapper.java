package com.cibertec.mapper;

import com.cibertec.dto.internal.CityResponse;
import com.cibertec.model.City;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CityMapper {

    CityResponse toDTO(City city);
}
