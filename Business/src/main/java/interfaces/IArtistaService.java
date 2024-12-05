/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import dtos.ArtistaDTO;
import dtos.IntegranteDTO;
import java.text.ParseException;
import java.util.List;
import models.Integrante;

/**
 *
 * @author martinez
 */
public interface IArtistaService {
    void agregarArtista(String nombre, String tipo, String imagenPath, String genero, List<IntegranteDTO> integrantesDTO);
    void autoInsertarDatos() throws ParseException;
    List<Integrante> dtoToEntidad(List<IntegranteDTO> integranteDTO);
    List<IntegranteDTO> entidadToDTO(List<Integrante> integrantes);
    List<ArtistaDTO> obtenerArtistas();
}
