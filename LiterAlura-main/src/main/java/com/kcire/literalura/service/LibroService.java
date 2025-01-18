package com.kcire.literalura.service;

import com.kcire.literalura.model.Idioma;
import com.kcire.literalura.model.Libro;
import com.kcire.literalura.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LibroService {

    @Autowired
    private LibroRepository repository;


    public Libro obtenerLibroPorNombre(String titulo) {
        return repository.findByTitulo(titulo);
    }

    public void guardarLibro(Libro libro) {
        repository.save(libro);
    }

    public List<Libro> obtenerLibros() {
        return repository.findAll();
    }

    public List<Libro> obtenerLibrosPorIdioma(String idioma) {
        Idioma idiomaLibro = Idioma.fromString(idioma);
        return repository.findByIdiomas(idiomaLibro);
    }
}
