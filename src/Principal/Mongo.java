/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Principal;

import com.mongodb.MongoClient;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;

import com.mongodb.client.MongoDatabase;

public class Mongo {

   public static void main( String args[] ) {
	
      try{   
		
         // To connect to mongodb server
         MongoClient mongoClient = new MongoClient( "192.168.183.33" , 27017 );
			
         // Now connect to your databases
         //DB db = mongoClient.getDB( "test" );
         MongoDatabase db = mongoClient.getDatabase("test");
         System.out.println("Connect to database successfully");
         
         String myUserName = "root";
         String myPass = "temporal";
         char[] myPassword = {'t','e','m','p','o','r','a','l'};
			
         //boolean auth = db.authenticate(myUserName, myPassword);
         //System.out.println("Authentication: "+auth);         
			
         DBCollection coll = (DBCollection) db.getCollection("imagenes");
         System.out.println("Collection mycol selected successfully");
			
         DBCursor cursor = coll.find();
         int i = 1;
			
         while (cursor.hasNext()) { 
            System.out.println("Inserted Document: "+i); 
            System.out.println(cursor.next()); 
            i++;
         }
			
      }catch(Exception e){
         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
      }
   }
}