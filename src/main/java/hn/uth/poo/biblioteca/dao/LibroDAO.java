/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package hn.uth.poo.biblioteca.dao;

/**
 *
 * @author frank
 */
import hn.uth.poo.biblioteca.excepciones.DatosException;
import hn.uth.poo.biblioteca.modelo.Libro;
import java.util.List;

public interface LibroDAO {
    void insertar(Libro libro) throws DatosException;
    void actualizar(Libro libro) throws DatosException;
    void eliminar(int id) throws DatosException;
    Libro buscarPorId(int id) throws DatosException;
    List<Libro> listarTodos() throws DatosException;
}
