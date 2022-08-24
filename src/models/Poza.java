/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author Stefan
 */
public class Poza {
    private int id;
    private int tipObiect;
    private int idObiect;
    private String imagePath;

    public Poza(int id, int tipObiect, int idObiect, String imagePath) {
        this.id = id;
        this.tipObiect = tipObiect;
        this.idObiect = idObiect;
        this.imagePath = imagePath;
    }
    
    public Poza(){
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTipObiect() {
        return tipObiect;
    }

    public void setTipObiect(int tipObiect) {
        this.tipObiect = tipObiect;
    }

    public int getIdObiect() {
        return idObiect;
    }

    public void setIdObiect(int idObiect) {
        this.idObiect = idObiect;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    @Override
    public String toString() {
        return "Poza{" + "id=" + id + ", tipObiect=" + tipObiect + ", idObiect=" + idObiect + ", imagePath=" + imagePath + '}';
    }
    
    
    
}
