/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.util.Objects;

/**
 *
 * @author martinez
 */
public class Usuario {
    private String id;
    private String nombre;
    private String apellido;
    private String correo;
    private String pass;
    private String imagenPath;

    public Usuario() {
    }

    public Usuario(String nombre, String apellido, String correo, String pass, String imagenPath) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.pass = pass;
        this.imagenPath = imagenPath;
    }

    public Usuario(String id, String nombre, String apellido, String correo, String pass, String imagenPath) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.pass = pass;
        this.imagenPath = imagenPath;
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

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getImagenPath() {
        return imagenPath;
    }

    public void setImagenPath(String imagenPath) {
        this.imagenPath = imagenPath;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.id);
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
        final Usuario other = (Usuario) obj;
        return Objects.equals(this.id, other.id);
    }

    @Override
    public String toString() {
        return "Usuario{" + "id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", correo=" + correo + ", pass=" + pass + ", imagenPath=" + imagenPath + '}';
    }
}
