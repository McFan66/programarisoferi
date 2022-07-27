/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.util.ArrayList;
import models.Stare;
import repositories.StareHibernateRepository;
import repositories.StareRepository;

/**
 *
 * @author Stefan
 */
public class StareServiceImpl implements StareService {

    private final StareRepository stareRepository = new StareHibernateRepository();
    
    @Override
    public boolean adaugaStare(Stare stare) {
        return stareRepository.adaugaStare(stare);
    }

    @Override
    public void stergeStare(Stare stare) {
        stareRepository.stergeStare(stare);
    }

    @Override
    public ArrayList<Stare> getAll() {
        return stareRepository.getAll();
    }

    @Override
    public Stare getStareByNume(String nume) {
        return stareRepository.getStareByNume(nume);
    }
    
}
