/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModeloDAO;

import Modelo.ETL.Imagen;
import Modelo.ETL.Metadata;
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
public class BaseDatos {

    /**
     * @param args the command line arguments
     */
    
       
        ConexionBD bd = new ConexionBD();
        Connection con = bd.conectar();
        PreparedStatement pstmt = null;
        PreparedStatement pstmt2 = null;
        
        
        public int insertImage(String path, String name, String ext, int size){
            
            int id = 0;
            try {
                CallableStatement cs = con.prepareCall("{? = call insertImage(?,?,?,?)} ");
                cs.registerOutParameter(1, java.sql.Types.INTEGER);
                cs.setString(2, path);
                cs.setString(3, name);
                cs.setString(4, ext);
                cs.setInt(5, size);
                cs.execute();
                id = cs.getInt(1);
                
                
            } catch (SQLException ex) {
                Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
            }
            
          return id;  
        }
         
     
    public boolean insertMetadata(int id, String directory, String tag, String value){
            
            boolean done = false;
            try {
                CallableStatement cs = con.prepareCall("{ call insertMetadata(?,?,?,?)} ");
              
                cs.setInt(1, id);
                cs.setString(2, directory);
                cs.setString(3, tag);
                cs.setString(4, value);
                cs.execute();
                done = true;
                
                
            } catch (SQLException ex) {
                Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
            }
            
          return done;  
        }
    

  
    
    
    
}
