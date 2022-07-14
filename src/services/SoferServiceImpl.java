/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.util.ArrayList;
import models.Sofer;
import repositories.SoferiHibernateRepository;
import repositories.SoferiRepository;

/**
 *
 * @author Stefan
 */
public class SoferServiceImpl implements SoferService{

    private final SoferiRepository soferiRepository = new SoferiHibernateRepository();
    
    @Override
    public boolean salveazaSofer(Sofer sofer) {
        return soferiRepository.adaugaSofer(sofer);
    }

    @Override
    public void stergeSofer(Sofer sofer) {
        soferiRepository.stergeSofer(sofer);
    }

    @Override
    public ArrayList<Sofer> getAll() {
        return soferiRepository.getAll();
    }

    @Override
    public ArrayList<Sofer> getSoferByValid(boolean valid) {
        return soferiRepository.getSoferByValid(valid);
    }
    
}
