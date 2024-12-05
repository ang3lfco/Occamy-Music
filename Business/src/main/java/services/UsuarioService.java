/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import daos.UsuarioDAO;
import dtos.AlbumDTO;
import dtos.ArtistaDTO;
import dtos.IntegranteDTO;
import dtos.UsuarioDTO;
import interfaces.IUsuarioDAO;
import interfaces.IUsuarioService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.swing.JOptionPane;
import models.Album;
import models.Artista;
import models.Usuario;
import org.bson.types.ObjectId;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author martinez
 */
public class UsuarioService implements IUsuarioService{
    private IUsuarioDAO usuarioDAO;
    
    public UsuarioService() {
        this.usuarioDAO = new UsuarioDAO();
    }
    
    @Override
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
    
    @Override
    public boolean actualizarDatos(UsuarioDTO usuarioDTO){
        Usuario usuario = new Usuario();
        usuario.setId(new ObjectId(usuarioDTO.getId()));
        usuario.setNombre(usuarioDTO.getNombre());
        usuario.setCorreo(usuarioDTO.getCorreo());
        usuario.setPass(hashedPassword(usuarioDTO.getPass()));
        usuario.setImagenPath(usuarioDTO.getImagenPath());
        return usuarioDAO.actualizar(usuario);
    }
    
    @Override
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
    
    
    public static String hashedPassword(String password){
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    
    public static boolean checkPassword(String password, String hashed) {
        return BCrypt.checkpw(password, hashed);
    }
    
    @Override
    public List<?> getFavoritos(String usuarioIdStr, String tipo){
        if (tipo.equalsIgnoreCase("artistas")){
            List<Artista> artistas = (List<Artista>) usuarioDAO.getFavoritos(usuarioIdStr, tipo);
            return artistas.stream()
                    .map(artista -> new ArtistaDTO(
                            artista.getId().toHexString(),
                            artista.getNombre(),
                            artista.getTipo(),
                            artista.getImagenPath(),
                            artista.getGenero(),
                            
                            artista.getIntegrantes().stream()
                            .map(integrante -> new IntegranteDTO(
                                    integrante.getNombre(),
                                    integrante.getApellido(),
                                    integrante.getRol(),
                                    integrante.getFechaIngreso(),
                                    integrante.isEstadoActivo(),
                                    integrante.getFechaSalida()
                            ))
                            .collect(Collectors.toList())
                    ))
                    .collect(Collectors.toList());
        }
        else if(tipo.equalsIgnoreCase("albumes")){
            List<Album> albums = (List<Album>) usuarioDAO.getFavoritos(usuarioIdStr, tipo);
            return albums.stream()
                    .map(album -> new AlbumDTO(
                            album.getId().toHexString(),
                            album.getNombre(),
                            album.getFechaLanzamiento(),
                            album.getGenero(),
                            album.getPortadaPath(),
                            album.getCanciones(),
                            album.getArtista()
                    ))
                    .collect(Collectors.toList());
        }
        else if(tipo.equalsIgnoreCase("canciones")){
            List<Map<String, String>> canciones = (List<Map<String, String>>) usuarioDAO.getFavoritos(usuarioIdStr, tipo);
            return canciones.stream()
                    .map(cancion -> {
                        Map<String, String> cancionDTO = new HashMap<>();
                        cancionDTO.put("titulo", cancion.get("titulo")); 
                        Object albumId = cancion.get("album");
                        cancionDTO.put("albumId", ((ObjectId) albumId).toString());
                        return cancionDTO;
                    })
                    .collect(Collectors.toList());
        }
        throw new IllegalArgumentException("Tipo de favorito no soportado: " + tipo);
    }
    
    @Override
    public boolean agregarAFavoritos(String usuarioIdStr, String tipo, String favoritoIdStr){
        return usuarioDAO.agregarAFavoritos(usuarioIdStr, tipo, favoritoIdStr);
    }
    
    @Override
    public boolean eliminarDeFavoritos(String usuarioIdStr, String tipo, String favoritoIdStr){
        return usuarioDAO.eliminarDeFavoritos(usuarioIdStr, tipo, favoritoIdStr);
    }
    
    @Override
    public boolean eliminarCancionDeFavoritos(String usuarioIdStr, String titulo, String albumIdStr){
        return usuarioDAO.eliminarCancionDeFavoritos(usuarioIdStr, titulo, albumIdStr);
    }
    
    @Override
    public boolean agregarCancionAFavoritos(String usuarioIdStr, String tituloCancion, String albumIdStr){
        return usuarioDAO.agregarCancionAFavoritos(usuarioIdStr, tituloCancion, albumIdStr);
    }
    
    @Override
    public boolean agregarNoDeseado(String usuarioIdStr, String genero){
        return usuarioDAO.agregarBloqueo(usuarioIdStr, genero);
    }
    
    @Override
    public List<String> getGenerosNoDeseados(String usuarioIdStr){
        return usuarioDAO.getGenerosNoDeseados(usuarioIdStr);
    }
    
    @Override
    public boolean eliminarBloqueo(String usuarioIdStr, String genero){
        return usuarioDAO.eliminarBloqueo(usuarioIdStr, genero);
    }
}
