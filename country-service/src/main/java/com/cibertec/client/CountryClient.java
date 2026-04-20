package com.cibertec.client;

import com.cibertec.dto.external.CountryExternal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class CountryClient {

    private final RestTemplate restTemplate = new RestTemplate();

    private static final String BASE_URL = "https://restcountries.com/v3.1";

   
    public CountryExternal[] getByCode(String code) {
        String url = BASE_URL + "/alpha/" + code;
        return restTemplate.getForObject(url, CountryExternal[].class);
    }


    public CountryExternal[] getByName(String name) {
        String url = BASE_URL + "/name/" + name;
        return restTemplate.getForObject(url, CountryExternal[].class);
    }


    public CountryExternal[] getAllCountries() {
        String url = BASE_URL +  "/all?fields=name,cca2,region,capital,flags";
        return restTemplate.getForObject(url, CountryExternal[].class);
    }


    public CountryExternal[] getByRegion(String region) {
        String url = BASE_URL + "/region/" + region;
        return restTemplate.getForObject(url, CountryExternal[].class);
    }
}
