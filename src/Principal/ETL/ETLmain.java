/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Principal.ETL;

import Modelo.Imagen;
import Principal.ETL.ETLDAO.Mongo;
import Principal.ETL.ETLDAO.QueryETL;
import java.util.ArrayList;

/**
 *
 * @author aitorpagan
 */
public class ETLmain {

    /**
     *
     * @param args
     */
    public static void main(String[] args){
        QueryETL imagequery = new QueryETL();
        ArrayList<Imagen> listimagen = imagequery.extract();
        for (Imagen img : listimagen){
            Mongo.insertimage(img);
        }
        
    }
}
