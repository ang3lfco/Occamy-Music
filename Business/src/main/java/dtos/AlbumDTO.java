/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dtos;

import java.util.Date;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author martinez
 */
public class AlbumDTO {
    private ObjectId id;
    private String nombre;
    private Date fechaLanzamiento;
    private String genero;
    private String portadaPath;
    private List<String> canciones;
    private String artista;

    public AlbumDTO() {
    }

    public AlbumDTO(String nombre, Date fechaLanzamiento, String genero, String portadaPath, List<String> canciones, String artista) {
        this.nombre = nombre;
        this.fechaLanzamiento = fechaLanzamiento;
        this.genero = genero;
        this.portadaPath = portadaPath;
        this.canciones = canciones;
        this.artista = artista;
    }

    public AlbumDTO(ObjectId id, String nombre, Date fechaLanzamiento, String genero, String portadaPath, List<String> canciones, String artista) {
        this.id = id;
        this.nombre = nombre;
        this.fechaLanzamiento = fechaLanzamiento;
        this.genero = genero;
        this.portadaPath = portadaPath;
        this.canciones = canciones;
        this.artista = artista;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFechaLanzamiento() {
        return fechaLanzamiento;
    }

    public void setFechaLanzamiento(Date fechaLanzamiento) {
        this.fechaLanzamiento = fechaLanzamiento;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getPortadaPath() {
        return portadaPath;
    }

    public void setPortadaPath(String portadaPath) {
        this.portadaPath = portadaPath;
    }

    public List<String> getCanciones() {
        return canciones;
    }

    public void setCanciones(List<String> canciones) {
        this.canciones = canciones;
    }

    public String getArtista() {
        return artista;
    }

    public void setArtista(String artista) {
        this.artista = artista;
    }
}
