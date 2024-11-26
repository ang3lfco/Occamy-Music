/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import models.Album;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import connection.MongoConnection;
import java.util.Date;
import java.util.List;
import org.bson.Document;

/**
 *
 * @author martinez
 */
public class AlbumDAO {
    private MongoClient mongoClient;
    
    public AlbumDAO(){
        this.mongoClient = MongoClients.create(MongoConnection.getConnection());
    }
    
    public void insertar(Album album){
        Document albumDoc = new Document("id", album.getId())
                .append("nombre", album.getNombre())
                .append("fechaLanzamiento", album.getFechaLanzamiento())
                .append("genero", album.getGenero())
                .append("portadaPath", album.getPortadaPath())
                .append("canciones", album.getCanciones());
        
        MongoCollection<Document> collection = mongoClient.getDatabase(MongoConnection.getDatabase()).getCollection(MongoConnection.getAlbumesCollection());
        collection.insertOne(albumDoc);
    }
}