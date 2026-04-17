package country_service.dto.external;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class CountryExternalDto {

    private Name name;
    private List<String> capital;
    private String region;
    private Map<String, Currency> currencies;
    private Flags flags;

    @Data
    public static class Name {
        private String common;
    }

    @Data
    public static class Currency {
        private String name;
        private String symbol;
    }

    @Data
    public static class Flags {
        private String png;
    }
}