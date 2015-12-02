/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author alejandroruiz
 */
public class Imagen {
    private int id;
    private int ruta;
    private String nombre;
    private String extension;
    private int tamanno;

    public Imagen(int id, String nombre, String extension, int tamanno, int ruta) {
        this.id = id;
        this.ruta = ruta;
        this.nombre = nombre;
        this.extension = extension;
        this.tamanno = tamanno;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRuta() {
        return ruta;
    }

    public void setRuta(int ruta) {
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

    @Override
    public String toString() {
        return "Imagen{" + "id=" + id + ", ruta=" + ruta + ", nombre=" + nombre + ", extension=" + extension + ", tamanno=" + tamanno + '}';
    }
    
    
}
