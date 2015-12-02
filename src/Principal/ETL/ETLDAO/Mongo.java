/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Principal.ETL.ETLDAO;

import Modelo.ETL.Imagen;
import Modelo.ETL.Metadata;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;

import com.mongodb.client.MongoDatabase;
import java.util.ArrayList;
import org.bson.Document;


public class Mongo {

   public static void insertimage( Imagen img) {
       Document bson = null;
	
      try{   
		
         // To connect to mongodb server
         MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
         bson = img.toBson();
         // Now connect to your databases
         MongoDatabase db = mongoClient.getDatabase("test");
         System.out.println("Connect to database successfully");
         
         MongoCollection coll = db.getCollection("imagenes");
         System.out.println("Collection mycol selected successfully");
			
        
         Document i = new Document().append("_id", bson.get("_id"));
         if(!coll.find(i).iterator().hasNext()){
             coll.insertOne(bson);
         }else{
             coll.findOneAndReplace(i, bson);
         }
         
//	 FindIterable cursor = coll.find();
//         MongoCursor x = cursor.iterator();		

			
      }catch(Exception e){
         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
      }
      
      
      
   }
}