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
public class InfoRoluriUtilizatori {
    private String numeRol;
    private int numarUtilizatori;

    public InfoRoluriUtilizatori() {
    }

    public InfoRoluriUtilizatori(String numeRol, int numarUtilizatori) {
        this.numeRol = numeRol;
        this.numarUtilizatori = numarUtilizatori;
    }

    public String getNumeRol() {
        return numeRol;
    }

    public void setNumeRol(String numeRol) {
        this.numeRol = numeRol;
    }

    public int getNumarUtilizatori() {
        return numarUtilizatori;
    }

    public void setNumarUtilizatori(int numarUtilizatori) {
        this.numarUtilizatori = numarUtilizatori;
    }
    
    
    
}
