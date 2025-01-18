package com.kcire.literalura.repository;

import com.kcire.literalura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Long> {

    //solo necesito estos 2 metodos, porque tienen que recibir datos,
    // el resto de metodos estan añadidos por deafult por la Interface JpaRepository
    Autor findByNombre(String nombre);

    //Fallecieron después del año especificado, o No tienen fecha de fallecimiento registrada (aún vivos, en el año especificado).
    @Query("SELECT a FROM Autor a WHERE a.fechaNacimiento <= :anio AND (a.fechaFallecimiento > :anio OR a.fechaFallecimiento IS NULL)")
    List<Autor> autoresVivosEnDeterminadoAnio(int anio);

}
