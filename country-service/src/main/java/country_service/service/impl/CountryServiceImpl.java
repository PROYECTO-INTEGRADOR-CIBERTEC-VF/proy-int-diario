package country_service.service.impl;

import country_service.client.CountryClient;
import country_service.dto.external.CountryExternalDto;
import country_service.dto.response.CountryResponse;
import country_service.exception.PaisNoEncontradoException;
import country_service.mapper.CountryMapper;
import country_service.service.CountryService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Arrays;

@Service
public class CountryServiceImpl implements CountryService {

    private final CountryClient client;

    public CountryServiceImpl(CountryClient client) {
        this.client = client;
    }

    @Override
    public CountryResponse getByCode(String code) {

        try {
            CountryExternalDto[] response = client.getByCode(code);

            if (response == null || response.length == 0) {
                throw new PaisNoEncontradoException("No se encontró el país con código: " + code);
            }

            return CountryMapper.toResponse(response[0]);

        } catch (HttpClientErrorException.NotFound e) {
            throw new PaisNoEncontradoException("No se encontró el país con código: " + code);
        }
    }

    @Override
    public CountryResponse[] searchByName(String name) {

        CountryExternalDto[] response = client.getByName(name);

        validarRespuesta(response, "No se encontraron países con el nombre: " + name);

        return Arrays.stream(response)
                .map(CountryMapper::toResponse)
                .toArray(CountryResponse[]::new);
    }

    private void validarRespuesta(CountryExternalDto[] response, String mensaje) {
        if (response == null || response.length == 0) {
            throw new PaisNoEncontradoException(mensaje);
        }
    }
}