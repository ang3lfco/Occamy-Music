/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import models.Artista;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

/**
 *
 * @author martinez
 */
public class ArtistaDAO {
    private final MongoCollection<Document> collection;

    public ArtistaDAO(MongoDatabase database){
        this.collection = database.getCollection("artistas");
    }
    
    public void insertar(Artista artista){
        Document artistaDoc = new Document("_id", artista.getId())
                .append("nombre", artista.getNombre())
                .append("tipo", artista.getTipo())
                .append("imagenPath", artista.getImagenPath())
                .append("genero", artista.getGenero())
                .append("integrantes", artista.getIntegrantes());
        collection.insertOne(artistaDoc);
    }
}