
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
import java.util.List;
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
                  Document p = (Document) x.next();
                 System.out.println(p.get("path"));
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
         
         cur =coll.find();
         MongoCursor x = cur.iterator();
            while (x.hasNext()){
                Document p = (Document) x.next();
                 System.out.println(p.get("ext").toString());
            }
		
      }catch(Exception e){
         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
      }
      
    return cur;  
      
   }
   
    public static AggregateIterable queryDirectorios(){
       //Document doc = new Document().append("$unwind", "$meta").append("$group",new Document("_id","$meta.directory"));
       ArrayList<Document> doc = new ArrayList<>();
       Document uw = new Document("$unwind", "$meta");
       Document gr = new Document().append("$group",new Document("_id","$meta.directory"));
       doc.add(uw);
       doc.add(gr);
        AggregateIterable cur = null;
      try{   
		
         // To connect to mongodb server
         MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
         
         // Now connect to your databases
         MongoDatabase db = mongoClient.getDatabase("test");
         System.out.println("Connect to database successfully");
         
         MongoCollection coll = db.getCollection("imagenes");
         System.out.println("Collection mycol selected successfully");
         
         cur = coll.aggregate(doc);
         MongoCursor x = cur.iterator();
            while (x.hasNext()){
                Document p = (Document) x.next();
                 System.out.println(p.get("meta").toString());
            }
		
      }catch(Exception e){
         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
      }
      
    return cur;  
      
   }
    
    public static AggregateIterable queryTags(){
       
       ArrayList<Document> doc = new ArrayList<>();
       Document uw2 = new Document("$unwind","$meta.data");
       Document uw = new Document("$unwind", "$meta");
       
       Document gr = new Document().append("$group",new Document("_id","$meta.data.tag"));
       doc.add(uw);
       doc.add(uw2);
       doc.add(gr);
        AggregateIterable cur = null;
      try{   
		
         // To connect to mongodb server
         MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
         
         // Now connect to your databases
         MongoDatabase db = mongoClient.getDatabase("test");
         System.out.println("Connect to database successfully");
         
         MongoCollection coll = db.getCollection("imagenes");
         System.out.println("Collection mycol selected successfully");
         
         cur = coll.aggregate(doc);
         MongoCursor x = cur.iterator();
            while (x.hasNext()){
                Document p = (Document) x.next();
                 System.out.println(p.get("meta").toString());
            }
		
      }catch(Exception e){
         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
      }
      
    return cur;  
      
   }
}