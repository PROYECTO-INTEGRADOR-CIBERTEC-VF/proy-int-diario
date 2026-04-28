package com.cibertec.service.impl;

import com.cibertec.dto.internal.CityResponse;
import com.cibertec.mapper.CityMapper;
import com.cibertec.model.City;
import com.cibertec.model.Country;
import com.cibertec.model.State;
import com.cibertec.repository.CityRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CityServiceImplTest {

    @Mock
    private CityRepository cityRepository;

    @Mock
    private CityMapper cityMapper;

    @InjectMocks
    private CityServiceImpl cityService;

    @Test
    void getCities_WhenMoreThanZeroCities_ReturnsCitiesList() {
        List<City> cities = loadCities();

        CityResponse response1 = CityResponse.builder()
                        .id(1L).name("Lima").country("Perú").state(null).build();
        CityResponse response2 = CityResponse.builder()
                        .id(212L).name("Springfield").country("Estados Unidos")
                        .state("Illinois").build();

        when(cityRepository.findAllByOrderByNameAsc()).thenReturn(cities);
        when(cityMapper.toDTO(cities.getFirst())).thenReturn(response1);
        when(cityMapper.toDTO(cities.get(1))).thenReturn(response2);

        List<CityResponse> result = cityService.getCities();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(212L, result.get(1).id());
        assertEquals("Lima", result.get(0).name());
        assertEquals("Estados Unidos", result.get(1).country());
        assertNull(result.get(0).state());
        assertEquals("Illinois", result.get(1).state());

        verify(cityRepository, times(1)).findAllByOrderByNameAsc();
        verify(cityMapper, times(2)).toDTO(any(City.class));
    }

    @Test
    void getCities_WhenZeroCities_ReturnsEmptyCitiesList() {
        when(cityRepository.findAllByOrderByNameAsc()).thenReturn(Collections.emptyList());

        List<CityResponse> result = cityService.getCities();

        assertNotNull(result);
        assertTrue(result.isEmpty());

        verify(cityRepository, times(1)).findAllByOrderByNameAsc();
        verifyNoInteractions(cityMapper);
    }

    List<City> loadCities() {
        Country peru = Country.builder().id(1L).name("Perú").build();
        Country usa = Country.builder().id(2L).name("Estados Unidos").build();

        State illinois = State.builder().id(7L).name("Illinois").country(usa).build();

        List<City> cities = new ArrayList<>();
        cities.add(City.builder()
                .id(1L).name("Lima")
                .latitude(new BigDecimal("-12.0621065"))
                .longitude(new BigDecimal("-77.0365256"))
                .country(peru).state(null).build());
        cities.add(City.builder()
                .id(212L).name("Springfield")
                .latitude(new BigDecimal("39.7990175"))
                .longitude(new BigDecimal("-89.6439575"))
                .country(usa).state(illinois).build());

        return cities;
    }
}
