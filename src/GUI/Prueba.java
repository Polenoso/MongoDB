/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

/**
 *
 * @author alejandroruiz
 */
public class Prueba {
    public static void main(String[] args) {
        Consultas consulta = new Consultas();
        Vista v = new Vista();
        Controlador ctr = new Controlador(v,consulta);
        Consultas.queryExtension();
    }
    
}
