/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.ETL;

import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author aitorpagan
 */
public class Directory {
    String name;
    ArrayList<Metadata> meta;

    public Directory(String name, ArrayList<Metadata> meta) {
        this.name = name;
        this.meta = meta;
    }

    public Directory(String direc) {
        this.name = direc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Metadata> getMeta() {
        return meta;
    }

    public void setMeta(ArrayList<Metadata> meta) {
        this.meta = meta;
    }


    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Directory other = (Directory) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }

        return true;
    }
    
    
}
