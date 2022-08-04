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
public class Utilizator {
    
    private int id;
    private String nume;
    private String prenume;
    private String email;
    private String parola;
    private boolean valid;
    private Set<UtilizatoriRoluri> utilizatoriRoluri = new HashSet<UtilizatoriRoluri>(0);

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

    public String getPrenume() {
        return prenume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getParola() {
        return parola;
    }

    public void setParola(String parola) {
        this.parola = parola;
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
    
    public String getNumeComplet(){
        return String.format("%s %s", nume, prenume);
    }
}
