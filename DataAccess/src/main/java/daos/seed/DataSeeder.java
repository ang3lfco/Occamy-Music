/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos.seed;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.io.File;
import java.io.IOException;
import java.util.List;
import org.bson.Document;

/**
 *
 * @author martinez
 */
public class DataSeeder {
    
    public void insercionMasiva(){
        System.out.println("Insercion Masiva de Datos inicializada.");
        String artistasPath = System.getProperty("user.dir") + "/../dataaccess/artistas.json";
        String albumesPath = System.getProperty("user.dir") + "/../dataaccess/albumes.json";

        try{
            ObjectMapper mapper = new ObjectMapper();
            CollectionType listType = mapper.getTypeFactory().constructCollectionType(List.class, Document.class);
            List<Document> artistas = mapper.readValue(new File(artistasPath), listType);
            
            var client = MongoClients.create("mongodb://localhost:27017");
            MongoDatabase database = client.getDatabase("occamymusic");
            MongoCollection<Document> artistasCollection = database.getCollection("artistas");
            artistasCollection.insertMany(artistas);
            
            try{
                List<Document> albumes = mapper.readValue(new File(albumesPath), listType);
                
                MongoCollection<Document> albumesCollection = database.getCollection("albumes");
                albumesCollection.insertMany(albumes);
            }
            catch(IOException e){
                System.err.println("Error al leer archivos JSON: " + e.getMessage());
            }
            client.close();
        }
        catch(IOException e){
            System.err.println("Error al leer archivos JSON: " + e.getMessage());
        }
        System.out.println("Insercion Masiva de Datos Finalizada.");
    }
}
