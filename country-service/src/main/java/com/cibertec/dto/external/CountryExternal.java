package com.cibertec.dto.external;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class CountryExternal {

    private Name name;
    private List<String> capital;
    private String region;
    private Map<String, Currency> currencies;
    private Flags flags;
}