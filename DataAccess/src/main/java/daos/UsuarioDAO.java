/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import connection.MongoConnection;
import interfaces.IUsuarioDAO;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import models.Album;
import models.Artista;
import models.Usuario;
import org.bson.Document;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.types.ObjectId;

/**
 *
 * @author martinez
 */
public class UsuarioDAO implements IUsuarioDAO{
    private MongoClient mongoClient;
    private CodecRegistry pojoCodecRegistry;
    
    public UsuarioDAO() {
        this.pojoCodecRegistry = fromRegistries(
                MongoClientSettings.getDefaultCodecRegistry(),
                fromProviders(PojoCodecProvider.builder().automatic(true).build())
        );
        
        this.mongoClient = MongoClients.create(
                MongoClientSettings.builder()
                .codecRegistry(pojoCodecRegistry)
                .applyConnectionString(new ConnectionString(MongoConnection.getConnection()))
                .build()
        );
    }
    
    @Override
    public boolean insertar(Usuario usuario) {
        try {
            MongoDatabase database = mongoClient.getDatabase("bibliotecaMusical7").withCodecRegistry(pojoCodecRegistry);
            MongoCollection<Document> collection = database.getCollection("usuarios");

            Document favoritos = new Document()
                    .append("artistas", new ArrayList<>())
                    .append("albumes", new ArrayList<>())
                    .append("canciones", new ArrayList<>());

            Document bloqueados = new Document()
                    .append("generos", new ArrayList<>());

            Document document = new Document()
                    .append("nombre", usuario.getNombre())
                    .append("correo", usuario.getCorreo())
                    .append("pass", usuario.getPass())
                    .append("imagenPath", usuario.getImagenPath())
                    .append("favoritos", favoritos) 
                    .append("bloqueados", bloqueados); 

            collection.insertOne(document);
            mongoClient.close();
            return true;
        } catch (MongoException e) {
            System.out.println("Error en Mongo al intentar insertar Usuario: " + e.getMessage());
            return false;
        }
    }
    
    @Override
    public Usuario login(String correo){
        try{
            MongoDatabase database = mongoClient.getDatabase("bibliotecaMusical7").withCodecRegistry(pojoCodecRegistry);
            MongoCollection<Usuario> collection = database.getCollection("usuarios", Usuario.class);
            Document filtro = new Document("correo", correo);
            Usuario usuario = collection.find(filtro).first();
            return usuario;
        }
        catch(MongoException e){
            System.out.println("Error al loguear: " + e.getMessage());
            return null;
        }
    }
    
    @Override
    public List<?> getFavoritos(String usuarioIdStr, String tipo){
        ObjectId usuarioId = new ObjectId(usuarioIdStr);
        
        MongoDatabase database = mongoClient.getDatabase("bibliotecaMusical7").withCodecRegistry(pojoCodecRegistry);
        MongoCollection<Document> usuariosCollection = database.getCollection("usuarios");
        MongoCollection<Artista> artistasCollection = database.getCollection("artistas", Artista.class);
        MongoCollection<Album> albumsCollection = database.getCollection("albumes", Album.class);

        Map<String, Class<?>> tipoColeccionMap = Map.of(
            "artistas", Artista.class,
            "albumes", Album.class
        );

        if(tipo.equals("canciones")){
            return getCancionesFavoritas(usuarioId, albumsCollection);
        } 
        else if(!tipoColeccionMap.containsKey(tipo)){
            throw new IllegalArgumentException("Tipo de favorito no válido: " + tipo);
        }

        MongoCollection<?> favoritosCollection = database.getCollection(tipo, tipoColeccionMap.get(tipo));

        List<Document> pipeline = List.of(
            new Document("$match", new Document("_id", usuarioId)),
            new Document("$project", new Document("favoritos." + tipo, 1))
        );
        
        Document resultado = usuariosCollection.aggregate(pipeline).first();

        if(resultado != null && resultado.containsKey("favoritos")){
            Document favoritos = resultado.get("favoritos", Document.class);
            List<ObjectId> favoritosIds = favoritos.getList(tipo, ObjectId.class);

            return favoritosCollection.find(Filters.in("_id", favoritosIds)).into(new ArrayList<>());
        }
        return Collections.emptyList();
    }
    
    @Override
    public List<Map<String, Object>> getCancionesFavoritas(ObjectId usuarioId, MongoCollection<Album> albumsCollection){
        MongoDatabase database = mongoClient.getDatabase("bibliotecaMusical7").withCodecRegistry(pojoCodecRegistry);
        MongoCollection<Document> usuariosCollection = database.getCollection("usuarios");

        List<Document> pipeline = List.of(
            new Document("$match", new Document("_id", usuarioId)),
            new Document("$project", new Document("favoritos.canciones", 1))
        );
        Document resultado = usuariosCollection.aggregate(pipeline).first();

        if(resultado != null && resultado.containsKey("favoritos")){
            Document favoritos = resultado.get("favoritos", Document.class);
            List<Document> cancionesFavoritas = favoritos.getList("canciones", Document.class);

            List<Map<String, Object>> cancionesConAlbum = new ArrayList<>();
            for(Document doc : cancionesFavoritas){
                String titulo = doc.getString("titulo");
                ObjectId albumId = doc.getObjectId("album");

                Album album = albumsCollection.find(Filters.eq("_id", albumId)).first();
                if(album != null){
                    Map<String, Object> cancionInfo = new HashMap<>();
                    cancionInfo.put("titulo", titulo);
                    cancionInfo.put("album", albumId);

                    cancionesConAlbum.add(cancionInfo);
                }
            }
            return cancionesConAlbum;
        }
        return Collections.emptyList();
    }
    
