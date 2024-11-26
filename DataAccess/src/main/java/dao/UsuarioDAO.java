/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import models.Usuario;
import org.bson.Document;

/**
 *
 * @author martinez
 */
public class UsuarioDAO {
    private final MongoCollection<Document> collection;

    public UsuarioDAO(MongoDatabase database){
        this.collection = database.getCollection("usuarios");
    }
    
    public void insertar(Usuario usuario){
        Document usuarioDoc = new Document("_id", usuario.getId())
                .append("nombre", usuario.getNombre())
                .append("apellido", usuario.getApellido())
                .append("correo", usuario.getCorreo())
                .append("contraseña", usuario.getPass())
                .append("imagenPath", usuario.getImagenPath());
        collection.insertOne(usuarioDoc);
    }
}