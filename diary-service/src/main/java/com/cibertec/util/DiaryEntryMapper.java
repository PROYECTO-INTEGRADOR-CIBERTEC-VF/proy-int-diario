package com.cibertec.util;

import com.cibertec.dto.DiaryEntryRequest;
import com.cibertec.dto.DiaryEntryResponse;
import com.cibertec.model.DiaryEntry;
import org.mapstruct.Mapper;

import java.util.List;


@Mapper(componentModel = "spring")
public interface DiaryEntryMapper {

    DiaryEntry toEntity(DiaryEntryRequest request);
    DiaryEntryResponse toDto(DiaryEntry entity);
    List<DiaryEntryResponse> toDtoList(List<DiaryEntry> entities);
}
