package country_service.mapper;

import country_service.dto.external.CountryExternalDto;
import country_service.dto.response.CountryResponse;

public class CountryMapper {

    public static CountryResponse toResponse(CountryExternalDto dto) {

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