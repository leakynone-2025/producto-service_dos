package co.empresa.libroservice.domain.exception;

public class LibroNoEncontradoException extends RuntimeException {
    public LibroNoEncontradoException(String message) {
        super(message);
    }
}