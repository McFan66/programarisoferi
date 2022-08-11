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
import repositories.UtilizatorHibernateRepository;
import repositories.UtilizatorRepository;
import repositories.UtilizatoriRoluriHibernateRepository;
import repositories.UtilizatoriRoluriRepository;

/**
 *
 * @author Stefan
 */
public class UtilizatoriServiceImpl implements UtilizatoriService{
    
    UtilizatorRepository utilizatorRepository = new UtilizatorHibernateRepository();
    UtilizatoriRoluriRepository utilizatoriRoluriRepository = new UtilizatoriRoluriHibernateRepository();

    @Override
    public boolean adaugaUtilizator(Utilizator utilizator) {
        return utilizatorRepository.adaugaUtilizator(utilizator);
    }

    @Override
    public void stergeUtilizator(Utilizator utilizator) {
        utilizatorRepository.stergeUtilizator(utilizator);
    }

    @Override
    public ArrayList<Utilizator> getAll() {
        return utilizatorRepository.getAll();
    }

    @Override
    public Utilizator login(String email, String parola) {
        return utilizatorRepository.login(email, parola);
    }

    @Override
    public ArrayList<Utilizator> getUtilizatoriByValid(boolean valid) {
        return utilizatorRepository.getUtilizatorByValid(valid);
    }

    @Override
    public Rol getRolulUtilizatorului(Utilizator utilizator) {
        for (UtilizatoriRoluri ur : utilizatoriRoluriRepository.getUtilizatoriRoluriByUtilizator(utilizator)){
            if (ur.getDataSfarsit()==null){
                return ur.getRol();
            }
        }
        return new Rol("Nedefinit");
    }
    
}
