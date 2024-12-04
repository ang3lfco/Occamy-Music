/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui.manager;

import dtos.UsuarioDTO;

/**
 *
 * @author martinez
 */
public class SessionManager {
    private static UsuarioDTO usuarioActual;
    
    public static UsuarioDTO getUsuarioActual(){
        return usuarioActual;
    }
    
    public static void setUsuarioActual(UsuarioDTO usuario){
        usuarioActual = usuario;
    }
    
    public static void cerrarSesion(){
        usuarioActual = null;
    }
    
    public static boolean isLogueado(){
        return usuarioActual != null;
    }
}
