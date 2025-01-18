# LiterAlura - Gestor de Libros y Autores


## Descripción del Proyecto

LiterAlura es un sistema desarrollado en **Java** con **Spring Boot** y la API [Gutendex](https://gutendex.com/) que permite gestionar un catálogo de libros y autores.  
Ofrece funcionalidades para buscar información, registrar nuevos libros y establecer relaciones entre libros y autores, con persistencia en una base de datos relacional [PostgreSQL](https://www.postgresql.org/).  

## Funcionalidades
![Project Features](https://github.com/user-attachments/assets/a80de37f-ab05-45a4-b963-2d1df8a45750)

- **Búsqueda de libros por título:**  
  Hara una busqueda de un libro en base a un titulo recibido, haciendo uso de la API, de ser encontrado lo agregara a la base de datos, al igual que su correspondiente autor, solo seran agregados si no existen en la bd.
![image](https://github.com/user-attachments/assets/810263e8-398e-4be6-b0a6-8c741397dbcd)

- **Listado de libros guardados en la base de datos:**
  Listara todos los libros registrados hasta el momento en la bd, mediante el uso de JpaRepository.
  
  ![image](https://github.com/user-attachments/assets/ab218478-bcc4-4d42-ad42-2ea4fc7070d7)

- **Listado de autores guardados en la base de datos:**  
  Listara todos los autores registrados hasta el momento en la bd, mediante el uso de JpaRepository.

  ![image](https://github.com/user-attachments/assets/0f74ee54-e386-40bf-91f5-49ef6b2daabf)

- **Listado de autores vivos durante un determinado año:**  
  Presentara a los autores que se encuentren o hayan encontrado vivos durante un determinado año, mediante una query que encontrara a un autor que haya nacido antes o en el mismo año indicado y que a la vez la fecha de su fallecimiento aun no haya ocurrido( es decir que sea mayor al año indicado) o que por su defecto no haya muerto (no tenga fecha de fallecimiento registrada).

![image](https://github.com/user-attachments/assets/e30c2bd6-0576-4c1d-b71d-14f2d32dd584)

  
- **Presentar los libros registrados en la bd, en base a un idioma seleccionado:**  
  Mostrara todos los libros registrados en la base datos, que esten escritos en el idioma seleccionado por el usuario.
  
![image](https://github.com/user-attachments/assets/e64cd938-1ded-4dbd-ad2b-9922165510f9)

  
