/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hn.uth.poo.biblioteca.app;

/**
 *
 * @author frank
 */
import hn.uth.poo.biblioteca.modelo.Autor;
import hn.uth.poo.biblioteca.modelo.Libro;
import hn.uth.poo.biblioteca.servicio.BibliotecaServicio;
import java.util.List;

public class Menu {
    public static void main(String[] args) {
        System.out.println("=== INICIANDO PRUEBA DE SISTEMA DE BIBLIOTECA ===");
        BibliotecaServicio servicio = new BibliotecaServicio();

        try {
            // 1. Registrar un autor de prueba
            System.out.println("Registrando autor...");
            servicio.guardarAutor("Gabriel Garcia Marquez", "Colombiana");

            // 2. Listar autores y obtener el primero para asignarlo al libro
            List<Autor> autores = servicio.listarAutores();
            if (!autores.isEmpty()) {
                Autor autorPrincipal = autores.get(0);
                System.out.println("Autor encontrado: " + autorPrincipal.getNombre());

                // 3. Registrar un libro asociado a ese autor
                System.out.println("Registrando libro...");
                servicio.guardarLibro("979-0307474728", "Cien años de soledad", 1967, true, autorPrincipal);
            }

            // 4. Listar todos los libros registrados
            System.out.println("\n--- LISTADO DE LIBROS EN LA BASE DE DATOS ---");
            List<Libro> libros = servicio.listarLibros();
            for (Libro l : libros) {
                System.out.println("- " + l.getTitulo() + " (" + l.getAnioPublicacion() + ") | Autor: " + l.getAutor().getNombre());
            }

            System.out.println("\n Prueba ejecutada con exito!");

        } catch (Exception e) {
            System.err.println("Ocurrio un error en la ejecucion: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
