package com.cibertec.service.impl;

import com.cibertec.client.WeatherClient;
import com.cibertec.dto.external.WeatherApiResponse;
import com.cibertec.dto.external.WeatherConditionApiResponse;
import com.cibertec.dto.external.WeatherMetricsApiResponse;
import com.cibertec.dto.external.WindMetricsApiResponse;
import com.cibertec.dto.internal.WeatherConditionResponse;
import com.cibertec.dto.internal.WeatherResponse;
import com.cibertec.mapper.WeatherMapper;
import com.cibertec.model.City;
import com.cibertec.model.Country;
import com.cibertec.model.State;
import com.cibertec.repository.CityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class WeatherServiceImplTest {

    @Mock
    private CityRepository cityRepository;

    @Mock
    private WeatherClient weatherClient;

    @Mock
    private WeatherMapper weatherMapper;

    @InjectMocks
    private WeatherServiceImpl weatherService;

    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(weatherService, "apiKey", "apiKey");
        ReflectionTestUtils.setField(weatherService, "lang", "es");
        ReflectionTestUtils.setField(weatherService, "units", "metric");
    }

    @Test
    void getCurrentWeatherByCityId_WithUsaCity_ReturnsCityWithState() {
        Country country = Country.builder().id(2L).name("Estados Unidos").build();
        State state = State.builder().id(7L).name("Illinois").country(country).build();

        City city = City.builder()
                .id(1L).name("Springfield")
                .latitude(new BigDecimal("39.7990175"))
                .longitude(new BigDecimal("-89.6439575"))
                .country(country).state(state).build();

        WeatherApiResponse externalDTO = WeatherApiResponse.builder()
                .name("Springfield")
                .weather(new WeatherConditionApiResponse[]{
                        WeatherConditionApiResponse.builder()
                                .description("tormenta con lluvia intensa").icon("11d").build()
                })
                .main(WeatherMetricsApiResponse.builder()
                        .temp(288.13).feels_like(288.14).humidity(94.0).build())
                .wind(WindMetricsApiResponse.builder().speed(8.23).build())
                .build();

        WeatherResponse expectedResponse = WeatherResponse.builder()
                .ubicacion("Springfield")
                .clima(new WeatherConditionResponse[]{
                        WeatherConditionResponse.builder()
                                .estado("tormenta con lluvia intensa").icono("11d").build()
                })
                .temperatura(288.13).sensacion_termica(288.14)
                .humedad(94.0).velocidad_viento(5.0).build();

        when(cityRepository.findById(1L)).thenReturn(Optional.of(city));
        when(weatherClient.getWeatherData(any(), any(), anyString(), anyString(), anyString()))
                .thenReturn(ResponseEntity.ok(externalDTO));
        when(weatherMapper.toInternalDTO(externalDTO)).thenReturn(expectedResponse);

        WeatherResponse result = weatherService.getCurrentWeatherByCityId(1L);

        assertNotNull(result);
        assertEquals("Springfield", result.ubicacion());
        assertEquals(288.13, result.temperatura());
        assertEquals(288.14, result.sensacion_termica());
        assertEquals(94, result.humedad());
        assertEquals(5.0, result.velocidad_viento());

        verify(cityRepository, times(1)).findById(1L);
        verify(weatherClient, times(1))
                .getWeatherData(any(), any(), anyString(), anyString(), anyString());
        verify(weatherMapper, times(1)).toInternalDTO(externalDTO);
    }
}
