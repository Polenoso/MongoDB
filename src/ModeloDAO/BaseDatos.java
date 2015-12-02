/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModeloDAO;

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
    
    public Imagen extract(){
        
            Imagen imagen = null;
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
                    System.out.println(imagen.toString());
                    
                    //System.out.println(id+" "+nombre+" "+ext+" "+tam+" "+ruta);
                }
            } catch (SQLException ex) {
                Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        
        return imagen;
    }
  
    
    
    
}
