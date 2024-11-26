/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dtos;

import java.util.Date;

/**
 *
 * @author martinez
 */
public class IntegranteDTO {
    private String nombre;
    private String apellido;
    private String rol;
    private Date fechaIngreso;
    private boolean estadoActivo;
    private Date fechaSalida;

    public IntegranteDTO() {
    }
    
    public IntegranteDTO(String nombre, String apellido, String rol, Date fechaIngreso, boolean estadoActivo, Date fechaSalida) {
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
}
