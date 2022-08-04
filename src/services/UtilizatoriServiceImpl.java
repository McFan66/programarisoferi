/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.util.ArrayList;
import models.Utilizator;
import repositories.UtilizatorHibernateRepository;
import repositories.UtilizatorRepository;

/**
 *
 * @author Stefan
 */
public class UtilizatoriServiceImpl implements UtilizatoriService{
    
    UtilizatorRepository utilizatorRepository = new UtilizatorHibernateRepository();

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
    
}
