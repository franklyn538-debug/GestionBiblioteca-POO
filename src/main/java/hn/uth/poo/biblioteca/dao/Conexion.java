/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hn.uth.poo.biblioteca.dao;

/**
 *
 * @author frank
 */
import hn.uth.poo.biblioteca.excepciones.DatosException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Conexion {

    private static final String URL = "jdbc:sqlite:biblioteca.db";

    public static Connection obtenerConexion() throws DatosException {
        try {
            Connection conn = DriverManager.getConnection(URL);
            inicializarBaseDeDatos(conn);
            return conn;
        } catch (SQLException e) {
            throw new DatosException("Error al conectar con la base de datos SQLite: " + e.getMessage(), e);
        }
    }

    private static void inicializarBaseDeDatos(Connection conn) throws SQLException {
        String sqlAutores = """
            CREATE TABLE IF NOT EXISTS autores (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                nombre TEXT NOT NULL,
                nacionalidad TEXT NOT NULL
            );
            """;

        String sqlLibros = """
            CREATE TABLE IF NOT EXISTS libros (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                isbn TEXT NOT NULL UNIQUE,
                titulo TEXT NOT NULL,
                anio_publicacion INTEGER NOT NULL,
                disponible INTEGER NOT NULL DEFAULT 1,
                autor_id INTEGER NOT NULL,
                FOREIGN KEY (autor_id) REFERENCES autores(id)
            );
            """;

        try (Statement stmt = conn.createStatement()) {
            stmt.execute("PRAGMA foreign_keys = ON;");
            stmt.execute(sqlAutores);
            stmt.execute(sqlLibros);
        }
    }
}