/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Principal;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

import org.bson.Document;
import com.mongodb.Block;
import com.mongodb.client.FindIterable;

/**
 *
 * @author Jesus Munoz
 */
public class PruebaMongo {

    /**
     * @param args the command line arguments
     */
    private MongoClient mongoClient = null;
    private MongoDatabase db = null;

     
    public PruebaMongo(String dbName) {
        this.mongoClient = new MongoClient();
        this.db = mongoClient.getDatabase(dbName);

    }

    public void listAllfromCollect(String collName) {

        FindIterable<org.bson.Document> iterable = this.db.getCollection(collName).find(); //Buscamos el documento con id: Asus

        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(final Document document) { //Necesitamos comprobar si para ese ID el campo esta vacío.
                System.out.println(document);
                //System.out.println(document.getString("name"));

            }
        });

    }
    
    public static void main(String[] args) { //Main de prueba
        
        PruebaMongo conexion = new PruebaMongo("imagesDB");
        
        conexion.listAllfromCollect("images");
        
    }
}