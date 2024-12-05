/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import java.text.ParseException;
import java.util.List;
import models.Artista;

/**
 *
 * @author martinez
 */
public interface IArtistaDAO {
    boolean insertar(Artista artista);
    List<Artista> getArtistas();
    void insercionMasiva() throws ParseException;
    boolean verificarArtista(String nombre);
}
