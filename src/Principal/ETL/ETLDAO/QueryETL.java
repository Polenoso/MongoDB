/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Principal.ETL.ETLDAO;

import ModeloDAO.*;
import Modelo.Imagen;
import Modelo.Metadata;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alejandroruiz
 */
public class QueryETL {

    /**
     * @param args the command line arguments
     */
    
       
        ConexionBD bd = new ConexionBD();
        Connection con = bd.conectar();
        PreparedStatement pstmt = null;
        PreparedStatement pstmt2 = null;
        
       
    public ArrayList<Imagen> extract(){
        
            Imagen imagen = null;
            ArrayList<Imagen> listimagen = new ArrayList<>();
            try {
                
                pstmt = con.prepareStatement("SELECT * FROM IMAGEN, RUTA WHERE RUTA.ID = IMAGEN.RUTA");
                pstmt.clearParameters();
                ResultSet rs = pstmt.executeQuery();
                while(rs.next()){
                    
                    ArrayList<Metadata> lista_metadatos = new ArrayList<Metadata>();
                    
                    int id = rs.getInt("ID");
                    String nombre = rs.getString("NOMBRE");
                    String ext = rs.getString("ext");
                    int tam = rs.getInt("tam");
                    String ruta = rs.getString("path");
                    
                    System.out.println(id+" "+nombre+" "+ext+" "+tam+" "+ruta);
                    
                    pstmt2 = con.prepareStatement("SELECT * FROM DIRECTORIOS,ETIQUETA,METADATA WHERE DIRECTORIOS.id=ETIQUETA.dir AND ETIQUETA.id=METADATA.etq AND METADATA.img=?");
                    //pstmt2.clearParameters();
                    pstmt2.setInt(1, id);
                    ResultSet rs2 = pstmt2.executeQuery();
                    while(rs2.next()){
                        String direc = rs2.getString("nombreDir");
                        String etiq = rs2.getString("nombre");
                        String valor = rs2.getString("valor");
                        
                        
                        Metadata md = new Metadata(direc,etiq,valor);
                        
                        lista_metadatos.add(md);
                        //System.out.println(direc+" "+etiq+" "+valor);
                    }
                    
                    imagen = new Imagen(id,ruta,nombre,ext,tam,lista_metadatos);
                    listimagen.add(imagen);
                    
                    //System.out.println(id+" "+nombre+" "+ext+" "+tam+" "+ruta);
                }
            } catch (SQLException ex) {
                Logger.getLogger(QueryETL.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        
        return listimagen;
    }
  
    
    
    
}
