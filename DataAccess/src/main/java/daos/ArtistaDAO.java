/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import models.Artista;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import connection.MongoConnection;
import java.util.ArrayList;
import java.util.List;
import models.Integrante;
import org.bson.Document;

/**
 *
 * @author martinez
 */
public class ArtistaDAO {
    private MongoClient mongoClient;
    
    public ArtistaDAO() {
        this.mongoClient = MongoClients.create(MongoConnection.getConnection());
    }
    
    public void insertar(Artista artista){
        Document artistaDoc = new Document("_id", artista.getId())
                .append("nombre", artista.getNombre())
                .append("tipo", artista.getTipo())
                .append("imagenPath", artista.getImagenPath())
                .append("genero", artista.getGenero())
                .append("integrantes", artista.getIntegrantes());
        
        MongoCollection<Document> collection = mongoClient.getDatabase(MongoConnection.getDatabase()).getCollection(MongoConnection.getArtistasCollection());
        collection.insertOne(artistaDoc);
    }
}