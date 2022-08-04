/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import gui.FrmAddInregistrare;
import javax.swing.JFrame;
import models.Inregistrare;
import models.SoferiTiruri;
import services.InregistrareServiceImpl;

/**
 *
 * @author Vlad
 */
public class InregistrariController {
    
    private FrmAddInregistrare frmAddInregistrare;
    private services.InregistrariService inregistrariService = new InregistrareServiceImpl();
    
    private Inregistrare inregistrareSelectata = null;
    
    public void actionEdit(JFrame parent) {
        
    }
    
    public void actionCreate(JFrame parent) {
        frmAddInregistrare = new FrmAddInregistrare(parent, true);
        frmAddInregistrare.setInregistrariController(this);
        frmAddInregistrare.setLocationRelativeTo(parent);
        frmAddInregistrare.setVisible(true);
    }
    
    public boolean isFormValid() {
        return true;
    }
    
    public void saveInregistrare() {
        if(isFormValid()) {
            Inregistrare i = new Inregistrare();
            
            i.setDataPlecare(frmAddInregistrare.getDtcPlecare().getDate());
            i.setDataSosire(frmAddInregistrare.getDtcSosire().getDate());
            i.setSoferTir((SoferiTiruri) frmAddInregistrare.getCmbSoferTir().getSelectedItem());
            
            inregistrariService.adaugaInregistrare(i);
        }
    }
    
    
}
