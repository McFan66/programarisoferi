/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.util.ArrayList;
import java.util.Date;
import models.Inregistrare;
import models.SoferiTiruri;
import observers.VObserver;
import repositories.InregistrariHibernateRepository;
import repositories.InregistrariRepository;

/**
 *
 * @author Stefan
 */
public class InregistrareServiceImpl implements InregistrariService  {
    
    private final InregistrariRepository inregistrariRepository = new InregistrariHibernateRepository();
    
    private ArrayList<VObserver> observere = new ArrayList<>();

    @Override
    public boolean adaugaInregistrare(Inregistrare inregistrare) {
        notifyObservers(inregistrare);
        return inregistrariRepository.adaugaInregistrare(inregistrare);
    }

    @Override
    public void stergeInregistrare(Inregistrare inregistrare) {
        inregistrariRepository.stergeInregistrare(inregistrare);
    }

    @Override
    public ArrayList<Inregistrare> getAll() {
        return inregistrariRepository.getAll();
    }

    @Override
    public ArrayList<Inregistrare> getInregistrariBySoferiTiruri(SoferiTiruri soferTir) {
        return inregistrariRepository.getInregistrariBySoferiTiruri(soferTir);
    }

    @Override
    public ArrayList<Inregistrare> getInregistrariByDataSosire(Date dataSosire) {
        return inregistrariRepository.getInregistrariByDataSosire(dataSosire);
    }

    @Override
    public ArrayList<Inregistrare> getInregistrareByDataPlecare(Date dataPlecare) {
        return inregistrariRepository.getInregistrareByDataPlecare(dataPlecare);
    }

    @Override
    public ArrayList<Inregistrare> getInregistrareByNoPlecare() {
        return inregistrariRepository.getInregistrareByNoPlecare();
    }

    @Override
    public ArrayList<Inregistrare> getInregistrariFinalizate() {
        return inregistrariRepository.getInregistrariFinalizate();
    }

    @Override
    public ArrayList<Inregistrare> getInregistrariInDesfasurare() {
        return inregistrariRepository.getInregistrariInDesfasurare();
    }

    @Override
    public ArrayList<Inregistrare> getInregistrariByDates(Date dataPlecare, Date dataSosire) {
        return inregistrariRepository.getInregistrariByDates(dataPlecare, dataSosire);
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
    
}
