/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultListModel;
import org.bson.Document;

/**
 *
 * @author alejandroruiz
 */
public class Controlador implements ActionListener {

    Vista vista;
    Consultas consultas;

    public Controlador(Vista v, Consultas c) {
        this.vista=v;
        this.consultas=c;
        v.setVisible(true);
        initEvents();
        //buscarExtensiones();
    }
    
    
   
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("Buscar")){
            String nombre = vista.nombre.getText();
            Document doc = new Document().append("name",nombre);
            FindIterable cur =  Consultas.queryNombre(doc);
            MongoCursor x = cur.iterator();
            DefaultListModel<String> listaNombre = new DefaultListModel<>();
            while (x.hasNext()){
               listaNombre.addElement(x.next().toString());
      
            }
            vista.ListaImagenes.setModel(listaNombre);
            
            
            
            
        }
    }

    private void initEvents() {
        vista.btnBuscar.setActionCommand("Buscar");
        vista.btnBuscar.addActionListener(this);
    }

    /*private void buscarExtensiones() {
        
        Document doc = new Document();
        FindIterable cursor = Consultas.query(doc);
        
    }*/
    
}
