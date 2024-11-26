/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import daos.ArtistaDAO;
import dtos.IntegranteDTO;
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
    
    public List<Integrante> dtoToEntidad(List<IntegranteDTO> integranteDTO){
        List<Integrante> integrantes = new ArrayList<>();
        for (IntegranteDTO dto : integranteDTO) {
            Integrante integrante = new Integrante(dto.getNombre(), dto.getApellido(), dto.getRol(), dto.getFechaIngreso(), dto.isEstadoActivo(), dto.getFechaSalida());
            integrantes.add(integrante);
        }
        return integrantes;
    }
}
