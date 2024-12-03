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
import connection.MongoConnection;
import models.Usuario;
import org.bson.Document;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

/**
 *
 * @author martinez
 */
public class UsuarioDAO {
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
    
    public boolean insertar(Usuario usuario){
        try{
            MongoDatabase database = mongoClient.getDatabase("bibliotecaMusical7").withCodecRegistry(pojoCodecRegistry);
            MongoCollection<Usuario> collection = database.getCollection("usuarios", Usuario.class);
            collection.insertOne(usuario);
            mongoClient.close();
            return true;
        }
        catch(MongoException e){
            System.out.println("Error en Mongo al intentar insertar Usuario: " + e.getMessage());
            return false;
        }
    }
}