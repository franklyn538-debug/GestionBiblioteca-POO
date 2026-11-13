/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hn.uth.poo.biblioteca.servicio;

/**
 *
 * @author frank
 */
import hn.uth.poo.biblioteca.dao.AutorDAO;
import hn.uth.poo.biblioteca.dao.LibroDAO;
import hn.uth.poo.biblioteca.dao.impl.AutorDAOImpl;
import hn.uth.poo.biblioteca.dao.impl.LibroDAOImpl;
import hn.uth.poo.biblioteca.excepciones.DatosException;
import hn.uth.poo.biblioteca.excepciones.DatoInvalidoException;
import hn.uth.poo.biblioteca.modelo.Autor;
import hn.uth.poo.biblioteca.modelo.Libro;
import java.util.List;

public class BibliotecaServicio {
    private AutorDAO autorDAO;
    private LibroDAO libroDAO;

    public BibliotecaServicio() {
        this.autorDAO = new AutorDAOImpl();
        this.libroDAO = new LibroDAOImpl();
    }

    //  MÉTODOS PARA AUTORES
    public void guardarAutor(String nombre, String nacionalidad) throws DatosException, DatoInvalidoException {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new DatoInvalidoException("El nombre del autor no puede estar vacio.");
        }
        if (nacionalidad == null || nacionalidad.trim().isEmpty()) {
            throw new DatoInvalidoException("La nacionalidad no puede estar vacia.");
        }
        Autor autor = new Autor(nombre.trim(), nacionalidad.trim());
        autorDAO.insertar(autor);
    }

    public List<Autor> listarAutores() throws DatosException {
        return autorDAO.listarTodos();
    }

    public void eliminarAutor(int id) throws DatosException {
        autorDAO.eliminar(id);
    }

    // MÉTODOS PARA LIBROS
    public void guardarLibro(String isbn, String titulo, int anio, boolean disponible, Autor autor) throws DatosException, DatoInvalidoException {
        if (isbn == null || isbn.trim().isEmpty()) {
            throw new DatoInvalidoException("El ISBN del libro no puede estar vacio.");
        }
        if (titulo == null || titulo.trim().isEmpty()) {
            throw new DatoInvalidoException("El titulo del libro no puede estar vacio.");
        }
        if (autor == null) {
            throw new DatoInvalidoException("Debe seleccionar un autor valido para el libro.");
        }
        Libro libro = new Libro(isbn.trim(), titulo.trim(), anio, disponible, autor);
        libroDAO.insertar(libro);
    }

    public List<Libro> listarLibros() throws DatosException {
        return libroDAO.listarTodos();
    }

    public void eliminarLibro(int id) throws DatosException {
        libroDAO.eliminar(id);
    }
}
