/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dataaccess;

import daos.UsuarioDAO;
import java.util.ArrayList;
import java.util.List;
import models.Artista;
import models.Usuario;
import org.bson.types.ObjectId;

/**
 *
 * @author martinez
 */
public class testing {
    public static void main(String[] args) {
        
        UsuarioDAO udao = new UsuarioDAO();
//        udao.eliminarCancionDeFavoritos("675010208d4c7a5729d1cf0a", "What Makes You Beautiful", "674f84db00a7e77d3e5ec65a");
//        
//        List<String> lista =  udao.getGenerosNoDeseados("675010208d4c7a5729d1cf0a");
//        for(String generoBan : lista){
//            System.out.println(generoBan.toString());
//        }
       
        
        
//        // Crear instancia del DAO
//        UsuarioDAO udao = new UsuarioDAO();
//        
//        // ID de usuario de prueba (reemplaza con un ObjectId válido de tu base de datos)
//        ObjectId usuarioId = new ObjectId("675010208d4c7a5729d1cf0a"); // Reemplaza con un valor válido
//
//        // Llamada al método getFavoritos para artistas
//        List<?> artistasFavoritos = udao.getFavoritos(usuarioId.toHexString(), "artistas");
//        System.out.println("Artistas favoritos: " + artistasFavoritos);
//
//        // Llamada al método getFavoritos para álbumes
//        List<?> albumesFavoritos = udao.getFavoritos(usuarioId.toHexString(), "albumes");
//        System.out.println("albumes favoritos: " + albumesFavoritos);
//
//        // Llamada al método getFavoritos para canciones
//        List<String> cancionesFavoritas = (List<String>) udao.getFavoritos(usuarioId.toHexString(), "canciones");
//        System.out.println("Canciones favoritas: " + cancionesFavoritas);
    }
}
