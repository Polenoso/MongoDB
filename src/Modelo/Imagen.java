/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.util.ArrayList;

/**
 *
 * @author alejandroruiz
 */
public class Imagen {
    private int id;
    private String ruta;
    private String nombre;
    private String extension;
    private int tamanno;
    private ArrayList<Metadata> metadatos;

    public Imagen(int id, String ruta, String nombre, String extension, int tamanno, ArrayList<Metadata> metadatos) {
        this.id = id;
        this.ruta = ruta;
        this.nombre = nombre;
        this.extension = extension;
        this.tamanno = tamanno;
        this.metadatos = metadatos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public ArrayList<Metadata> getMetadatos() {
        return metadatos;
    }

    public void setMetadatos(ArrayList<Metadata> metadatos) {
        this.metadatos = metadatos;
    }

    @Override
    public String toString() {
        return "Imagen{" + "id=" + id + ", ruta=" + ruta + ", nombre=" + nombre + ", extension=" + extension + ", tamanno=" + tamanno + ", metadatos=" + metadatos + '}';
    }

    
    
    
}
