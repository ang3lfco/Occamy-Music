/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dataaccess;

import daos.ArtistaDAO;
import daos.seed.DataSeeder;
import java.util.List;
import models.Artista;
import models.Integrante;

/**
 *
 * @author martinez
 */
public class Agg {
    public static void main(String[] args) {
        DataSeeder ds = new DataSeeder();
        ds.insercionMasiva();
//         // Crear instancia del DAO de Artista
//        ArtistaDAO artistaDAO = new ArtistaDAO();
//
//        // Llamar al método getArtistas y obtener la lista de artistas
//        List<Artista> artistas = artistaDAO.getArtistas();
//        
//        // Imprimir los detalles de cada artista
//        for (Artista artista : artistas) {
//            System.out.println("ID: " + artista.getId());
//            System.out.println("Nombre: " + artista.getNombre());
//            System.out.println("Tipo: " + artista.getTipo());
//            System.out.println("Genero: " + artista.getGenero());
//            System.out.println("Imagen Path: " + artista.getImagenPath());
//            
//            // Mostrar la lista de integrantes
//            System.out.println("Integrantes:");
//            for (Integrante integrante : artista.getIntegrantes()) {
//                System.out.println("\tNombre: " + integrante.getNombre());
//                System.out.println("\tRol: " + integrante.getRol());
//            }
//            System.out.println("-------------------------");
//        }
    }
}
