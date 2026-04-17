package country_service.service;

import country_service.dto.response.CountryResponse;

public interface CountryService {

    CountryResponse getByCode(String code);

    CountryResponse[] searchByName(String name);
}