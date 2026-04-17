package country_service.exception;

public class PaisNoEncontradoException extends RuntimeException {

    public PaisNoEncontradoException(String mensaje) {
        super(mensaje);
    }
}