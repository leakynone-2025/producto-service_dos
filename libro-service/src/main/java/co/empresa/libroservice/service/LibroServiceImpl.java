package co.empresa.libroservice.service;

import co.empresa.libroservice.domain.exception.LibroExistenteException;
import co.empresa.libroservice.domain.exception.LibroNoEncontradoException;
import co.empresa.libroservice.domain.model.Libro;
import co.empresa.libroservice.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibroServiceImpl implements LibroService {

    private final LibroRepository libroRepository;

    @Autowired
    public LibroServiceImpl(LibroRepository libroRepository) {
        this.libroRepository = libroRepository;
    }

    @Override
    public List<Libro> obtenerTodosLosLibros() {
        return libroRepository.findAll();
    }

    @Override
    public Libro obtenerLibroPorId(Long id) {
        return libroRepository.findById(id)
                .orElseThrow(() -> new LibroNoEncontradoException("Libro no encontrado con ID: " + id));
    }

    @Override
    public Libro crearLibro(Libro libro) {
        if (libroRepository.existsByIsbn(libro.getIsbn())) {
            throw new LibroExistenteException("Ya existe un libro con el ISBN: " + libro.getIsbn());
        }
        return libroRepository.save(libro);
    }

    @Override
    public Libro actualizarLibro(Long id, Libro libroActualizado) {
        Libro libroExistente = obtenerLibroPorId(id);

        // Verificar si el ISBN ya existe en otro libro
        if (!libroExistente.getIsbn().equals(libroActualizado.getIsbn()) &&
                libroRepository.existsByIsbn(libroActualizado.getIsbn())) {
            throw new LibroExistenteException("Ya existe otro libro con el ISBN: " + libroActualizado.getIsbn());
        }

        libroExistente.setTitulo(libroActualizado.getTitulo());
        libroExistente.setAutor(libroActualizado.getAutor());
        libroExistente.setIsbn(libroActualizado.getIsbn());
        libroExistente.setPrecio(libroActualizado.getPrecio());

        return libroRepository.save(libroExistente);
    }

    @Override
    public void eliminarLibro(Long id) {
        Libro libro = obtenerLibroPorId(id);
        libroRepository.delete(libro);
    }

    @Override
    public Libro obtenerLibroPorIsbn(String isbn) {
        Libro libro = libroRepository.findByIsbn(isbn);
        if (libro == null) {
            throw new LibroNoEncontradoException("Libro no encontrado con ISBN: " + isbn);
        }
        return libro;
    }
}