/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import java.text.ParseException;
import java.util.List;
import models.Album;

/**
 *
 * @author martinez
 */
public interface IAlbumDAO {
    boolean insertar(Album album);
    boolean insercionMasiva() throws ParseException;
    List<Album> getAlbumes();
}
