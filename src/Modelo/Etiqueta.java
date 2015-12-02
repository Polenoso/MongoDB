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
public class Etiqueta {
    
    private int id_etiquta;
    private String nombre;
    private int id_directorio;

    public Etiqueta(int id_etiquta, String nombre, int id_directorio) {
        this.id_etiquta = id_etiquta;
        this.nombre = nombre;
        this.id_directorio = id_directorio;
    }

    public int getId_etiquta() {
        return id_etiquta;
    }

    public void setId_etiquta(int id_etiquta) {
        this.id_etiquta = id_etiquta;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getId_directorio() {
        return id_directorio;
    }

    public void setId_directorio(int id_directorio) {
        this.id_directorio = id_directorio;
    }
    
    
}
