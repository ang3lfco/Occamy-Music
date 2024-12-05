/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import daos.AlbumDAO;
import dtos.AlbumDTO;
import interfaces.IAlbumDAO;
import interfaces.IAlbumService;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import models.Album;

/**
 *
 * @author martinez
 */
public class AlbumService implements IAlbumService{
    private IAlbumDAO albumDAO;
    
    public AlbumService(){
        this.albumDAO = new AlbumDAO();
    }
    
    @Override
    public void agregarAlbum(String nombre, Date fechaLanzamiento, String genero, String portadaPath, List<String> canciones, String artista){
        Album album = new Album(nombre, fechaLanzamiento, genero, portadaPath, canciones, artista);
        albumDAO.insertar(album);
    }
    
    @Override
    public void autoInsertarDatos() throws ParseException{
        albumDAO.insercionMasiva();
    }
    
    @Override
    public List<AlbumDTO> obtenerAlbumes(){
        List<Album> albumes = albumDAO.getAlbumes();
        List<AlbumDTO> albumesDTO = new ArrayList<>();
        for(Album album : albumes){
            AlbumDTO albumDTO = new AlbumDTO(album.getId().toHexString(), album.getNombre(), album.getFechaLanzamiento(), album.getGenero(), album.getPortadaPath(), album.getCanciones(), album.getArtista());
            albumesDTO.add(albumDTO);
        }
        return albumesDTO;
    }
}
