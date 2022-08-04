/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.util.ArrayList;
import models.UtilizatoriRoluri;
import repositories.UtilizatoriRoluriHibernateRepository;
import repositories.UtilizatoriRoluriRepository;

/**
 *
 * @author Stefan
 */
public class UtilizatoriRoluriServiceImpl implements UtilizatoriRoluriService{
    
    private UtilizatoriRoluriRepository utilizatoriRoluriRepository = new UtilizatoriRoluriHibernateRepository();

    @Override
    public boolean adaugaUtilizatoriRoluri(UtilizatoriRoluri utilizatoriRoluri) {
        return utilizatoriRoluriRepository.adaugaUtilizatoriRoluri(utilizatoriRoluri);
    }

    @Override
    public void stergeUtilizatoriRoluri(UtilizatoriRoluri utilizatoriRoluri) {
        utilizatoriRoluriRepository.stergeUtilizatoriRoluri(utilizatoriRoluri);
    }

    @Override
    public ArrayList<UtilizatoriRoluri> getAll() {
        return utilizatoriRoluriRepository.getAll();
    }
    
}
