package com.cibertec.mapper;

import com.cibertec.dto.external.CountryExternal;
import com.cibertec.dto.response.CountryResponse;

public class CountryMapper {

    public static CountryResponse toResponse(CountryExternal dto) {

        if (dto == null) return null;

        return new CountryResponse(
                dto.getName() != null ? dto.getName().getCommon() : null,
                (dto.getCapital() != null && !dto.getCapital().isEmpty())
                        ? dto.getCapital().get(0)
                        : null,
                dto.getRegion(),
                dto.getFlags() != null ? dto.getFlags().getPng() : null
        );
    }
}