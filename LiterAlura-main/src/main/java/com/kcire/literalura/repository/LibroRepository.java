package com.kcire.literalura.repository;

import com.kcire.literalura.model.Idioma;
import com.kcire.literalura.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LibroRepository extends JpaRepository<Libro, Long> {
    //solo necesito estos 2 metodos, porque tienen que recibir datos,
    // el resto de metodos estan añadidos por deafult por la Interface JpaRepository
    Libro findByTitulo(String titulo);

    List<Libro> findByIdiomas(Idioma idioma);
}
