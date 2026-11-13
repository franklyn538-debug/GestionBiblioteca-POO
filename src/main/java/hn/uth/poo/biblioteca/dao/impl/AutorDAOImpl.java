/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hn.uth.poo.biblioteca.dao.impl;

/**
 *
 * @author frank
 */
import hn.uth.poo.biblioteca.dao.AutorDAO;
import hn.uth.poo.biblioteca.dao.Conexion;
import hn.uth.poo.biblioteca.excepciones.DatosException;
import hn.uth.poo.biblioteca.modelo.Autor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AutorDAOImpl implements AutorDAO {

    @Override
    public void insertar(Autor autor) throws DatosException {
        String sql = "INSERT INTO autores (nombre, nacionalidad) VALUES (?, ?)";
        try (Connection conn = Conexion.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, autor.getNombre());
            ps.setString(2, autor.getNacionalidad());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DatosException("Error al insertar el autor: " + e.getMessage(), e);
        }
    }

    @Override
    public void actualizar(Autor autor) throws DatosException {
        String sql = "UPDATE autores SET nombre = ?, nacionalidad = ? WHERE id = ?";
        try (Connection conn = Conexion.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, autor.getNombre());
            ps.setString(2, autor.getNacionalidad());
            ps.setInt(3, autor.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DatosException("Error al actualizar el autor: " + e.getMessage(), e);
        }
    }

    @Override
    public void eliminar(int id) throws DatosException {
        String sql = "DELETE FROM autores WHERE id = ?";
        try (Connection conn = Conexion.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DatosException("Error al eliminar el autor (verifique que no tenga libros asociados): " + e.getMessage(), e);
        }
    }

    @Override
    public Autor buscarPorId(int id) throws DatosException {
        String sql = "SELECT id, nombre, nacionalidad FROM autores WHERE id = ?";
        try (Connection conn = Conexion.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Autor(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("nacionalidad")
                    );
                }
            }
            return null;
        } catch (SQLException e) {
            throw new DatosException("Error al buscar autor por ID: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Autor> listarTodos() throws DatosException {
        List<Autor> lista = new ArrayList<>();
        String sql = "SELECT id, nombre, nacionalidad FROM autores ORDER BY nombre ASC";
        try (Connection conn = Conexion.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(new Autor(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("nacionalidad")
                ));
            }
            return lista;
        } catch (SQLException e) {
            throw new DatosException("Error al listar los autores: " + e.getMessage(), e);
        }
    }
}
