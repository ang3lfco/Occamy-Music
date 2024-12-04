/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import com.mongodb.client.MongoCollection;
import daos.UsuarioDAO;
import dtos.UsuarioDTO;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import models.Album;
import models.Usuario;
import org.bson.types.ObjectId;
import org.mindrot.jbcrypt.BCrypt;

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
                    hashedPassword(usuarioDTO.getPass()),
                    usuarioDTO.getImagenPath()
                );
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
    
    public UsuarioDTO iniciarSesion(String correo, String pass){
        Usuario user = usuarioDAO.login(correo);
        if(user != null){
            if(checkPassword(pass, user.getPass())){
                return new UsuarioDTO(user.getId().toHexString(), user.getNombre(), user.getCorreo(), user.getPass(), user.getImagenPath());
            }
            else{
                JOptionPane.showMessageDialog(null, "Contraseña incorrecta.", "Error al iniciar sesión.", JOptionPane.ERROR_MESSAGE);
                return null;
            }
        }
        else{
            JOptionPane.showMessageDialog(null, "Error al iniciar sesión. ");
            return null;
        }
    }
    
    public static String hashedPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public static boolean checkPassword(String password, String hashed) {
        return BCrypt.checkpw(password, hashed);
    }
    
    public List<?> obtenerFavoritos(String usuarioIdStr, String tipo){
        return usuarioDAO.getFavoritos(usuarioIdStr, tipo);
    }
    
    public boolean agregarAFavoritos(String usuarioIdStr, String tipo, String favoritoIdStr){
        return usuarioDAO.agregarAFavoritos(usuarioIdStr, tipo, favoritoIdStr);
    }
    public boolean eliminarDeFavoritos(String usuarioIdStr, String tipo, String favoritoIdStr){
        return usuarioDAO.eliminarDeFavoritos(usuarioIdStr, tipo, favoritoIdStr);
    }
    
    public boolean agregarCancionAFavoritos(String usuarioIdStr, String tituloCancion, String albumIdStr){
        return usuarioDAO.agregarCancionAFavoritos(usuarioIdStr, tituloCancion, albumIdStr);
    }
    
    public boolean agregarNoDeseado(String usuarioIdStr, String genero){
        return usuarioDAO.agregarBloqueo(usuarioIdStr, genero);
    }
}
