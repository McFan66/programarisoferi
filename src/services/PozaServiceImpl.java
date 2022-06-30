/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.util.ArrayList;
import models.Poza;
import repositories.PozaHibernateRepository;
import repositories.PozaRepository;

/**
 *
 * @author Stefan
 */
public class PozaServiceImpl implements PozaService{

    private final PozaRepository pozaRepository = new PozaHibernateRepository();
    
    @Override
    public boolean adaugaPoza(Poza poza) {
        return pozaRepository.adaugaPoza(poza);
    }

    @Override
    public void stergePoza(Poza poza) {
        pozaRepository.stergePoza(poza);
    }

    @Override
    public ArrayList<Poza> getAll() {
        return pozaRepository.getAll();
    }
    
    
    
}
