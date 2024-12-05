/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import dtos.AlbumDTO;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

/**
 *
 * @author martinez
 */
public interface IAlbumService {
    void agregarAlbum(String nombre, Date fechaLanzamiento, String genero, String portadaPath, List<String> canciones, String artista);
    void autoInsertarDatos() throws ParseException;
    List<AlbumDTO> obtenerAlbumes();
}
