/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.dataaccess;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.io.File;
import java.io.IOException;
import java.util.List;
/**
 *
 * @author martinez
 */
public class DataAccess {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        // Ruta al archivo JSON
        String rutaArchivo = "artistas.json";

        try {
            // Leer el archivo JSON y convertirlo en una lista de documentos
            ObjectMapper mapper = new ObjectMapper();
            CollectionType listType = mapper.getTypeFactory().constructCollectionType(List.class, Document.class);
            List<Document> artistas = mapper.readValue(new File(rutaArchivo), listType);

            // Conexión a MongoDB
            var client = MongoClients.create("mongodb://localhost:27017");
            MongoDatabase database = client.getDatabase("occamymusic");
            MongoCollection<Document> coleccionArtistas = database.getCollection("artistas");

            // Insertar los documentos en la colección
            coleccionArtistas.insertMany(artistas);

            System.out.println("artistas insertados correctamente");
            client.close();
        } catch (IOException e) {
            System.err.println("Error al leer el archivo JSON: " + e.getMessage());
        }
    }
}
