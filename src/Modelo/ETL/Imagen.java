/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.ETL;

import com.mongodb.util.JSON;
import java.util.ArrayList;
import javax.json.JsonObject;
import org.bson.Document;

/**
 *
 * @author alejandroruiz
 */
public class Imagen {
    private int _id;
    private String ruta;
    private String nombre;
    private String extension;
    private int tamanno;
    private ArrayList<Directory> dir;

    public Imagen(int id, String ruta, String nombre, String extension, int tamanno, ArrayList<Directory> metadatos) {
        this._id = id;
        this.ruta = ruta;
        this.nombre = nombre;
        this.extension = extension;
        this.tamanno = tamanno;
        this.dir = metadatos;
    }

    public int getId() {
        return _id;
    }

    public void setId(int id) {
        this._id = id;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public int getTamanno() {
        return tamanno;
    }

    public void setTamanno(int tamanno) {
        this.tamanno = tamanno;
    }

    public ArrayList<Directory> getDir() {
        return dir;
    }

    public void setMetadatos(ArrayList<Directory> metadatos) {
        this.dir = metadatos;
    }

    @Override
    public String toString() {
        return "{" + "_id: " + _id + ", ruta:" + ruta + ", nombre: " + nombre + ", extension:" + extension + ", tamanno:" + tamanno + ", directory:" + dir + '}';
    }

    public Document toBson(){
        Document bson = new Document();
        bson = BsonMaker.getBSONobject(this);
        return bson;
    }
    
    
}
