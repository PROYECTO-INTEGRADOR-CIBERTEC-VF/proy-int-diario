package com.cibertec.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Currency {


    @JsonProperty("currencies")
    private Map<String, CurrencyDetail> currencies;

    @JsonProperty("flags")
    private Flags flags;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class CurrencyDetail {
        @JsonProperty("name")
        private String name;

        @JsonProperty("symbol")
        private String symbol;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Flags {
        @JsonProperty("png")
        private String png;

        @JsonProperty("svg")
        private String svg;

        @JsonProperty("alt")
        private String alt;
    }
}