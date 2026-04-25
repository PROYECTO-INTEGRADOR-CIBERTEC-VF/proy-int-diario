package com.cibertec.service.impl;

import com.cibertec.dto.internal.CityResponse;
import com.cibertec.mapper.CityMapper;
import com.cibertec.model.City;
import com.cibertec.repository.CityRepository;
import com.cibertec.service.CityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class CityServiceImpl implements CityService {
    private final CityRepository cityRepository;
    private final CityMapper cityMapper;

    @Override
    public List<CityResponse> getCities() {
        List<City> cities = cityRepository.findAllByOrderByNameAsc();

        log.info("Fetching cities: {}", cities);

        List<CityResponse> response = cities.stream()
                .map(cityMapper::toDTO)
                .collect(Collectors.toList());

        log.info("Returning cities response: {}", response);

        return response;
    }
}