    @Override
    public boolean agregarCancionAFavoritos(String usuarioIdStr, String tituloCancion, String albumIdStr) {
        ObjectId usuarioId = new ObjectId(usuarioIdStr);
        ObjectId albumId = new ObjectId(albumIdStr);

        try{
            MongoDatabase database = mongoClient.getDatabase("bibliotecaMusical7").withCodecRegistry(pojoCodecRegistry);
            MongoCollection<Document> collection = database.getCollection("usuarios");

            Document cancion = new Document("titulo", tituloCancion)
                                    .append("album", albumId);

            Document filtro = new Document("_id", usuarioId);
            Document actualizacion = new Document("$addToSet", new Document("favoritos.canciones", cancion));

            collection.updateOne(filtro, actualizacion);
            return true;
        } 
        catch(MongoException e){
            System.out.println("Error al agregar cancion a favoritos: " + e.getMessage());
            return false;
        }
    }
    
    @Override
    public boolean agregarAFavoritos(String usuarioIdStr, String tipo, String favoritoIdStr){
        ObjectId usuarioId = new ObjectId(usuarioIdStr);
        ObjectId favoritoId = new ObjectId(favoritoIdStr);
        try{
            MongoDatabase database = mongoClient.getDatabase("bibliotecaMusical7").withCodecRegistry(pojoCodecRegistry);
            MongoCollection<Document> collection = database.getCollection("usuarios");

            if (!List.of("artistas", "albumes").contains(tipo)) {
                throw new IllegalArgumentException("Tipo de favorito no válido: " + tipo);
            }

            Document filtro = new Document("_id", usuarioId);
            Document actualizacion = new Document("$addToSet", new Document("favoritos." + tipo, favoritoId));

            collection.updateOne(filtro, actualizacion);
            return true;
        }
        catch(MongoException e){
            System.out.println("Error al agregar a favoritos: " + e.getMessage());
            return false;
        }
    }
    
    @Override
    public boolean eliminarDeFavoritos(String usuarioIdStr, String tipo, String favoritoIdStr){
        ObjectId usuarioId = new ObjectId(usuarioIdStr);
        ObjectId favoritoId = new ObjectId(favoritoIdStr);
        try{
            MongoDatabase database = mongoClient.getDatabase("bibliotecaMusical7").withCodecRegistry(pojoCodecRegistry);
            MongoCollection<Document> collection = database.getCollection("usuarios");

            if (!List.of("artistas", "albumes").contains(tipo)) {
                throw new IllegalArgumentException("Tipo de favorito no válido: " + tipo);
            }

            Document filtro = new Document("_id", usuarioId);
            Document actualizacion = new Document("$pull", new Document("favoritos." + tipo, favoritoId));

            collection.updateOne(filtro, actualizacion);
            return true;
        }
        catch(MongoException e){
            System.out.println("Error al eliminar de favoritos: " + e.getMessage());
            return false;
        }
    }
    
    @Override
    public boolean eliminarCancionDeFavoritos(String usuarioIdStr, String titulo, String albumIdStr){
        ObjectId usuarioId = new ObjectId(usuarioIdStr);
        ObjectId albumId = new ObjectId(albumIdStr);
        try{
            MongoDatabase database = mongoClient.getDatabase("bibliotecaMusical7").withCodecRegistry(pojoCodecRegistry);
            MongoCollection<Document> collection = database.getCollection("usuarios");

            Document filtro = new Document("_id", usuarioId);
            Document actualizacion = new Document("$pull", new Document("favoritos.canciones", new Document("titulo", titulo).append("album", albumId)));

            collection.updateOne(filtro, actualizacion);
            return true;
        } 
        catch(MongoException e){
            System.out.println("Error al eliminar la cancion de favoritos: " + e.getMessage());
            return false;
        }
    }
    
    @Override
    public boolean agregarBloqueo(String usuarioIdStr, String genero){
        ObjectId usuarioId = new ObjectId(usuarioIdStr);

        try {
            MongoDatabase database = mongoClient.getDatabase("bibliotecaMusical7").withCodecRegistry(pojoCodecRegistry);
            MongoCollection<Document> collection = database.getCollection("usuarios");

            Document filtro = new Document("_id", usuarioId);

            Document actualizacion = new Document("$addToSet", new Document("bloqueados.generos", genero));

            collection.updateOne(filtro, actualizacion);
            return true;
        } catch (MongoException e) {
            System.out.println("Error al agregar bloqueo: " + e.getMessage());
            return false;
        }
    }
    
    @Override
    public List<String> getGenerosNoDeseados(String usuarioIdStr){
        ObjectId usuarioId = new ObjectId(usuarioIdStr);
        MongoDatabase database = mongoClient.getDatabase("bibliotecaMusical7").withCodecRegistry(pojoCodecRegistry);
        MongoCollection<Document> usuariosCollection = database.getCollection("usuarios");

        Document filtro = new Document("_id", usuarioId);
        Document resultado = usuariosCollection.find(filtro).first();

        Document bloqueados = (Document) resultado.get("bloqueados");
        List<String> generosNoDeseados = (List<String>) bloqueados.get("generos");
  
        return generosNoDeseados;
    }
    
    public boolean eliminarBloqueo(String usuarioIdStr, String genero) {
        ObjectId usuarioId = new ObjectId(usuarioIdStr);
        try{
            MongoDatabase database = mongoClient.getDatabase("bibliotecaMusical7").withCodecRegistry(pojoCodecRegistry);
            MongoCollection<Document> collection = database.getCollection("usuarios");

            Document filtro = new Document("_id", usuarioId);

            Document actualizacion = new Document("$pull", new Document("bloqueados.generos", genero));

            collection.updateOne(filtro, actualizacion);

            return true;
        } 
        catch(MongoException e){
            System.out.println("Error al eliminar bloqueo: " + e.getMessage());
            return false;
        }
    }
}