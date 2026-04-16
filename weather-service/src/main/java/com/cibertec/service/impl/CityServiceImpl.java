package com.cibertec.service.impl;

import com.cibertec.repository.CityRepository;
import com.cibertec.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CityServiceImpl implements CityService {
    private final CityRepository cityRepository;
}
