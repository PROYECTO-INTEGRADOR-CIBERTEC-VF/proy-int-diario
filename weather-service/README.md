# Weather Service (Spring Boot)

Microservicio RESTful para obtener datos meteorológicos actuales mediante la API de OpenWeather.

---

## Resumen técnico
- Módulo Spring Boot independiente: weather-service
- Exposición REST con un endpoint: `GET /weather`
- Cliente HTTP remoto: OpenWeather via Feign
- Mapeo de DTOs: MapStruct
- Configuración sensible: clave API de OpenWeather fuera del código

**Notas:** 
- Otras tecnologías como Spring Boot Actuator o Spring Cache disponibles, pero aún no han sido configuradas.
- Integración con Eureka faltante.

---

## Qué hace
- Recibe coordenadas de latitud y longitud y un idioma opcional, y devuelve el estado actual del clima en formato simplificado.
- Integra con OpenWeather para obtener datos externos y los transforma a un modelo interno ligero para consumo por otros servicios/frontend.
- Manejo de errores centralizado para casos de ubicación no encontrada u otros fallos de recursos externos.

El endpoint público es:
- `GET /weather?lat=<lat>&lon=<lon>&lang=<lang>`

- Ejemplo de respuesta exitosa:
```
{
    "ubicacion": "Rímac",
    "clima": [
        {
            "estado": "muy nuboso",
            "icono": "04n"
        }
    ],
    "temperatura": 24.16,
    "sensacion_termica": 24.62,
    "humedad": 76.0,
    "velocidad_viento": 2.29
}
```

**Notas:**
- Parámetro "lang" es opcional y, si no es definido, será por defecto "es".
- Solicitudes a la API por nombre de ciudad, código postal e ID de ciudad han quedado obsoletas. Si bien aún se pueden usar, ya no se ofrecen correcciones de errores ni actualizaciones para esta funcionalidad. Por esa razón, se usa longitud y latitud.
- Para conseguir la longitud y latitud de una ciudad, se debe usar la API de Geocoding (también perteneciente a OpenWeather API).
- Tomando en cuenta los dos puntos anteriores, considerar la posibilidad de implementar un módulo de consumo de la API de Geocoding o simplemente integrarlo en el mismo módulo de weather-service.

---

## Uso de ejemplos (consultas rápidas)
- Consulta simple (lat/lon) con idioma por defecto (es):
  - GET /weather?lat=-12.0621065&lon=-77.0365256
- Consulta con idioma específico:
  - GET /weather?lat=-12.0621065&lon=-77.0365256&lang=en
- Consultas erróneas:
  - GET /weather
  - GET /weather?lat=1
  - GET /weather?lon=1
  - GET /weather?lat=1&lon=1
  - GET /weather?lat=a
  - GET /weather?lat=-12.0621065&lon=a

- Consulta externa para obtener longitud y latitud de una ciudad mediante la API de Geocoding (requiere API Key):
  - GET `http://api.openweathermap.org/geo/1.0/direct?q=Lima,PE&appid=<appid>`