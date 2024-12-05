/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import dtos.UsuarioDTO;
import java.util.List;
import models.Usuario;

/**
 *
 * @author martinez
 */
public interface IUsuarioService {
    boolean agregarUsuario(UsuarioDTO usuarioDTO);
    boolean actualizarDatos(UsuarioDTO usuarioDTO);
    UsuarioDTO iniciarSesion(String correo, String pass);
    List<?> getFavoritos(String usuarioIdStr, String tipo);
    boolean agregarAFavoritos(String usuarioIdStr, String tipo, String favoritoIdStr);
    boolean eliminarDeFavoritos(String usuarioIdStr, String tipo, String favoritoIdStr);
    boolean eliminarCancionDeFavoritos(String usuarioIdStr, String titulo, String albumIdStr);
    boolean agregarCancionAFavoritos(String usuarioIdStr, String tituloCancion, String albumIdStr);
    boolean agregarNoDeseado(String usuarioIdStr, String genero);
    List<String> getGenerosNoDeseados(String usuarioIdStr);
    boolean eliminarBloqueo(String usuarioIdStr, String genero);
}
