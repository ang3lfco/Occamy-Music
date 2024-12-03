/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dataaccess;

import daos.ArtistaDAO;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import models.Artista;
import models.Integrante;

/**
 *
 * @author martinez
 */
public class PojoTest {
    public static void main(String[] args) throws ParseException {
        
        // Formato para parsear las fechas
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        // Crear la lista de integrantes con fechas como objetos Date, pasando null para fechaSalida
        List<Integrante> integrantes = new ArrayList<>();
        integrantes.add(new Integrante("Kendall", "Schmidt", "Vocalista", dateFormat.parse("2009-01-01"), true, null));
        integrantes.add(new Integrante("James", "Maslow", "Vocalista", dateFormat.parse("2009-01-01"), true, null));
        integrantes.add(new Integrante("Carlos", "Pena Jr.", "Vocalista", dateFormat.parse("2009-01-01"), true, null));
        integrantes.add(new Integrante("Logan", "Henderson", "Vocalista", dateFormat.parse("2009-01-01"), true, null));

        // Crear el objeto Artista
        Artista bigTimeRush = new Artista();
        bigTimeRush.setNombre("Big Time Rush");
        bigTimeRush.setTipo("banda");
        bigTimeRush.setImagenPath("");
        bigTimeRush.setGenero("Pop");
        bigTimeRush.setIntegrantes(integrantes);
        
        ArtistaDAO adao = new ArtistaDAO();
        adao.insertar(bigTimeRush);
        
        
////        Artista artista = new Artista("Angela Vazquez", "Solista", "", "Romantica", null);
//        ArtistaDAO adao = new ArtistaDAO();
////        adao.insertar(artista);
//
//          List<Artista> artistas = adao.getArtistas();
//          for(Artista artista : artistas){
//              System.out.println(artista.toString());
//          }
//          System.out.println("tamaño: "+artistas.size());
    
    }
}
