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
import java.util.Arrays;
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
    
    public List<Artista> getArtistas() {
        List<Artista> artistas = new ArrayList<>();
        MongoCollection<Document> collection = mongoClient.getDatabase(MongoConnection.getDatabase()).getCollection(MongoConnection.getArtistasCollection());
        
        List<Document> pipeline = Arrays.asList(
            new Document("$project", new Document("_id", 1)
                                           .append("nombre", 1)
                                           .append("tipo", 1)
                                           .append("imagenPath", 1)
                                           .append("genero", 1)
                                           .append("integrantes", 1))
        );
        Iterable<Document> result = collection.aggregate(pipeline);

        for (Document doc : result) {
            Artista artista = new Artista();
            artista.setId(doc.getObjectId("_id"));
            artista.setNombre(doc.getString("nombre"));
            artista.setTipo(doc.getString("tipo"));
            artista.setImagenPath(doc.getString("imagenPath"));
            artista.setGenero(doc.getString("genero"));

            List<Integrante> integrantesList = new ArrayList<>();
            List<Document> integrantesDocs = (List<Document>) doc.get("integrantes");
            for (Document integranteDoc : integrantesDocs) {
                Integrante integrante = new Integrante();
                integrante.setNombre(integranteDoc.getString("nombre"));
                integrante.setRol(integranteDoc.getString("rol"));
                integrantesList.add(integrante);
            }
            artista.setIntegrantes(integrantesList);
            artistas.add(artista);
        }
        return artistas;
    }
}