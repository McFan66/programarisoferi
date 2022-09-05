/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.util.ArrayList;
import models.Sofer;
import observers.VObserver;
import repositories.SoferiHibernateRepository;
import repositories.SoferiRepository;

/**
 *
 * @author Stefan
 */
public class SoferServiceImpl implements SoferService {

    private final SoferiRepository soferiRepository = new SoferiHibernateRepository();

    private ArrayList<VObserver> observere = new ArrayList<>();

    @Override
    public boolean salveazaSofer(Sofer sofer) {
        soferiRepository.adaugaSofer(sofer);
        notifyObservers(sofer);
        return true;
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

    @Override
    public void addObserver(VObserver observer) {
        this.observere.add(observer);
    }

    @Override
    public void removeObserver(VObserver observer) {
        this.observere.remove(observer);
    }

    @Override
    public void notifyObservers(Object subject) {
        for(VObserver obs : observere) {
             obs.update(subject);
             System.out.println("[DEBUG]" + subject.toString());
        }
    }

}
