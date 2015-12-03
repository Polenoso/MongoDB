
package GUI;

import Modelo.ETL.Imagen;
import Modelo.ETL.Metadata;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;

import com.mongodb.client.MongoDatabase;
import java.util.ArrayList;
import static java.util.Arrays.asList;
import javax.swing.JFrame;
import org.bson.Document;


public class Consultas  {
    
    Document query = new Document();

   public static FindIterable queryNombre(Document doc){
       Document bson = null;
	FindIterable cur = null;
      try{   
		
         // To connect to mongodb server
         MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
         
         // Now connect to your databases
         MongoDatabase db = mongoClient.getDatabase("test");
         System.out.println("Connect to database successfully");
         
         MongoCollection coll = db.getCollection("imagenes");
         System.out.println("Collection mycol selected successfully");

         cur =coll.find(doc);
         MongoCursor x = cur.iterator();
            while (x.hasNext()){
                 System.out.println(x.next());
            }
        	

			
      }catch(Exception e){
         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
      }
      
    return cur;  
      
   }
   
   public static FindIterable queryExtension(){
       Document doc = new Document();
	FindIterable cur = null;
      try{   
		
         // To connect to mongodb server
         MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
         
         // Now connect to your databases
         MongoDatabase db = mongoClient.getDatabase("test");
         System.out.println("Connect to database successfully");
         
         MongoCollection coll = db.getCollection("imagenes");
         System.out.println("Collection mycol selected successfully");
         AggregateIterable<Document> cursor;
         cursor =coll.aggregate(asList(new Document("$group","$ext")));
         MongoCursor x = cursor.iterator();
            while (x.hasNext()){
                 System.out.println(x.next().toString());
            }
        	

			
      }catch(Exception e){
         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
      }
      
    return cur;  
      
   }
}