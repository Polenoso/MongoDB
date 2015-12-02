/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Principal.ETL;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonObject;

/**
 *
 * @author aitorpagan
 */
public class JsonMaker {

    /**
     * @param args the command line arguments
     */
    public static String getJSONobject(String[] args) {
        // TODO code application logic here
        FileWriter fw = null;
        JsonObject model = Json.createObjectBuilder()
                .add("_id", 1)
                .add("size", 599)
                .add("ext", "extension")
                .add("path", "ruta")
                .add("meta", Json.createArrayBuilder()
                    .add(Json.createObjectBuilder()
                        .add("directorio", "directorio")
                        .add("valor", Json.createArrayBuilder()
                            .add(Json.createObjectBuilder()
                                .add("etiq", "Valor")
                                .add("valor", "Value"))))).build();
        
        try {
            fw = new FileWriter("pruebajson.json");
            PrintWriter pw = new PrintWriter(fw);
            pw.println(model.toString());
        } catch (IOException ex) {
            Logger.getLogger(JsonMaker.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                fw.close();
            } catch (IOException ex) {
                Logger.getLogger(JsonMaker.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return model.toString();
        
    }
    
}

