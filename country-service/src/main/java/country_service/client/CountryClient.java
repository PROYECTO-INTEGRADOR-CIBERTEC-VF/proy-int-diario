package country_service.client;

import country_service.dto.external.CountryExternalDto;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class CountryClient {

    private final RestTemplate restTemplate = new RestTemplate();

    private final String BASE_URL = "https://restcountries.com/v3.1";

    // Buscar por código
    public CountryExternalDto[] getByCode(String code) {
        String url = BASE_URL + "/alpha/" + code;
        return restTemplate.getForObject(url, CountryExternalDto[].class);
    }

    // Buscar por nombre
    public CountryExternalDto[] getByName(String name) {
        String url = BASE_URL + "/name/" + name;
        return restTemplate.getForObject(url, CountryExternalDto[].class);
    }
}