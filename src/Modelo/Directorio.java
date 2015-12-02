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
public class Directorio {
    
    private int id_directorio;
    private String nombre;

    public Directorio(int id_directorio, String nombre) {
        this.id_directorio = id_directorio;
        this.nombre = nombre;
    }

    public int getId_directorio() {
        return id_directorio;
    }

    public void setId_directorio(int id_directorio) {
        this.id_directorio = id_directorio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Directorio{" + "id_directorio=" + id_directorio + ", nombre=" + nombre + '}';
    }
    
    
    
}
