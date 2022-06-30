/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import gui.FrmAdministrare;

/**
 *
 * @author Stefan
 */
public class ApplicationController {
    
    FrmAdministrare frmAdministrare;
    
    public void actionCreate(){
        frmAdministrare = new FrmAdministrare();  
        frmAdministrare.setApplicationController(this);
        frmAdministrare.setVisible(true);
    }
    
    
    
}
