/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dataaccess;

import daos.ArtistaDAO;
import java.util.List;
import models.Artista;

/**
 *
 * @author martinez
 */
public class PojoTest {
    public static void main(String[] args) {
//        Artista artista = new Artista("Angela Vazquez", "Solista", "", "Romantica", null);
        ArtistaDAO adao = new ArtistaDAO();
//        adao.insertar(artista);

          List<Artista> artistas = adao.getArtistas();
          for(Artista artista : artistas){
              System.out.println(artista.toString());
          }
          System.out.println("tamaño: "+artistas.size());
    }
}
