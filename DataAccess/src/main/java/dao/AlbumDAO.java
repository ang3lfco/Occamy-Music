/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import models.Album;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

/**
 *
 * @author martinez
 */
public class AlbumDAO {
    private final MongoCollection<Document> collection;
    
    public AlbumDAO(MongoDatabase database){
        this.collection = database.getCollection("albumes");
    }
    
    private void insertar(Album album){
        Document albumDoc = new Document("id", album.getId())
                .append("nombre", album.getNombre())
                .append("fechaLanzamiento", album.getFechaLanzamiento())
                .append("genero", album.getGenero())
                .append("portadaPath", album.getPortadaPath())
                .append("canciones", album.getCanciones());
        collection.insertOne(albumDoc);
    }
}