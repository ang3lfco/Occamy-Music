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
import interfaces.IAlbumDAO;
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
public class AlbumDAO implements IAlbumDAO{
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
    
    @Override
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
    
    @Override
    public boolean insercionMasiva() throws ParseException{
        try{
            MongoDatabase database = mongoClient.getDatabase("bibliotecaMusical7").withCodecRegistry(pojoCodecRegistry);
            MongoCollection<Album> collection = database.getCollection("albumes", Album.class);
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            List<Album> albumes = Arrays.asList(
                    new Album("Up All Night", dateFormat.parse("2011-11-18"), "Pop", "https://ang3lfco-next-ecommerce.s3.us-east-2.amazonaws.com/up-all-night.jpg", 
                              Arrays.asList("What Makes You Beautiful", "One Thing", "More Than This"), "One Direction"),
                    new Album("Take Me Home", dateFormat.parse("2012-11-09"), "Pop", "https://ang3lfco-next-ecommerce.s3.us-east-2.amazonaws.com/take-me-home.jpg", 
                              Arrays.asList("Live While We're Young", "Kiss You", "Little Things"), "One Direction"),
                    new Album("2 Cool 4 Skool", dateFormat.parse("2013-06-12"), "Hip Hop", "https://ang3lfco-next-ecommerce.s3.us-east-2.amazonaws.com/2-cool-4-skool.jpg", 
                              Arrays.asList("No More Dream", "We Are Bulletproof Pt.2", "I Like It"), "BTS"),
                    new Album("Dark & Wild", dateFormat.parse("2014-08-19"), "Hip Hop", "https://ang3lfco-next-ecommerce.s3.us-east-2.amazonaws.com/dark-and-wild.jpg", 
                              Arrays.asList("Danger", "War of Hormone", "Let Me Know"), "BTS"),
                    new Album("The Album", dateFormat.parse("2020-10-02"), "Pop, Hip Hop", "https://ang3lfco-next-ecommerce.s3.us-east-2.amazonaws.com/the-album.jpg", 
                              Arrays.asList("How You Like That", "Ice Cream", "Pretty Savage"), "BLACKPINK"),
                    new Album("Square Up", dateFormat.parse("2018-06-15"), "Pop, Hip Hop", "https://ang3lfco-next-ecommerce.s3.us-east-2.amazonaws.com/square-up.jpg", 
                              Arrays.asList("Ddu-Du Ddu-Du", "Forever Young", "Really"), "BLACKPINK"),
                    new Album("The Marshall Mathers LP", dateFormat.parse("2000-05-23"), "Hip-Hop", "https://ang3lfco-next-ecommerce.s3.us-east-2.amazonaws.com/the-marshall-mathers-lp.jpg", 
                              Arrays.asList("Stan", "The Way I Am", "Real Slim Shady"), "Eminem"),
                    new Album("The Eminem Show", dateFormat.parse("2002-05-26"), "Hip-Hop", "https://ang3lfco-next-ecommerce.s3.us-east-2.amazonaws.com/the-eminem-show.jpg", 
                              Arrays.asList("Without Me", "Cleaning Out My Closet", "Sing for the Moment"), "Eminem"),
                    // The Weeknd
                    new Album("Starboy", dateFormat.parse("2016-11-25"), "R&B/Pop", "https://ang3lfco-next-ecommerce.s3.us-east-2.amazonaws.com/starboy.jpg",
                              Arrays.asList("Starboy", "I Feel It Coming", "Party Monster"), "The Weeknd"),
                    new Album("After Hours", dateFormat.parse("2020-03-20"), "R&B/Pop", "https://ang3lfco-next-ecommerce.s3.us-east-2.amazonaws.com/after-hours.jpg",
                              Arrays.asList("Blinding Lights", "Save Your Tears", "In Your Eyes"), "The Weeknd"),

                    // Taylor Swift
                    new Album("1989", dateFormat.parse("2014-10-27"), "Pop/Country", "https://ang3lfco-next-ecommerce.s3.us-east-2.amazonaws.com/1989.jpg",
                              Arrays.asList("Shake It Off", "Blank Space", "Style"), "Taylor Swift"),
                    new Album("Reputation", dateFormat.parse("2017-11-10"), "Pop/Country", "https://ang3lfco-next-ecommerce.s3.us-east-2.amazonaws.com/reputation.jpg",
                              Arrays.asList("Look What You Made Me Do", "…Ready for It?", "Gorgeous"), "Taylor Swift"),

                    // Coldplay
                    new Album("A Rush of Blood to the Head", dateFormat.parse("2002-08-26"), "Rock/Pop", "https://ang3lfco-next-ecommerce.s3.us-east-2.amazonaws.com/a-rush-of-blood-to-the-head.jpg",
                              Arrays.asList("Clocks", "The Scientist", "In My Place"), "Coldplay"),
                    new Album("Viva la Vida or Death and All His Friends", dateFormat.parse("2008-06-12"), "Rock/Pop", "https://ang3lfco-next-ecommerce.s3.us-east-2.amazonaws.com/viva-la-vida.jpg",
                              Arrays.asList("Viva la Vida", "Lovers in Japan", "Violet Hill"), "Coldplay"),

                    // Shakira
                    new Album("Laundry Service", dateFormat.parse("2001-11-13"), "Pop/Latin", "https://ang3lfco-next-ecommerce.s3.us-east-2.amazonaws.com/laundry-service.jpg",
                              Arrays.asList("Whenever, Wherever", "Underneath Your Clothes", "Objection (Tango)"), "Shakira"),
                    new Album("Oral Fixation, Vol. 2", dateFormat.parse("2005-11-29"), "Pop/Latin", "https://ang3lfco-next-ecommerce.s3.us-east-2.amazonaws.com/oral-fixation.jpg",
                              Arrays.asList("Hips Don't Lie", "Illegal", "La Tortura"), "Shakira"),

                    // Imagine Dragons
                    new Album("Night Visions", dateFormat.parse("2012-09-04"), "Rock/Pop", "https://ang3lfco-next-ecommerce.s3.us-east-2.amazonaws.com/night-visions.jpg",
                              Arrays.asList("It's Time", "Radioactive", "Demons"), "Imagine Dragons"),
                    new Album("Smoke + Mirrors", dateFormat.parse("2015-02-17"), "Rock/Pop", "https://ang3lfco-next-ecommerce.s3.us-east-2.amazonaws.com/smoke-mirrors.jpg",
                              Arrays.asList("I Bet My Life", "Gold", "Shots"), "Imagine Dragons"),

                    // Dua Lipa
                    new Album("Dua Lipa", dateFormat.parse("2017-06-02"), "Pop", "https://ang3lfco-next-ecommerce.s3.us-east-2.amazonaws.com/dua-lipa.jpg",
                              Arrays.asList("Be the One", "IDGAF", "New Rules"), "Dua Lipa"),
                    new Album("Future Nostalgia", dateFormat.parse("2020-03-27"), "Pop", "https://ang3lfco-next-ecommerce.s3.us-east-2.amazonaws.com/future-nostalgia.jpg",
                              Arrays.asList("Don't Start Now", "Physical", "Break My Heart"), "Dua Lipa"),

                    // Drake
                    new Album("Take Care", dateFormat.parse("2011-11-15"), "Hip-hop/Rap", "https://ang3lfco-next-ecommerce.s3.us-east-2.amazonaws.com/take-care.jpg",
                              Arrays.asList("Marvins Room", "The Motto", "Make Me Proud"), "Drake"),
                    new Album("Nothing Was the Same", dateFormat.parse("2013-09-24"), "Hip-hop/Rap", "https://ang3lfco-next-ecommerce.s3.us-east-2.amazonaws.com/nothing-was-the-same.jpg",
                              Arrays.asList("Started From the Bottom", "Hold On, We're Going Home", "Worst Behavior"), "Drake"),

                    // Katy Perry
                    new Album("Teenage Dream", dateFormat.parse("2010-08-24"), "Pop", "https://ang3lfco-next-ecommerce.s3.us-east-2.amazonaws.com/teenage-dream.jpg",
                              Arrays.asList("California Gurls", "Teenage Dream", "Firework"), "Katy Perry"),
                    new Album("Prism", dateFormat.parse("2013-10-18"), "Pop", "https://ang3lfco-next-ecommerce.s3.us-east-2.amazonaws.com/prism.jpg",
                              Arrays.asList("Roar", "Dark Horse", "Unconditionally"), "Katy Perry"),

                    // Arctic Monkeys
                    new Album("AM", dateFormat.parse("2013-09-09"), "Rock", "https://ang3lfco-next-ecommerce.s3.us-east-2.amazonaws.com/am.jpg",
                              Arrays.asList("Do I Wanna Know?", "R U Mine?", "Why'd You Only Call Me When You're High?"), "Arctic Monkeys"),
                    new Album("Whatever People Say I Am, That's What I'm Not", dateFormat.parse("2006-01-23"), "Rock", "https://ang3lfco-next-ecommerce.s3.us-east-2.amazonaws.com/whatever-people-say.jpg",
                              Arrays.asList("I Bet You Look Good on the Dancefloor", "When the Sun Goes Down", "The View from the Afternoon"), "Arctic Monkeys"),

                    // J Balvin
                    new Album("Vibras", dateFormat.parse("2018-05-25"), "Reggaeton", "https://ang3lfco-next-ecommerce.s3.us-east-2.amazonaws.com/vibras.jpg",
                              Arrays.asList("Mi Gente", "Ahora Me Llama", "Machika"), "J Balvin"),
                    new Album("Colores", dateFormat.parse("2020-03-20"), "Reggaeton", "https://ang3lfco-next-ecommerce.s3.us-east-2.amazonaws.com/colores.jpg",
                              Arrays.asList("Blanco", "Rojo", "Morado"), "J Balvin")
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
    
    @Override
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