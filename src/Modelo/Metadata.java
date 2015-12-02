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
public class Metadata {
    private String directorio;
    private String etiqueta;
    private String valor;

    public Metadata(String directorio, String etiqueta, String valor) {
        this.directorio = directorio;
        this.etiqueta = etiqueta;
        this.valor = valor;
    }

    public String getDirectorio() {
        return directorio;
    }

    public void setDirectorio(String directorio) {
        this.directorio = directorio;
    }

    public String getEtiqueta() {
        return etiqueta;
    }

    public void setEtiqueta(String etiqueta) {
        this.etiqueta = etiqueta;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "{" + "directorio:" + directorio + ", etiqueta:" + etiqueta + ", valor:" + valor + '}';
    }
    
    

    
}
