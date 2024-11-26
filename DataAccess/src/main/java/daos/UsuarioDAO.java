/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import connection.MongoConnection;
import models.Usuario;
import org.bson.Document;

/**
 *
 * @author martinez
 */
public class UsuarioDAO {
    
    private MongoClient mongoClient;
    
    public UsuarioDAO() {
        this.mongoClient = MongoClients.create(MongoConnection.getConnection());
    }
    
    public void insertar(Usuario usuario){
        Document usuarioDoc = new Document("_id", usuario.getId())
                .append("nombre", usuario.getNombre())
                .append("apellido", usuario.getApellido())
                .append("correo", usuario.getCorreo())
                .append("contraseña", usuario.getPass())
                .append("imagenPath", usuario.getImagenPath());
        
        MongoCollection<Document> collection = mongoClient.getDatabase(MongoConnection.getDatabase()).getCollection(MongoConnection.getArtistasCollection());
        collection.insertOne(usuarioDoc);
    }
}