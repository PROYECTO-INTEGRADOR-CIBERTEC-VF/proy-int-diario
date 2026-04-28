package com.cibertec.service.impl;

import com.cibertec.client.WeatherClient;
import com.cibertec.dto.external.WeatherApiResponse;
import com.cibertec.dto.external.WeatherConditionApiResponse;
import com.cibertec.dto.external.WeatherMetricsApiResponse;
import com.cibertec.dto.external.WindMetricsApiResponse;
import com.cibertec.dto.internal.WeatherConditionResponse;
import com.cibertec.dto.internal.WeatherResponse;
import com.cibertec.exception.CityNotFoundException;
import com.cibertec.exception.LocationNotFoundException;
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

import static org.junit.jupiter.api.Assertions.*;
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
    void getCurrentWeatherByCityId_WithAnyCity_ReturnsWeatherResponse() {
        City city = City.builder()
                .id(1L)
                .latitude(new BigDecimal("-12.0621065"))
                .longitude(new BigDecimal("-77.0365256"))
                .build();

        WeatherApiResponse externalDTO = WeatherApiResponse.builder()
                .name("Lima")
                .weather(new WeatherConditionApiResponse[]{
                        WeatherConditionApiResponse.builder()
                                .description("cielo claro").icon("01n").build()
                })
                .main(WeatherMetricsApiResponse.builder()
                        .temp(295.44).feels_like(295.79).humidity(79.0).build())
                .wind(WindMetricsApiResponse.builder().speed(5.14).build())
                .build();

        WeatherResponse expectedResponse = WeatherResponse.builder()
                .ubicacion("Lima")
                .clima(new WeatherConditionResponse[] {
                        WeatherConditionResponse.builder()
                                .estado("cielo claro").icono("01n").build()
                })
                .temperatura(295.44).sensacion_termica(295.79)
                .humedad(79.0).velocidad_viento(5.14).build();

        when(cityRepository.findById(1L)).thenReturn(Optional.of(city));
        when(weatherClient.getWeatherData(any(), any(), anyString(), anyString(), anyString()))
                .thenReturn(ResponseEntity.ok(externalDTO));
        when(weatherMapper.toInternalDTO(externalDTO)).thenReturn(expectedResponse);

        WeatherResponse result = weatherService.getCurrentWeatherByCityId(1L);

        assertNotNull(result);
        assertEquals("Lima", result.ubicacion());
        assertEquals("cielo claro", result.clima()[0].estado());
        assertEquals("01n", result.clima()[0].icono());
        assertEquals(295.44, result.temperatura());
        assertEquals(295.79, result.sensacion_termica());
        assertEquals(79.0, result.humedad());
        assertEquals(5.14, result.velocidad_viento());

        verify(cityRepository, times(1)).findById(1L);
        verify(weatherClient, times(1))
                .getWeatherData(any(), any(), anyString(), anyString(), anyString());
        verify(weatherMapper, times(1)).toInternalDTO(externalDTO);
    }

    @Test
    void getCurrentWeatherByCityId_WithNonExistentCity_ThrowsCityNotFoundException() {
        when(cityRepository.findById(0L)).thenReturn(Optional.empty());

        CityNotFoundException exception = assertThrows(CityNotFoundException.class,
                () -> weatherService.getCurrentWeatherByCityId(0L));

        assertEquals("City not found with id: 0", exception.getMessage());

        verify(cityRepository, times(1)).findById(0L);
        verifyNoInteractions(weatherClient);
        verifyNoInteractions(weatherMapper);
    }

    @Test
    void getCurrentWeatherByCityId_WithInvalidCoordinates_ThrowsLocationNotFoundException() {
        City city = City.builder()
                .id(1L)
                .latitude(new BigDecimal("-99.0000000"))
                .longitude(new BigDecimal("-99.0000000"))
                .build();

        WeatherApiResponse externalDTO = WeatherApiResponse.builder()
                .name("")
                .build();

        when(cityRepository.findById(1L)).thenReturn(Optional.of(city));
        when(weatherClient.getWeatherData(any(), any(), anyString(), anyString(), anyString()))
                .thenReturn(ResponseEntity.ok(externalDTO));

        LocationNotFoundException exception = assertThrows(LocationNotFoundException.class,
                () -> weatherService.getCurrentWeatherByCityId(1L));

        assertTrue(exception.getMessage().contains("No location found for coordinates"));

        verify(cityRepository, times(1)).findById(1L);
        verify(weatherClient, times(1))
                .getWeatherData(any(), any(), anyString(), anyString(), anyString());
        verifyNoInteractions(weatherMapper);
    }
}
