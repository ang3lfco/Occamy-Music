/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import daos.ArtistaDAO;
import dtos.ArtistaDTO;
import dtos.IntegranteDTO;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import models.Artista;
import models.Integrante;

/**
 *
 * @author martinez
 */
public class ArtistaService {
    private final ArtistaDAO artistaDAO;
    
    public ArtistaService() {
        this.artistaDAO = new ArtistaDAO();
    }
    
    public void agregarArtista(String nombre, String tipo, String imagenPath, String genero, List<IntegranteDTO> integrantesDTO){
        List<Integrante> integrantes = dtoToEntidad(integrantesDTO);
        Artista artista = new Artista(nombre, tipo, imagenPath, genero, integrantes);
        artistaDAO.insertar(artista);
    }
    
    public void autoInsertarDatos() throws ParseException{
        artistaDAO.insercionMasiva();
    }
    
    public List<Integrante> dtoToEntidad(List<IntegranteDTO> integranteDTO){
        List<Integrante> integrantes = new ArrayList<>();
        for (IntegranteDTO dto : integranteDTO) {
            Integrante integrante = new Integrante(dto.getNombre(), dto.getApellido(), dto.getRol(), dto.getFechaIngreso(), dto.isEstadoActivo(), dto.getFechaSalida());
            integrantes.add(integrante);
        }
        return integrantes;
    }
    
    public List<IntegranteDTO> entidadToDTO(List<Integrante> integrantes){
        List<IntegranteDTO> integrantesDTO = new ArrayList<>();
        for(Integrante integrante : integrantes){
            IntegranteDTO dto = new IntegranteDTO(integrante.getNombre(), integrante.getApellido(), integrante.getRol(), integrante.getFechaIngreso(), integrante.isEstadoActivo(), integrante.getFechaSalida());
            integrantesDTO.add(dto);
        }
        return integrantesDTO;
    }
    
    public List<ArtistaDTO> obtenerArtistas(){
        List<Artista> artistas = artistaDAO.getArtistas();
        List<ArtistaDTO> artistasDTO = new ArrayList<>();
        for(Artista artista : artistas){
            ArtistaDTO artistaDTO = new ArtistaDTO(artista.getId().toHexString(), artista.getNombre(), artista.getTipo(), artista.getImagenPath(), artista.getGenero(), entidadToDTO(artista.getIntegrantes()));
            artistasDTO.add(artistaDTO);
        }
        return artistasDTO;
    }
}
