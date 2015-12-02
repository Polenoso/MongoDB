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
    private String valor;
    private int id_imagen;
    private int id_etiqueta;

    public Metadata(String valor, int id_imagen, int id_etiqueta) {
        this.valor = valor;
        this.id_imagen = id_imagen;
        this.id_etiqueta = id_etiqueta;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public int getId_imagen() {
        return id_imagen;
    }

    public void setId_imagen(int id_imagen) {
        this.id_imagen = id_imagen;
    }

    public int getId_etiqueta() {
        return id_etiqueta;
    }

    public void setId_etiqueta(int id_etiqueta) {
        this.id_etiqueta = id_etiqueta;
    }

    @Override
    public String toString() {
        return "Metadata{" + "valor=" + valor + ", id_imagen=" + id_imagen + ", id_etiqueta=" + id_etiqueta + '}';
    }
    
    
    
}
