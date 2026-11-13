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
import hn.uth.poo.biblioteca.modelo.Autor;
import java.util.List;

public interface AutorDAO {
    void insertar(Autor autor) throws DatosException;
    void actualizar(Autor autor) throws DatosException;
    void eliminar(int id) throws DatosException;
    Autor buscarPorId(int id) throws DatosException;
    List<Autor> listarTodos() throws DatosException;
}
