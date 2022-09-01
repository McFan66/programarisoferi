/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.util.ArrayList;
import models.Sofer;
import models.SoferiTiruri;
import models.Tir;
import observers.VObserver;
import repositories.SoferiTiruriHibernateRepository;
import repositories.SoferiTiruriRepository;

/**
 *
 * @author Stefan
 */
public class SoferiTiruriServiceImpl implements SoferiTiruriService{

    private final SoferiTiruriRepository soferiTiruriRepository = new SoferiTiruriHibernateRepository();
    
    private ArrayList<VObserver> observere = new ArrayList<>();
    
    @Override
    public boolean adaugaSoferTir(SoferiTiruri soferTir) {
        soferiTiruriRepository.adaugaSoferTir(soferTir);
        notifyObservers(soferTir);
        return true;
    }

    @Override
    public void stergeSoferTir(SoferiTiruri soferTir) {
        soferiTiruriRepository.stergeSoferTir(soferTir);
    }

    @Override
    public ArrayList<SoferiTiruri> getAll() {
        return soferiTiruriRepository.getAll();
    }

    @Override
    public ArrayList<SoferiTiruri> getSoferiTiruriBySofer(Sofer sofer) {
        return soferiTiruriRepository.getSoferiTiruriBySofer(sofer);
    }

    @Override
    public ArrayList<SoferiTiruri> getSoferiTiruriByTir(Tir tir) {
        return soferiTiruriRepository.getSoferiTiruriByTir(tir);
    }

    @Override
    public ArrayList<SoferiTiruri> getSoferiTiruriByValid(boolean valid) {
        notifyObservers(valid);
        return soferiTiruriRepository.getSoferiTiruriByValid(valid);
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
        for (VObserver observer : observere) {
            observer.update(subject);
        }
    }

    @Override
    public ArrayList<SoferiTiruri> getSoferiTiruriByInCursa(boolean inCursa) {
        return soferiTiruriRepository.getSoferiTiruriByInCursa(inCursa);
    }

    @Override
    public SoferiTiruri getSoferiTiruriInCursaByTir(Tir tir) {
        return soferiTiruriRepository.getSoferiTiruriInCursaByTir(tir);
    }
    
}
