/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.util.List;
import java.util.Objects;
import org.bson.types.ObjectId;

/**
 *
 * @author martinez
 */
public class Artista {
    private ObjectId id;
    private String nombre;
    private String tipo;
    private String imagenPath;
    private String genero;
    private List<Integrante> integrantes;

    public Artista() {
    }

    public Artista(String nombre, String tipo, String imagenPath, String genero, List<Integrante> integrantes) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.imagenPath = imagenPath;
        this.genero = genero;
        this.integrantes = integrantes;
    }

    public Artista(ObjectId id, String nombre, String tipo, String imagenPath, String genero, List<Integrante> integrantes) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.imagenPath = imagenPath;
        this.genero = genero;
        this.integrantes = integrantes;
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

    public List<Integrante> getIntegrantes() {
        return integrantes;
    }

    public void setIntegrantes(List<Integrante> integrantes) {
        this.integrantes = integrantes;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Artista other = (Artista) obj;
        return Objects.equals(this.id, other.id);
    }

    @Override
    public String toString() {
        return "Artista{" + "id=" + id + ", nombre=" + nombre + ", tipo=" + tipo + ", imagenPath=" + imagenPath + ", genero=" + genero + ", integrantes=" + integrantes + '}';
    }
}
