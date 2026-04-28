package com.cibertec.serviceimpl;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import com.cibertec.dto.DiaryEntryRequest;
import com.cibertec.dto.DiaryEntryResponse;
import com.cibertec.exception.ResourceNotFound;
import com.cibertec.model.DiaryEntry;
import com.cibertec.repository.DiaryEntryRepository;
import com.cibertec.service.impl.DiaryEntryServiceImpl;
import com.cibertec.util.DiaryEntryMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DiaryEntryServiceImplTest {
    @Mock
    private DiaryEntryRepository diaryEntryRepository;

    @Mock
    private DiaryEntryMapper diaryEntryMapper;

    @InjectMocks
    private DiaryEntryServiceImpl diaryEntryService;

    private DiaryEntry diaryEntry;
    private DiaryEntryRequest diaryEntryRequest;
    private DiaryEntryResponse diaryEntryResponse;
    private DiaryEntry updatedDiaryEntry;
    private DiaryEntryResponse updatedDiaryEntryResponse;

    @BeforeEach
    void setUp() {
        LocalDateTime createdAt = LocalDateTime.of(2026, 4, 27, 10, 0);
        LocalDateTime updatedAt = LocalDateTime.of(2026, 4, 27, 11, 0);

        diaryEntry = new DiaryEntry(
                1L,
                "Mi primer día",
                "Hoy escribí una entrada larga y válida",
                10L,
                "Happy",
                "Lima",
                "Sunny",
                "personal,diario",
                true,
                false,
                createdAt,
                updatedAt
        );

        diaryEntryRequest = new DiaryEntryRequest(
                "Entrada nueva",
                "Este contenido tiene la longitud suficiente",
                10L,
                "Calm",
                "Cusco",
                "Cloudy",
                "travel,study",
                false,
                true
        );

        diaryEntryResponse = new DiaryEntryResponse(
                1L,
                diaryEntry.getTitle(),
                diaryEntry.getContent(),
                diaryEntry.getUserId(),
                diaryEntry.getMood(),
                diaryEntry.getLocation(),
                diaryEntry.getWeather(),
                diaryEntry.getTags(),
                diaryEntry.getIsFavorite(),
                diaryEntry.getIsPrivate(),
                createdAt,
                updatedAt
        );

        updatedDiaryEntry = new DiaryEntry(
                1L,
                "Entrada editada",
                "El contenido fue actualizado correctamente",
                11L,
                "Reflective",
                "Arequipa",
                "Windy",
                "updated,notes",
                false,
                true,
                createdAt,
                updatedAt
        );

        updatedDiaryEntryResponse = new DiaryEntryResponse(
                1L,
                updatedDiaryEntry.getTitle(),
                updatedDiaryEntry.getContent(),
                updatedDiaryEntry.getUserId(),
                updatedDiaryEntry.getMood(),
                updatedDiaryEntry.getLocation(),
                updatedDiaryEntry.getWeather(),
                updatedDiaryEntry.getTags(),
                updatedDiaryEntry.getIsFavorite(),
                updatedDiaryEntry.getIsPrivate(),
                createdAt,
                updatedAt
        );
    }

    @Test
    void getAllDiaryEntries_returnsMappedEntries() {
        List<DiaryEntry> entities = List.of(diaryEntry);
        List<DiaryEntryResponse> responses = List.of(diaryEntryResponse);

        when(diaryEntryRepository.findAll()).thenReturn(entities);
        when(diaryEntryMapper.toDtoList(entities)).thenReturn(responses);

        List<DiaryEntryResponse> result = diaryEntryService.getAllDiaryEntries();

        assertEquals(responses, result);
        verify(diaryEntryRepository).findAll();
        verify(diaryEntryMapper).toDtoList(entities);
    }

    @Test
    void getDiaryEntryById_returnsMappedEntry() {
        when(diaryEntryRepository.findById(1L)).thenReturn(Optional.of(diaryEntry));
        when(diaryEntryMapper.toDto(diaryEntry)).thenReturn(diaryEntryResponse);

        DiaryEntryResponse result = diaryEntryService.getDiaryEntryById(1L);

        assertEquals(diaryEntryResponse, result);
        verify(diaryEntryRepository).findById(1L);
        verify(diaryEntryMapper).toDto(diaryEntry);
    }

    @Test
    void getDiaryEntryById_whenMissing_throwsResourceNotFound() {
        when(diaryEntryRepository.findById(1L)).thenReturn(Optional.empty());

        ResourceNotFound exception = assertThrows(ResourceNotFound.class,
                () -> diaryEntryService.getDiaryEntryById(1L));

        assertEquals("Diary entry not found with id: 1", exception.getMessage());
        verify(diaryEntryRepository).findById(1L);
    }

    @Test
    void getDiaryEntriesByUserId_returnsMappedEntries() {
        List<DiaryEntry> entities = List.of(diaryEntry);
        List<DiaryEntryResponse> responses = List.of(diaryEntryResponse);

        when(diaryEntryRepository.findByUserIdOrderByCreatedAtDesc(10L)).thenReturn(entities);
        when(diaryEntryMapper.toDtoList(entities)).thenReturn(responses);

        List<DiaryEntryResponse> result = diaryEntryService.getDiaryEntriesByUserId(10L);

        assertEquals(responses, result);
        verify(diaryEntryRepository).findByUserIdOrderByCreatedAtDesc(10L);
        verify(diaryEntryMapper).toDtoList(entities);
    }

    @Test
    void createDiaryEntry_savesAndReturnsDto() {
        when(diaryEntryMapper.toEntity(diaryEntryRequest)).thenReturn(diaryEntry);
        when(diaryEntryRepository.save(diaryEntry)).thenReturn(diaryEntry);
        when(diaryEntryMapper.toDto(diaryEntry)).thenReturn(diaryEntryResponse);

        DiaryEntryResponse result = diaryEntryService.createDiaryEntry(diaryEntryRequest);

        assertEquals(diaryEntryResponse, result);
        verify(diaryEntryMapper).toEntity(diaryEntryRequest);
        verify(diaryEntryRepository).save(diaryEntry);
        verify(diaryEntryMapper).toDto(diaryEntry);
    }

    @Test
    void updateDiaryEntry_updatesExistingEntry() {
        when(diaryEntryRepository.findById(1L)).thenReturn(Optional.of(diaryEntry));
        when(diaryEntryRepository.save(diaryEntry)).thenReturn(diaryEntry);
        when(diaryEntryMapper.toDto(diaryEntry)).thenReturn(updatedDiaryEntryResponse);

        DiaryEntryResponse result = diaryEntryService.updateDiaryEntry(1L, diaryEntryRequest);

        assertEquals(updatedDiaryEntryResponse, result);
        assertEquals(diaryEntryRequest.title(), diaryEntry.getTitle());
        assertEquals(diaryEntryRequest.content(), diaryEntry.getContent());
        assertEquals(diaryEntryRequest.userId(), diaryEntry.getUserId());
        assertEquals(diaryEntryRequest.mood(), diaryEntry.getMood());
        assertEquals(diaryEntryRequest.location(), diaryEntry.getLocation());
        assertEquals(diaryEntryRequest.weather(), diaryEntry.getWeather());
        assertEquals(diaryEntryRequest.tags(), diaryEntry.getTags());
        assertEquals(diaryEntryRequest.isFavorite(), diaryEntry.getIsFavorite());
        assertEquals(diaryEntryRequest.isPrivate(), diaryEntry.getIsPrivate());
        verify(diaryEntryRepository).findById(1L);
        verify(diaryEntryRepository).save(diaryEntry);
        verify(diaryEntryMapper).toDto(diaryEntry);
    }

    @Test
    void updateDiaryEntry_whenMissing_throwsResourceNotFound() {
        when(diaryEntryRepository.findById(1L)).thenReturn(Optional.empty());

        ResourceNotFound exception = assertThrows(ResourceNotFound.class,
                () -> diaryEntryService.updateDiaryEntry(1L, diaryEntryRequest));

        assertEquals("Diary entry not found with id: 1", exception.getMessage());
        verify(diaryEntryRepository).findById(1L);
    }

    @Test
    void deleteDiaryEntry_deletesExistingEntry() {
        when(diaryEntryRepository.findById(1L)).thenReturn(Optional.of(diaryEntry));
        doNothing().when(diaryEntryRepository).delete(diaryEntry);

        diaryEntryService.deleteDiaryEntry(1L);

        verify(diaryEntryRepository).findById(1L);
        verify(diaryEntryRepository).delete(diaryEntry);
    }
}
