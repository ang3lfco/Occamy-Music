/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dtos;

import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author martinez
 */
public class ArtistaDTO {
    private String id;
    private String nombre;
    private String tipo;
    private String imagenPath;
    private String genero;
    private List<IntegranteDTO> integrantes;

    public ArtistaDTO() {
    }

    public ArtistaDTO(String nombre, String tipo, String imagenPath, String genero, List<IntegranteDTO> integrantes) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.imagenPath = imagenPath;
        this.genero = genero;
        this.integrantes = integrantes;
    }

    public ArtistaDTO(String id, String nombre, String tipo, String imagenPath, String genero, List<IntegranteDTO> integrantes) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.imagenPath = imagenPath;
        this.genero = genero;
        this.integrantes = integrantes;
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getImagenPath() {
        return imagenPath;
    }

    public void setImagenPath(String imagenPath) {
        this.imagenPath = imagenPath;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public List<IntegranteDTO> getIntegrantes() {
        return integrantes;
    }

    public void setIntegrantes(List<IntegranteDTO> integrantes) {
        this.integrantes = integrantes;
    }

    @Override
    public String toString() {
        return "ArtistaDTO{" + "id=" + id + ", nombre=" + nombre + ", tipo=" + tipo + ", imagenPath=" + imagenPath + ", genero=" + genero + ", integrantes=" + integrantes + '}';
    }
}
