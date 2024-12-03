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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import models.Artista;
import models.Integrante;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

/**
 *
 * @author martinez
 */
public class ArtistaDAO {
    private MongoClient mongoClient;
    private CodecRegistry pojoCodecRegistry;
    
    public ArtistaDAO(){
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
    
    public boolean insertar(Artista artista){
        try{
            MongoDatabase database = mongoClient.getDatabase("bibliotecaMusical7").withCodecRegistry(pojoCodecRegistry);
            MongoCollection<Artista> collection = database.getCollection("artistas", Artista.class);
            collection.insertOne(artista);
            mongoClient.close();
            return true;
        }
        catch(MongoException e){
            System.out.println("Error en Mongo al intentar insertar Artista: " + e.getMessage());
            return false;
        }
    }
    
    public List<Artista> getArtistas() {
        MongoDatabase database = mongoClient.getDatabase("bibliotecaMusical7").withCodecRegistry(pojoCodecRegistry);
        MongoCollection<Artista> collection = database.getCollection("artistas", Artista.class);

        List<Artista> artistas = new ArrayList<>();
        for (Artista artista : collection.find()) {
            if (artista.getIntegrantes() != null && !artista.getIntegrantes().isEmpty()) {
                List<Integrante> integrantes = new ArrayList<>();
                for (Integrante integrante : artista.getIntegrantes()) {
                    if (integrante.getFechaIngreso() != null && !(integrante.getFechaIngreso() instanceof Date)) {
                        Date fecha = new Date(integrante.getFechaIngreso().getTime());
                        integrante.setFechaIngreso(fecha);
                    }
                    integrantes.add(integrante);
                }
                artista.setIntegrantes(integrantes);
            }
            artistas.add(artista);
        }
        return artistas;
    }
    
    public void insercionMasiva() throws ParseException {
        
        MongoDatabase database = mongoClient.getDatabase("bibliotecaMusical7").withCodecRegistry(pojoCodecRegistry);
        MongoCollection<Artista> collection = database.getCollection("artistas", Artista.class);
        
        // Formato para parsear las fechas
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        List<Artista> artistas = Arrays.asList(
            new Artista("One Direction", "banda", "", "Pop", Arrays.asList(
                new Integrante("Harry", "Styles", "Vocalista", dateFormat.parse("2010-01-01"), false, dateFormat.parse("2015-01-01")),
                new Integrante("Niall", "Horan", "Vocalista", dateFormat.parse("2010-01-01"), false, dateFormat.parse("2015-01-01")),
                new Integrante("Liam", "Payne", "Vocalista", dateFormat.parse("2010-01-01"), false, dateFormat.parse("2015-01-01")),
                new Integrante("Louis", "Tomlinson", "Vocalista", dateFormat.parse("2010-01-01"), false, dateFormat.parse("2015-01-01")),
                new Integrante("Zayn", "Malik", "Vocalista", dateFormat.parse("2010-01-01"), false, dateFormat.parse("2015-03-25"))
            )),
            new Artista("BTS", "banda", "", "K-pop", Arrays.asList(
                new Integrante("Kim", "Namjoon", "Rapero principal", dateFormat.parse("2013-01-01"), true, null),
                new Integrante("Kim", "Seokjin", "Vocalista", dateFormat.parse("2013-01-01"), true, null),
                new Integrante("Min", "Yoongi", "Rapero", dateFormat.parse("2013-01-01"), true, null),
                new Integrante("Jung", "Hoseok", "Rapero y bailarín", dateFormat.parse("2013-01-01"), true, null),
                new Integrante("Park", "Jimin", "Vocalista", dateFormat.parse("2013-01-01"), true, null),
                new Integrante("Kim", "Taehyung", "Vocalista", dateFormat.parse("2013-01-01"), true, null),
                new Integrante("Jeon", "Jungkook", "Vocalista principal", dateFormat.parse("2013-01-01"), true, null)
            )),
            new Artista("BLACKPINK", "banda", "", "K-pop", Arrays.asList(
                new Integrante("Kim", "Jisoo", "Vocalista", dateFormat.parse("2016-01-01"), true, null),
                new Integrante("Jennie", "Kim", "Rapera y vocalista", dateFormat.parse("2016-01-01"), true, null),
                new Integrante("Roseanne", "Park", "Vocalista", dateFormat.parse("2016-01-01"), true, null),
                new Integrante("Lalisa", "Manoban", "Rapera y bailarina", dateFormat.parse("2016-01-01"), true, null)
            )),
            new Artista("Eminem", "solista", "", "Rap", Arrays.asList(
                new Integrante("Marshall", "Mathers", "Rapero", dateFormat.parse("1996-01-01"), true, null)
            )),
            new Artista("Santa Fe Klan", "solista", "", "Rap Mexicano", Arrays.asList(
                new Integrante("Ángel", "Quezada", "Rapero", dateFormat.parse("2013-01-01"), true, null)
            )),
            new Artista("TXT", "banda", "", "K-pop", Arrays.asList(
                new Integrante("Choi", "Yeonjun", "Vocalista y rapero", dateFormat.parse("2019-01-01"), true, null),
                new Integrante("Choi", "Soobin", "Líder y vocalista", dateFormat.parse("2019-01-01"), true, null),
                new Integrante("Choi", "Beomgyu", "Vocalista", dateFormat.parse("2019-01-01"), true, null),
                new Integrante("Kang", "Taehyun", "Vocalista", dateFormat.parse("2019-01-01"), true, null),
                new Integrante("Huening", "Kai", "Vocalista", dateFormat.parse("2019-01-01"), true, null)
            )),
            new Artista("Jay Park", "solista", "", "K-hiphop", Arrays.asList(
                new Integrante("Park", "Jaebeom", "Rapero y Vocalista", dateFormat.parse("2008-01-01"), true, null)
            )),
            new Artista("Lil Nas X", "solista", "", "Rap", Arrays.asList(
                new Integrante("Montero", "Hill", "Rapero y Cantante", dateFormat.parse("2019-01-01"), true, null)
            )),
            new Artista("Natanael Cano", "solista", "", "Regional Mexicano", Arrays.asList(
                new Integrante("Natanael", "Cano", "Vocalista", dateFormat.parse("2019-01-01"), true, null)
            )),
            new Artista("Adele", "solista", "", "Pop/Soul", Arrays.asList(
                new Integrante("Adele", "Adkins", "Vocalista", dateFormat.parse("2008-01-01"), true, null)
            ))
        );

        collection.insertMany(artistas);
        System.out.println("Inserción masiva completada.");
    }
}
