/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hn.uth.poo.biblioteca.dao.impl;

/**
 *
 * @author frank
 */
import hn.uth.poo.biblioteca.dao.Conexion;
import hn.uth.poo.biblioteca.dao.LibroDAO;
import hn.uth.poo.biblioteca.excepciones.DatosException;
import hn.uth.poo.biblioteca.modelo.Autor;
import hn.uth.poo.biblioteca.modelo.Libro;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LibroDAOImpl implements LibroDAO {

    @Override
    public void insertar(Libro libro) throws DatosException {
        String sql = "INSERT INTO libros (isbn, titulo, anio_publicacion, disponible, autor_id) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = Conexion.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, libro.getIsbn());
            ps.setString(2, libro.getTitulo());
            ps.setInt(3, libro.getAnioPublicacion());
            ps.setInt(4, libro.isDisponible() ? 1 : 0);
            ps.setInt(5, libro.getAutor().getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DatosException("Error al insertar el libro: " + e.getMessage(), e);
        }
    }

    @Override
    public void actualizar(Libro libro) throws DatosException {
        String sql = "UPDATE libros SET isbn = ?, titulo = ?, anio_publicacion = ?, disponible = ?, autor_id = ? WHERE id = ?";
        try (Connection conn = Conexion.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, libro.getIsbn());
            ps.setString(2, libro.getTitulo());
            ps.setInt(3, libro.getAnioPublicacion());
            ps.setInt(4, libro.isDisponible() ? 1 : 0);
            ps.setInt(5, libro.getAutor().getId());
            ps.setInt(6, libro.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DatosException("Error al actualizar el libro: " + e.getMessage(), e);
        }
    }

    @Override
    public void eliminar(int id) throws DatosException {
        String sql = "DELETE FROM libros WHERE id = ?";
        try (Connection conn = Conexion.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DatosException("Error al eliminar el libro: " + e.getMessage(), e);
        }
    }

    @Override
    public Libro buscarPorId(int id) throws DatosException {
        String sql = """
            SELECT l.id, l.isbn, l.titulo, l.anio_publicacion, l.disponible,
                   a.id AS autor_id, a.nombre AS autor_nombre, a.nacionalidad AS autor_nacionalidad
            FROM libros l
            INNER JOIN autores a ON l.autor_id = a.id
            WHERE l.id = ?
            """;
        try (Connection conn = Conexion.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Autor autor = new Autor(
                        rs.getInt("autor_id"),
                        rs.getString("autor_nombre"),
                        rs.getString("autor_nacionalidad")
                    );
                    return new Libro(
                        rs.getInt("id"),
                        rs.getString("isbn"),
                        rs.getString("titulo"),
                        rs.getInt("anio_publicacion"),
                        rs.getInt("disponible") == 1,
                        autor
                    );
                }
            }
            return null;
        } catch (SQLException e) {
            throw new DatosException("Error al buscar libro por ID: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Libro> listarTodos() throws DatosException {
        List<Libro> lista = new ArrayList<>();
        String sql = """
            SELECT l.id, l.isbn, l.titulo, l.anio_publicacion, l.disponible,
                   a.id AS autor_id, a.nombre AS autor_nombre, a.nacionalidad AS autor_nacionalidad
            FROM libros l
            INNER JOIN autores a ON l.autor_id = a.id
            ORDER BY l.titulo ASC
            """;
        try (Connection conn = Conexion.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Autor autor = new Autor(
                    rs.getInt("autor_id"),
                    rs.getString("autor_nombre"),
                    rs.getString("autor_nacionalidad")
                );
                lista.add(new Libro(
                    rs.getInt("id"),
                    rs.getString("isbn"),
                    rs.getString("titulo"),
                    rs.getInt("anio_publicacion"),
                    rs.getInt("disponible") == 1,
                    autor
                ));
            }
            return lista;
        } catch (SQLException e) {
            throw new DatosException("Error al listar los libros: " + e.getMessage(), e);
        }
    }
}
