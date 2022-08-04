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
public class AppSingleTone {
    
    private static AppSingleTone singleInstance;
    
    private Utilizator utilizatorAutentificat;

    public AppSingleTone() {
    }
    
    public static AppSingleTone getAppSingleToneInstance(){
        if (singleInstance == null){
            singleInstance = new AppSingleTone();
        }
        return singleInstance;
    }

    public Utilizator getUtilizatorAutentificat() {
        return utilizatorAutentificat;
    }

    public void setUtilizatorAutentificat(Utilizator utilizatorAutentificat) {
        this.utilizatorAutentificat = utilizatorAutentificat;
    }
    
    
}
