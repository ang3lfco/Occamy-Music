/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import daos.AlbumDAO;
import java.util.Date;
import java.util.List;
import models.Album;

/**
 *
 * @author martinez
 */
public class AlbumService {
    private final AlbumDAO albumDAO;
    
    public AlbumService(){
        this.albumDAO = new AlbumDAO();
    }
    
    public void agregarAlbum(String nombre, Date fechaLanzamiento, String genero, String portadaPath, List<String> canciones){
        Album album = new Album(nombre, fechaLanzamiento, genero, portadaPath, canciones);
        albumDAO.insertar(album);
    }
}