/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connection;

/**
 *
 * @author martinez
 */
public class MongoConnection {
    private static final String dbhost = "localhost";
    private static final int dbport = 27017;
    private static final String dbname = "occamymusic";
    
    private static final String usuariosCollection = "usuarios";
    private static final String artistasCollection = "artistas";
    private static final String albumesCollection = "albumes";
    
    public static String getConnection(){
        return String.format("mongodb://%s:%d", dbhost, dbport);
    }
    
    public static String getDatabase(){
        return dbname;
    }
    
    public static String getUsuariosCollection(){
        return usuariosCollection;
    }
    
    public static String getArtistasCollection(){
        return artistasCollection;
    }
    
    public static String getAlbumesCollection(){
        return albumesCollection;
    }
}