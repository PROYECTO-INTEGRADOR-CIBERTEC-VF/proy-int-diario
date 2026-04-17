package country_service.controller;

import country_service.dto.response.CountryResponse;
import country_service.service.CountryService;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping("/api/countries")
public class CountryController {

    private final CountryService service;

    public CountryController(CountryService service) {
        this.service = service;
    }

    @GetMapping("/{code}")
    public ResponseEntity<CountryResponse> getByCode(
            @PathVariable
            @NotBlank(message = "El código no puede estar vacío")
            String code) {

        return ResponseEntity.ok(service.getByCode(code));
    }

    @GetMapping("/search")
    public ResponseEntity<CountryResponse[]> searchByName(
            @RequestParam
            @NotBlank(message = "El nombre no puede estar vacío")
            String name) {

        return ResponseEntity.ok(service.searchByName(name));
    }
}