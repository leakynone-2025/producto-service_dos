package co.empresa.libroservice.repository;

import co.empresa.libroservice.domain.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibroRepository extends JpaRepository<Libro, Long> {
    Libro findByIsbn(String isbn);
    boolean existsByIsbn(String isbn);
}