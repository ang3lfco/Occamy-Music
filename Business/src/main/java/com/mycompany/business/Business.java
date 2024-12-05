/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.business;

import daos.ArtistaDAO;
import daos.UsuarioDAO;
import dtos.ArtistaDTO;
import dtos.IntegranteDTO;
import dtos.UsuarioDTO;
import java.util.List;
import models.Artista;
import models.Integrante;
import org.bson.types.ObjectId;
import services.ArtistaService;
import services.UsuarioService;

/**
 *
 * @author martinez
 */
public class Business {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        // Crear instancia del DAO de Artista
        UsuarioService us = new UsuarioService();
        
        // ID de usuario de prueba (reemplaza con un ObjectId válido de tu base de datos)
        ObjectId usuarioId = new ObjectId("675010208d4c7a5729d1cf0a"); // Reemplaza con un valor válido

        // Llamada al método getFavoritos para artistas
        List<?> artistasFavoritos = us.getFavoritos(usuarioId.toHexString(), "artistas");
        System.out.println("Artistas favoritos: " + artistasFavoritos);

        // Llamada al método getFavoritos para álbumes
        List<?> albumesFavoritos = us.getFavoritos(usuarioId.toHexString(), "albumes");
        System.out.println("albumes favoritos: " + albumesFavoritos);

        // Llamada al método getFavoritos para canciones
        List<String> cancionesFavoritas = (List<String>) us.getFavoritos(usuarioId.toHexString(), "canciones");
        System.out.println("Canciones favoritas: " + cancionesFavoritas);
//        UsuarioDTO usuarioDTO = new UsuarioDTO("angel", "ang3l@example.com", "456", "");
//        us.agregarUsuario(usuarioDTO);
//
//        // Llamar al método getArtistas y obtener la lista de artistas
//        List<ArtistaDTO> artistasDTO = as.obtenerArtistas();
//        
//        // Imprimir los detalles de cada artista
//        for (ArtistaDTO artsDTO : artistasDTO) {
//            System.out.println("ID: " + artsDTO.getId());
//            System.out.println("Nombre: " + artsDTO.getNombre());
//            System.out.println("Tipo: " + artsDTO.getTipo());
//            System.out.println("Genero: " + artsDTO.getGenero());
//            System.out.println("Imagen Path: " + artsDTO.getImagenPath());
//            
//            // Mostrar la lista de integrantes
//            System.out.println("Integrantes:");
//            for (IntegranteDTO integranteDTO : artsDTO.getIntegrantes()) {
//                System.out.println("\tNombre: " + integranteDTO.getNombre());
//                System.out.println("\tRol: " + integranteDTO.getRol());
//            }
//            System.out.println("-------------------------");
        }
    }

