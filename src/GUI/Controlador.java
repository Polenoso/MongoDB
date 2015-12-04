/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.mongodb.client.AggregateIterable;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.TreeSet;
import javax.swing.DefaultListModel;
import org.bson.Document;

/**
 *
 * @author alejandroruiz
 */
public class Controlador implements ActionListener, MouseListener {

    Vista vista;
    Consultas consultas;

    public Controlador(Vista v, Consultas c) {
        this.vista=v;
        this.consultas=c;
        Extensiones();
        Directorios();
        Etiquetas();
        v.setVisible(true);
        initEvents(); 
        
    }
    
    
   
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("Buscar")){
            Document doc = new Document();
            
            String nombre = vista.nombre.getText();
            System.out.println(nombre);
            if(!nombre.equals("")){
                 doc.append("name",nombre);
            }
            String ext = vista.extension.getSelectedItem().toString();
            
            if(!ext.equals("-")){
                doc.append("ext",ext);
            }
            String directory = vista.directorios.getSelectedItem().toString();
            if(!directory.equals("-")){
                doc.append("meta.directory", directory);
            }
            
            for(int i = 0; i < vista.tagList.getRowCount();i++){
               
                String activetag = vista.tagList.getValueAt(i, 0).toString();
                doc.append("meta.data.tag", activetag);
            }
            ///String tag = vista.e.getSelectedItem().toString();
//            if(!tag.equals("-")){
//                doc.append("meta.data.tag", tag);
//            }
            
            FindIterable cur =  Consultas.queryNombre(doc);
            
            MongoCursor x = cur.iterator();
            DefaultListModel<String> listaNombre = new DefaultListModel<>();
            while (x.hasNext()){
                Document p = (Document) x.next();
               listaNombre.addElement(p.get("_id").toString()+" "+p.get("path").toString()+"/"+p.get("name").toString()+"."+p.get("ext").toString());
      
            }
            vista.ListaImagenes.setModel(listaNombre);
            vista.numImg.setText(Integer.toString(vista.ListaImagenes.getModel().getSize()));
            

   
        }if(e.getActionCommand().equals("Add")){
            Object[] row = {vista.etiquetas.getSelectedItem()};
            vista.TableModel.addRow(row);
        }if(e.getActionCommand().equals("Del")){
            int row = vista.tagList.getSelectedRow();
            vista.TableModel.removeRow(row);
        }
    }

    private void initEvents() {
        vista.btnBuscar.setActionCommand("Buscar");
        vista.btnBuscar.addActionListener(this);
        vista.btnAdd.setActionCommand("Add");
        vista.btnAdd.addActionListener(this);
        vista.btnDel.setActionCommand("Del");
        vista.btnDel.addActionListener(this);
        vista.ListaImagenes.addMouseListener(this);
       
        
    }

    private void Extensiones() {
        
        Document doc = new Document();
        FindIterable cursor = Consultas.queryExtension();
        TreeSet<String> conjunto = new TreeSet<>();
        
        MongoCursor x = cursor.iterator();
        
        while(x.hasNext()){
            int i=1;
            Document p = (Document)x.next();
            conjunto.add(p.get("ext").toString());
            i++;
        }
        
        for(String s : conjunto){
            vista.extension.addItem(s);
        }
    }
    
    private void Directorios() {
        
        Document doc = new Document();
        AggregateIterable cursor = Consultas.queryDirectorios();
       
        
        MongoCursor x = cursor.iterator();
        
        while(x.hasNext()){
            int i=1;
            Document p = (Document)x.next();
            vista.directorios.addItem(p.get("_id").toString());
            i++;
        }
        
   }
    
    private void Etiquetas() {
        
        Document doc = new Document();
        AggregateIterable cursor = Consultas.queryTags();
       
        
        MongoCursor x = cursor.iterator();
        
        while(x.hasNext()){
            int i=1;
            Document p = (Document)x.next();
            vista.etiquetas.addItem(p.get("_id").toString());
            i++;
        }
        
   }

    @Override
    public void mouseClicked(MouseEvent e) {
            int ind = vista.ListaImagenes.getSelectedValue().toString().indexOf(' ');
            ArrayList<String> Atag = new ArrayList<>();
            int id = Integer.parseInt(vista.ListaImagenes.getSelectedValue().toString().substring(0, ind));
            if(vista.tagList.getRowCount()>0){
                for(int i = 0; i<vista.tagList.getRowCount();i++){
                    Atag.add(vista.tagList.getValueAt(i, 0).toString());
                    System.out.println(Atag.toString());
                }
            }
            AggregateIterable cur2 = Consultas.queryMeta(id);
            
            MongoCursor x2 = cur2.iterator();
            DefaultListModel<String> listaMeta = new DefaultListModel<>();
            while(x2.hasNext()){
                Document p = (Document) x2.next();
                //listaMeta.addElement(p.get("meta").toString());
                Document w = (Document) p.get("meta");
                Document z = (Document) w.get("data");
                if(!Atag.isEmpty() && (Atag.contains(z.get("tag").toString()))){
                    listaMeta.addElement(w.get("directory").toString()+": "+z.get("tag").toString()+": "+z.get("value").toString());
                }else if(Atag.isEmpty()){
                    listaMeta.addElement(w.get("directory").toString()+": "+z.get("tag").toString()+": "+z.get("value").toString());
                }
                //listaMeta.addElement(p.get("meta.directory").toString()+": "+p.get("meta.data.tag").toString()+": "+p.get("meta.data.value").toString());
            }
            vista.MetaList.setModel(listaMeta);
    }

    @Override
    public void mousePressed(MouseEvent e) {
   }

    @Override
    public void mouseReleased(MouseEvent e) {
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }
}
