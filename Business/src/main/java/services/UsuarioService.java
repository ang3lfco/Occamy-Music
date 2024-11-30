/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import daos.UsuarioDAO;
import dtos.UsuarioDTO;
import javax.swing.JOptionPane;
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
    
    public boolean agregarUsuario(UsuarioDTO usuarioDTO){
        try{
            if(usuarioDTO.getNombre().isEmpty() || usuarioDTO.getCorreo().isEmpty() || usuarioDTO.getPass().isEmpty()){
                throw new IllegalArgumentException("Llene todos los campos.");
            }
            else if(!usuarioDTO.getNombre().matches("^[A-Za-z\\s]+$")){
                throw new IllegalArgumentException("El nombre solo puede contener letras {A-Za-z} y espacios.");
            }
            else{
                Usuario usuario = new Usuario(
                usuarioDTO.getNombre(), 
                usuarioDTO.getCorreo(), 
                usuarioDTO.getPass(), 
                usuarioDTO.getImagenPath());
                
                try{
                    return usuarioDAO.insertar(usuario);
                }
                catch(Exception e){
                    JOptionPane.showMessageDialog(null, "Error al insertar: " + e.getMessage(), "Error de Base de Datos", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
            }
        }
        catch(IllegalArgumentException e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
}
