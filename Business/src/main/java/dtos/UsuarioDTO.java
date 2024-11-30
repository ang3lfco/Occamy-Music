/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dtos;

import org.bson.types.ObjectId;

/**
 *
 * @author martinez
 */
public class UsuarioDTO {
    private ObjectId id;
    private String nombre;
    private String correo;
    private String pass;
    private String imagenPath;

    public UsuarioDTO() {
    }

    public UsuarioDTO(String nombre, String correo, String pass, String imagenPath) {
        this.nombre = nombre;
        this.correo = correo;
        this.pass = pass;
        this.imagenPath = imagenPath;
    }

    public UsuarioDTO(ObjectId id, String nombre, String correo, String pass, String imagenPath) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.pass = pass;
        this.imagenPath = imagenPath;
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
}
