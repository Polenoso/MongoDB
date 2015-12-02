/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Principal.ETL.ETLDAO;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alejandroruiz
 */
public class ConectionSQL {
    
    private static Connection con = null;
    
    public Connection getCon(){
        return con;
    }
    
    public static Connection conectar(){
        
        if(con!=null){
            return con;
        }
        
        Properties pr = new Properties();
        try (FileInputStream conexion = new FileInputStream("./configuracion.properties")){
            pr.load(conexion);
        } catch (IOException ex) {
            Logger.getLogger(ConectionSQL.class.getName()).log(Level.SEVERE, null, ex);
        } 
        String cadena = "jdbc:oracle:"+pr.getProperty("tipoConexion")+":"+
                pr.getProperty("user")+"/"+pr.getProperty("pass")+"@"+
                pr.getProperty("host")+":"+pr.getProperty("port")+":"+
                pr.getProperty("SID");
        try{
            
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con=DriverManager.getConnection(cadena);
            
        }catch(ClassNotFoundException| SQLException ex ){
            Logger.getLogger(ConectionSQL.class.getName()).log(Level.SEVERE, null, ex);
        }finally{} 
        
        
        return con;   
    }
    
}

