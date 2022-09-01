/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Stefan
 */
public class Rol {
    
    private int id;
    private String nume;
    private boolean valid;
    private Set<UtilizatoriRoluri> utilizatoriRoluri = new HashSet<UtilizatoriRoluri>(0);

    public Rol() {
    }

    public Rol(String nume) {
        this.nume = nume;
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public Set<UtilizatoriRoluri> getUtilizatoriRoluri() {
        return utilizatoriRoluri;
    }

    public void setUtilizatoriRoluri(Set<UtilizatoriRoluri> utilizatoriRoluri) {
        this.utilizatoriRoluri = utilizatoriRoluri;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Rol other = (Rol) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nume;
    }
    
    
    
}
