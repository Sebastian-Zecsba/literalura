package com.kcire.literalura.principal;

import com.kcire.literalura.model.*;
import com.kcire.literalura.service.AutorService;
import com.kcire.literalura.service.ConsumoAPI;
import com.kcire.literalura.service.ConvierteDatos;
import com.kcire.literalura.service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;


public class Principal {


    private Scanner leer = new Scanner(System.in);
    private static final String URL_BASE = "https://gutendex.com/books/";
    private ConsumoAPI consumoApi = new ConsumoAPI();
    private ConvierteDatos conversor = new ConvierteDatos();

    private static LibroService libroService;
    private static AutorService autorService;

    public Principal(LibroService libroService, AutorService autorService) {
        Principal.libroService = libroService;
        Principal.autorService = autorService;
    }

    public void menu() {
        var opcion = -1;

        while (opcion != 0) {
            System.out.println("""
                      ***MENU***
                    1- Buscar libro por titulo
                    2- Listar libros registrados
                    3- Listar autores registrados
                    4- listar autores vivos en un determinado año
                    5- Listar libros por idioma
                    
                    0- Salir
                    ******************************
                    Seleccione una opción:
                    """);
            opcion = leer.nextInt();
            leer.nextLine();

            switch (opcion) {
                case 1:
                    obtenerLibroPorTitulo();
                    break;
                case 2:
                    listarLibrosRegistrados();
                    break;
                case 3:
                    listarAutoresRegistrados();
                    break;
                case 4:
                    listarAutoresVivos();
                    break;
                case 5:
                    listarLibrosPorIdioma();
                    break;
                case 0:
                    System.out.println("Cerrando la aplicación...");
                    break;
                default:
                    System.out.println("Opcion no valida...");
            }

        }

    }


    private void obtenerLibroPorTitulo() {
        System.out.println("Titulo del libro a buscar: ");
        var nombreLibro = leer.nextLine();

        // Obtener datos de la API
        var json = consumoApi.obtenerDatos(URL_BASE + "?search=" + normalizarTextoParaURL(nombreLibro));
        var datosBusqueda = conversor.obtenerDatos(json, Datos.class);

        // Buscar libro en los resultados de la API
        Optional<DatosLibro> libroBuscado = datosBusqueda.resultados().stream()
                .filter(l -> normalizarTexto(l.titulo()).contains(normalizarTexto(nombreLibro)))
                .findFirst();

        if (libroBuscado.isPresent()) {
            System.out.println("Libro encontrado...");
            manejarLibro(libroBuscado.get());
        } else {
            System.out.println("Libro no encontrado...");
        }
    }

    // Normalizar texto para búsquedas
    private String normalizarTexto(String texto) {
        return texto.toUpperCase().trim();
    }

    // Convertir espacios en URL-friendly
    private String normalizarTextoParaURL(String texto) {
        return texto.replace(" ", "+");
    }

    // Manejar lógica de creación/actualización del libro
    private void manejarLibro(DatosLibro datosLibro) {
        Libro libroExistente = libroService.obtenerLibroPorNombre(datosLibro.titulo());
        if (libroExistente != null) {
            System.out.println("El libro existe en el sistema...");
            System.out.println(libroExistente);
            return;
        }

        // Crear libro si no existe
        Libro libro = new Libro(datosLibro);
        datosLibro.autor().forEach(datosAutor -> libro.agregarAutor(obtenerOGuardarAutor(datosAutor)));
        libroService.guardarLibro(libro);

        System.out.println("Libro guardado en la bd...");
        System.out.println(libro);
    }

    // Obtener o guardar autor
    private Autor obtenerOGuardarAutor(DatosAutor datosAutor) {
        Autor autor = autorService.obtenerAutorPorNombre(datosAutor.nombre());
        if (autor != null) {
            System.out.println("El autor existe en el sistema...");
            return autor;
        }

        // Crear nuevo autor
        autor = new Autor(datosAutor);
        autorService.guardarAutor(autor);
        return autor;
    }

    //listar los libros registrador en la bd
    public void listarLibrosRegistrados() {
        List<Libro> libros = libroService.obtenerLibros();
        libros.forEach(System.out::println);
    }

    //listar los autores registrador en la bd
    private void listarAutoresRegistrados() {
        List<Autor> autores = autorService.obtenerAutores();
        autores.forEach(System.out::println);
    }

    //listar los autores vivos en un determinado año
    private void listarAutoresVivos() {
        System.out.println("Ingrese el año para listar los autores que esten vivos:");
        var anio = leer.nextInt();
        leer.nextLine();

        List<Autor> autoresVivos = autorService.obtenerAutoresVivosEnDeterminadoAnio(anio);
        autoresVivos.forEach(System.out::println);
    }

    //listar los libros por idioma
    private void listarLibrosPorIdioma() {
        System.out.println("""
                    ***Idiomas***
                1- Español
                2- Ingles
                3- Portugues
                
                -------------------
                Selecciona una opción:
                """);
        var opc = leer.nextInt();
        leer.nextLine();
        String idioma = "";

        switch (opc) {
            case 1:
                idioma = "es";
                break;
            case 2:
                idioma = "en";
                break;
            case 3:
                idioma = "pt";
                break;
            default:
                System.out.println("Opción invalida...");
        }
        List<Libro> librosIdioma = libroService.obtenerLibrosPorIdioma(idioma);
        librosIdioma.forEach(System.out::println);
    }

}
