package co.empresa.libroservice.domain.exception;

public class LibroExistenteException extends RuntimeException {
    public LibroExistenteException(String message) {
        super(message);
    }
}
