/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import daos.UsuarioDAO;
import models.Usuario;

/**
 *
 * @author martinez
 */
public class UsuarioService {
    private final UsuarioDAO usuarioDAO;
    
    public UsuarioService() {
        this.usuarioDAO = new UsuarioDAO();
    }
    
    public void agregarUsuario(String nombre, String apellido, String correo, String pass, String imagenPath){
        Usuario usuario = new Usuario(nombre, apellido, correo, pass, imagenPath);
        usuarioDAO.insertar(usuario);
    }
}
