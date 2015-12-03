/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Principal;


import ModeloDAO.BaseDatos;
import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.MimetypesFileTypeMap;
import oracle.net.aso.n;

/**
 *
 * @author alejandroruiz
 */
class Indexador {
    
    public static void listDirectory(File dir) throws IOException {
    if (dir.isDirectory()) {
        //Array de los ficheros del directorio
        File s[] = dir.listFiles();
        Metadata metadata = new Metadata();
        BaseDatos bd = new BaseDatos();
        //Recorre el array de ficheros
        for (int i=0; i < s.length; i++) {
                //Si es un fichero
                if(s[i].isFile()){
                 MimetypesFileTypeMap mtftp = new MimetypesFileTypeMap();
                 mtftp.addMimeTypes("image png tif jpg jpeg bmp");
                 String mimetype = mtftp.getContentType(s[i]);
                 String type = mimetype.split("/")[0];
                 //Si es el fichero es una imagen
                 if(type.equals("image")){
                    
                    //Extrae el nombre
                    String n = s[i].getName();
                    int punto = n.lastIndexOf(".");
                    String name = n.substring(0,punto);
                    
                    //Extrae la extension
                    String ext = n.substring(punto+1);
                    
                    //Extrae el path
                    String pathTotal = s[i].getCanonicalPath();
                    int barra = pathTotal.lastIndexOf("/");
                    String path = pathTotal.substring(0,barra);
                    
                    //Extrae el tamaño
                    int size = (int) s[i].length();
                    
                    //Introduce la imagen
                    int id = bd.insertImage(path, name, ext, size );
                   System.out.println(id);
                    
                     try {
                         //Extraer los metadatos
                         metadata =  ImageMetadataReader.readMetadata(s[i]);
                         //Recorre los directorios
                         for(Directory d: metadata.getDirectories()){
                             //Recorre las etiquetas
                             for (Tag tag: d.getTags()){
                                 
                                 //System.out.println(tag.getDirectoryName()+" "+ tag.getTagName()+" "+tag.getDescription());
                                 //inserta los metadatas
                                 if(tag.getDescription()!=null && tag.getDescription().length()<256){
                                    bd.insertMetadata(id, tag.getDirectoryName(), tag.getTagName(), tag.getDescription());
                                 }else if(tag.getDescription()==null){
                                         bd.insertMetadata(id, tag.getDirectoryName(), tag.getTagName(), tag.getDescription());
                                     }else{
                                         bd.insertMetadata(id, tag.getDirectoryName(), tag.getTagName(), "Too long");
                                 }
                             }
                         }
                     } catch (ImageProcessingException ex) {
                         Logger.getLogger(Indexador.class.getName()).log(Level.SEVERE, null, ex);
                     }
                 }
                }
                //Llama a la función de forma recurrente
            listDirectory(s[i]); 
                
        }
      }
    }
    
   public static void main (String argv[]) throws IOException {
      
        Properties pr = new Properties();
        FileInputStream fis = new FileInputStream("./configuracion.properties");
        pr.load(fis);
        
        listDirectory( new File(pr.getProperty("directorio")) ); 
     
    } 
}
