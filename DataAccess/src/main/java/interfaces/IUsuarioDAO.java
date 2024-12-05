/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import com.mongodb.client.MongoCollection;
import java.util.List;
import java.util.Map;
import models.Album;
import models.Usuario;
import org.bson.types.ObjectId;

/**
 *
 * @author martinez
 */
public interface IUsuarioDAO {
    boolean insertar(Usuario usuario);
    Usuario login(String correo);
    List<?> getFavoritos(String usuarioIdStr, String tipo);
    List<Map<String, Object>> getCancionesFavoritas(ObjectId usuarioId, MongoCollection<Album> albumsCollection);
    boolean agregarCancionAFavoritos(String usuarioIdStr, String tituloCancion, String albumIdStr);
    boolean agregarAFavoritos(String usuarioIdStr, String tipo, String favoritoIdStr);
    boolean eliminarDeFavoritos(String usuarioIdStr, String tipo, String favoritoIdStr);
    boolean eliminarCancionDeFavoritos(String usuarioIdStr, String titulo, String albumIdStr);
    boolean agregarBloqueo(String usuarioIdStr, String genero);
    List<String> getGenerosNoDeseados(String usuarioIdStr);
    boolean eliminarBloqueo(String usuarioIdStr, String genero);
    boolean actualizar(Usuario usuario);
}
