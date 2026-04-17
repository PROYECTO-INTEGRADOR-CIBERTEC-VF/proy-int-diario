package com.cibertec.service;

import com.cibertec.dto.internal.CityResponse;

import java.util.List;

public interface CityService {
    List<CityResponse> getCities();
}
