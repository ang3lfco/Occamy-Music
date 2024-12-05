/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoException;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;
import connection.MongoConnection;
import interfaces.IArtistaDAO;
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
import org.bson.conversions.Bson;

/**
 *
 * @author martinez
 */
public class ArtistaDAO implements IArtistaDAO{
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
    
    @Override
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
    
    @Override
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
    
    @Override
    public void insercionMasiva() throws ParseException {
        
        MongoDatabase database = mongoClient.getDatabase("bibliotecaMusical7").withCodecRegistry(pojoCodecRegistry);
        MongoCollection<Artista> collection = database.getCollection("artistas", Artista.class);
        
        // Formato para parsear las fechas
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        List<Artista> artistas = Arrays.asList(
            new Artista("One Direction", "banda", "https://ang3lfco-next-ecommerce.s3.us-east-2.amazonaws.com/onedirection.jpg", "Pop", Arrays.asList(
                new Integrante("Harry", "Styles", "Vocalista", dateFormat.parse("2010-01-01"), false, dateFormat.parse("2015-01-01")),
                new Integrante("Niall", "Horan", "Vocalista", dateFormat.parse("2010-01-01"), false, dateFormat.parse("2015-01-01")),
                new Integrante("Liam", "Payne", "Vocalista", dateFormat.parse("2010-01-01"), false, dateFormat.parse("2015-01-01")),
                new Integrante("Louis", "Tomlinson", "Vocalista", dateFormat.parse("2010-01-01"), false, dateFormat.parse("2015-01-01")),
                new Integrante("Zayn", "Malik", "Vocalista", dateFormat.parse("2010-01-01"), false, dateFormat.parse("2015-03-25"))
            )),
            new Artista("BTS", "banda", "https://ang3lfco-next-ecommerce.s3.us-east-2.amazonaws.com/bts.jpg", "K-pop", Arrays.asList(
                new Integrante("Kim", "Namjoon", "Rapero principal", dateFormat.parse("2013-01-01"), true, null),
                new Integrante("Kim", "Seokjin", "Vocalista", dateFormat.parse("2013-01-01"), true, null),
                new Integrante("Min", "Yoongi", "Rapero", dateFormat.parse("2013-01-01"), true, null),
                new Integrante("Jung", "Hoseok", "Rapero y bailarín", dateFormat.parse("2013-01-01"), true, null),
                new Integrante("Park", "Jimin", "Vocalista", dateFormat.parse("2013-01-01"), true, null),
                new Integrante("Kim", "Taehyung", "Vocalista", dateFormat.parse("2013-01-01"), true, null),
                new Integrante("Jeon", "Jungkook", "Vocalista principal", dateFormat.parse("2013-01-01"), true, null)
            )),
            new Artista("BLACKPINK", "banda", "https://ang3lfco-next-ecommerce.s3.us-east-2.amazonaws.com/blackpink.jpg", "K-pop", Arrays.asList(
                new Integrante("Kim", "Jisoo", "Vocalista", dateFormat.parse("2016-01-01"), true, null),
                new Integrante("Jennie", "Kim", "Rapera y vocalista", dateFormat.parse("2016-01-01"), true, null),
                new Integrante("Roseanne", "Park", "Vocalista", dateFormat.parse("2016-01-01"), true, null),
                new Integrante("Lalisa", "Manoban", "Rapera y bailarina", dateFormat.parse("2016-01-01"), true, null)
            )),
            new Artista("Eminem", "solista", "https://ang3lfco-next-ecommerce.s3.us-east-2.amazonaws.com/eminem.jpg", "Rap", Arrays.asList(
                new Integrante("Marshall", "Mathers", "Rapero", dateFormat.parse("1996-01-01"), true, null)
            )),
            new Artista("Santa Fe Klan", "solista", "https://ang3lfco-next-ecommerce.s3.us-east-2.amazonaws.com/santafeklan.jpg", "Rap Mexicano", Arrays.asList(
                new Integrante("Ángel", "Quezada", "Rapero", dateFormat.parse("2013-01-01"), true, null)
            )),
            new Artista("TXT", "banda", "https://ang3lfco-next-ecommerce.s3.us-east-2.amazonaws.com/txt.png", "K-pop", Arrays.asList(
                new Integrante("Choi", "Yeonjun", "Vocalista y rapero", dateFormat.parse("2019-01-01"), true, null),
                new Integrante("Choi", "Soobin", "Líder y vocalista", dateFormat.parse("2019-01-01"), true, null),
                new Integrante("Choi", "Beomgyu", "Vocalista", dateFormat.parse("2019-01-01"), true, null),
                new Integrante("Kang", "Taehyun", "Vocalista", dateFormat.parse("2019-01-01"), true, null),
                new Integrante("Huening", "Kai", "Vocalista", dateFormat.parse("2019-01-01"), true, null)
            )),
            new Artista("Jay Park", "solista", "https://ang3lfco-next-ecommerce.s3.us-east-2.amazonaws.com/jaypark.jpg", "K-hiphop", Arrays.asList(
                new Integrante("Park", "Jaebeom", "Rapero y Vocalista", dateFormat.parse("2008-01-01"), true, null)
            )),
            new Artista("Lil Nas X", "solista", "https://ang3lfco-next-ecommerce.s3.us-east-2.amazonaws.com/lilnasx.jpg", "Rap", Arrays.asList(
                new Integrante("Montero", "Hill", "Rapero y Cantante", dateFormat.parse("2019-01-01"), true, null)
            )),
            new Artista("Natanael Cano", "solista", "https://ang3lfco-next-ecommerce.s3.us-east-2.amazonaws.com/natanaelcano.jpg", "Regional Mexicano", Arrays.asList(
                new Integrante("Natanael", "Cano", "Vocalista", dateFormat.parse("2019-01-01"), true, null)
            )),
            new Artista("Adele", "solista", "https://ang3lfco-next-ecommerce.s3.us-east-2.amazonaws.com/adele.jpg", "Pop/Soul", Arrays.asList(
                new Integrante("Adele", "Adkins", "Vocalista", dateFormat.parse("2008-01-01"), true, null)
            )),
            new Artista("The Weeknd", "solista", "https://ang3lfco-next-ecommerce.s3.us-east-2.amazonaws.com/theweeknd.jpg", "R&B/Pop", Arrays.asList(
                new Integrante("Abel", "Tesfaye", "Vocalista", dateFormat.parse("2010-01-01"), true, null)
            )),
            new Artista("Taylor Swift", "solista", "https://ang3lfco-next-ecommerce.s3.us-east-2.amazonaws.com/taylorswift.jpg", "Pop/Country", Arrays.asList(
                new Integrante("Taylor", "Swift", "Vocalista", dateFormat.parse("2006-01-01"), true, null)
            )),
            new Artista("Coldplay", "banda", "https://ang3lfco-next-ecommerce.s3.us-east-2.amazonaws.com/coldplay.jpg", "Rock/Pop", Arrays.asList(
                new Integrante("Chris", "Martin", "Vocalista y guitarrista", dateFormat.parse("1996-01-01"), true, null),
                new Integrante("Guy", "Berryman", "Bajista", dateFormat.parse("1996-01-01"), true, null),
                new Integrante("Jonny", "Buckland", "Guitarrista", dateFormat.parse("1996-01-01"), true, null),
                new Integrante("Will", "Champion", "Baterista", dateFormat.parse("1996-01-01"), true, null)
            )),
            new Artista("Shakira", "solista", "https://ang3lfco-next-ecommerce.s3.us-east-2.amazonaws.com/shakira.jpg", "Pop/Latin", Arrays.asList(
                new Integrante("Shakira", "Mebarak", "Vocalista y bailarina", dateFormat.parse("1990-01-01"), true, null)
            )),
            new Artista("Imagine Dragons", "banda", "https://ang3lfco-next-ecommerce.s3.us-east-2.amazonaws.com/imagine-dragons.jpg", "Rock/Pop", Arrays.asList(
                new Integrante("Dan", "Reynolds", "Vocalista", dateFormat.parse("2008-01-01"), true, null),
                new Integrante("Wayne", "Sermon", "Guitarrista", dateFormat.parse("2008-01-01"), true, null),
                new Integrante("Ben", "McKee", "Bajista", dateFormat.parse("2008-01-01"), true, null),
                new Integrante("Daniel", "Platt", "Baterista", dateFormat.parse("2008-01-01"), true, null)
            )),
            new Artista("Dua Lipa", "solista", "https://ang3lfco-next-ecommerce.s3.us-east-2.amazonaws.com/dualipa.jpg", "Pop", Arrays.asList(
                new Integrante("Dua", "Lipa", "Vocalista", dateFormat.parse("2015-01-01"), true, null)
            )),
            new Artista("Drake", "solista", "https://ang3lfco-next-ecommerce.s3.us-east-2.amazonaws.com/drake.jpg", "Hip-hop/Rap", Arrays.asList(
                new Integrante("Aubrey", "Graham", "Rapero y cantante", dateFormat.parse("2006-01-01"), true, null)
            )),
            new Artista("Katy Perry", "solista", "https://ang3lfco-next-ecommerce.s3.us-east-2.amazonaws.com/katyperry.jpg", "Pop", Arrays.asList(
                new Integrante("Katy", "Perry", "Vocalista", dateFormat.parse("2001-01-01"), true, null)
            )),
            new Artista("Arctic Monkeys", "banda", "https://ang3lfco-next-ecommerce.s3.us-east-2.amazonaws.com/arcticmonkeys.jpg", "Rock", Arrays.asList(
                new Integrante("Alex", "Turner", "Vocalista y guitarrista", dateFormat.parse("2002-01-01"), true, null),
                new Integrante("Jamie", "Cook", "Guitarrista", dateFormat.parse("2002-01-01"), true, null),
                new Integrante("Nick", "O'Malley", "Bajista", dateFormat.parse("2006-01-01"), true, null),
                new Integrante("Matt", "Helders", "Baterista", dateFormat.parse("2002-01-01"), true, null)
            )),
            new Artista("J Balvin", "solista", "https://ang3lfco-next-ecommerce.s3.us-east-2.amazonaws.com/jbalvin.jpg", "Reggaeton", Arrays.asList(
                new Integrante("José", "Alvarez", "Cantante", dateFormat.parse("2004-01-01"), true, null)
            ))
        );

        collection.insertMany(artistas);
        System.out.println("Inserción masiva de artistas completada.");
    }
    
    @Override
    public boolean verificarArtista(String nombre){
        try {
            MongoDatabase database = mongoClient.getDatabase("bibliotecaMusical7").withCodecRegistry(pojoCodecRegistry);
            MongoCollection<Artista> collection = database.getCollection("artistas", Artista.class);

            List<Bson> pipeline = Arrays.asList(
                Aggregates.match(Filters.eq("nombre", nombre)),
                Aggregates.limit(1)
            );
            AggregateIterable<Artista> resultado = collection.aggregate(pipeline);

            return resultado.iterator().hasNext();
        }
        catch(MongoException e){
            System.out.println("Error en Mongo al intentar verificar Artistas: " + e.getMessage());
            return false;
        }
    }
}
