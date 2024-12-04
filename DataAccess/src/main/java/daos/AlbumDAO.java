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
import models.Album;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import connection.MongoConnection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import models.Artista;
import org.bson.Document;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

/**
 *
 * @author martinez
 */
public class AlbumDAO {
    private MongoClient mongoClient;
    private CodecRegistry pojoCodecRegistry;
    
    public AlbumDAO(){
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
    
    public boolean insertar(Album album){
        try{
            MongoDatabase database = mongoClient.getDatabase("bibliotecaMusical7").withCodecRegistry(pojoCodecRegistry);
            MongoCollection<Album> collection = database.getCollection("albumes", Album.class);
            collection.insertOne(album);
            mongoClient.close();
            System.out.println("Inserción masiva de albumes completada.");
            return true;
        }
        catch(MongoException e){
            System.out.println("Error en Mongo al intentar insertar Album: " + e.getMessage());
            return false;
        }
    }
    
    public boolean insercionMasiva() throws ParseException{
        try{
            MongoDatabase database = mongoClient.getDatabase("bibliotecaMusical7").withCodecRegistry(pojoCodecRegistry);
            MongoCollection<Album> collection = database.getCollection("albumes", Album.class);
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            List<Album> albumes = Arrays.asList(
            new Album("Up All Night", dateFormat.parse("2011-11-18"), "Pop", "", 
                      Arrays.asList("What Makes You Beautiful", "One Thing", "More Than This"), "One Direction"),
            new Album("Take Me Home", dateFormat.parse("2012-11-09"), "Pop", "", 
                      Arrays.asList("Live While We're Young", "Kiss You", "Little Things"), "One Direction"),
            new Album("2 Cool 4 Skool", dateFormat.parse("2013-06-12"), "Hip Hop", "", 
                      Arrays.asList("No More Dream", "We Are Bulletproof Pt.2", "I Like It"), "BTS"),
            new Album("Dark & Wild", dateFormat.parse("2014-08-19"), "Hip Hop", "", 
                      Arrays.asList("Danger", "War of Hormone", "Let Me Know"), "BTS"),
            new Album("The Album", dateFormat.parse("2020-10-02"), "Pop, Hip Hop", "", 
                      Arrays.asList("How You Like That", "Ice Cream", "Pretty Savage"), "BLACKPINK"),
            new Album("Square Up", dateFormat.parse("2018-06-15"), "Pop, Hip Hop", "", 
                      Arrays.asList("Ddu-Du Ddu-Du", "Forever Young", "Really"), "BLACKPINK"),
            new Album("The Marshall Mathers LP", dateFormat.parse("2000-05-23"), "Hip-Hop", "", 
                      Arrays.asList("Stan", "The Way I Am", "Real Slim Shady"), "Eminem"),
            new Album("The Eminem Show", dateFormat.parse("2002-05-26"), "Hip-Hop", "", 
                      Arrays.asList("Without Me", "Cleaning Out My Closet", "Sing for the Moment"), "Eminem")
        );
            
            ArtistaDAO adao = new ArtistaDAO();
            for(Album album : albumes){
                if(!adao.verificarArtista(album.getArtista())){
                    System.out.println("Algun artista no existe. ");
                    return false;
                }
            }
            
            collection.insertMany(albumes);
            mongoClient.close();
            return true;
        }
        catch(MongoException e){
            System.out.println("Error en Mongo al intentar insertar Albumes: " + e.getMessage());
            return false;
        }
    }
    
    public List<Album> getAlbumes(){
        MongoDatabase database = mongoClient.getDatabase("bibliotecaMusical7").withCodecRegistry(pojoCodecRegistry);
        MongoCollection<Album> collection = database.getCollection("albumes", Album.class);
        
        List<Album> albumes = new ArrayList<>();
        for(Album album : collection.find()){
            List<String> canciones = new ArrayList<>();
            for(String cancion : album.getCanciones()){
                canciones.add(cancion);
            }
            album.setCanciones(canciones);
            albumes.add(album);
        }
        return albumes;
    }
}