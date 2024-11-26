/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.util.Date;
import java.util.Objects;

/**
 *
 * @author martinez
 */
public class Integrante {
    private String nombre;
    private String apellido;
    private String rol;
    private Date fechaIngreso;
    private boolean estadoActivo;
    private Date fechaSalida; 

    public Integrante() {
    }

    public Integrante(String nombre, String apellido, String rol, Date fechaIngreso, boolean estadoActivo, Date fechaSalida) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.rol = rol;
        this.fechaIngreso = fechaIngreso;
        this.estadoActivo = estadoActivo;
        this.fechaSalida = fechaSalida;
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

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public boolean isEstadoActivo() {
        return estadoActivo;
    }

    public void setEstadoActivo(boolean estadoActivo) {
        this.estadoActivo = estadoActivo;
    }

    public Date getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(Date fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 23 * hash + Objects.hashCode(this.nombre);
        hash = 23 * hash + Objects.hashCode(this.apellido);
        hash = 23 * hash + Objects.hashCode(this.fechaIngreso);
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
        final Integrante other = (Integrante) obj;
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        if (!Objects.equals(this.apellido, other.apellido)) {
            return false;
        }
        return Objects.equals(this.fechaIngreso, other.fechaIngreso);
    }

    @Override
    public String toString() {
        return "Integrante{" + "nombre=" + nombre + ", apellido=" + apellido + ", rol=" + rol + ", fechaIngreso=" + fechaIngreso + ", estadoActivo=" + estadoActivo + ", fechaSalida=" + fechaSalida + '}';
    }
}
