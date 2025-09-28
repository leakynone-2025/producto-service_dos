package co.empresa.libroservice.service;

import co.empresa.libroservice.domain.model.Libro;
import java.util.List;

public interface LibroService {
    List<Libro> obtenerTodosLosLibros();
    Libro obtenerLibroPorId(Long id);
    Libro crearLibro(Libro libro);
    Libro actualizarLibro(Long id, Libro libro);
    void eliminarLibro(Long id);
    Libro obtenerLibroPorIsbn(String isbn);
}