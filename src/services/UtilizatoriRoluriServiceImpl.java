/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.util.ArrayList;
import models.Rol;
import models.Utilizator;
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

    @Override
    public ArrayList<UtilizatoriRoluri> getUtilizatoriRoluriByValid(boolean valid) {
        return utilizatoriRoluriRepository.getUtilizatoriRoluriByValid(valid);
    }

    @Override
    public int getNrUtilizatoriCuRol(Rol rol) {
        return utilizatoriRoluriRepository.getUtilizatoriCuRol(rol);
    }

    @Override
    public ArrayList<UtilizatoriRoluri> getUtilizatoriRoluriByUtilizator(Utilizator utilizator) {
        return utilizatoriRoluriRepository.getUtilizatoriRoluriByUtilizator(utilizator);
    }

    @Override
    public ArrayList<UtilizatoriRoluri> getUtilizatoriRoluriActiveAndUpcomingByUtilizator(Utilizator utilizator) {
        return utilizatoriRoluriRepository.getUtilizatoriRoluriActiveAndUpcomingByUtilizator(utilizator);
    }

    
}
