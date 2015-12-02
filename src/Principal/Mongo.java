/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Principal;

import com.mongodb.MongoClient;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;

import com.mongodb.client.MongoDatabase;

public class Mongo {

   public static void main( String args[] ) {
	
      try{   
		
         // To connect to mongodb server
         MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
			
         // Now connect to your databases
         //DB db = mongoClient.getDB( "test" );
         MongoDatabase db = mongoClient.getDatabase("test");
         System.out.println("Connect to database successfully");
         
         MongoCollection coll = db.getCollection("imagenes");
         System.out.println("Collection mycol selected successfully");
			
         FindIterable cursor = coll.find();
         MongoCursor x = cursor.iterator();
         int i = 1;
			
         while (x.hasNext()) { 
            System.out.println(x.next());
         }
			
      }catch(Exception e){
         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
      }
   }
}