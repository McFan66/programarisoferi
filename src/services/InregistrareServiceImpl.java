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
import repositories.InregistrariHibernateRepository;
import repositories.InregistrariRepository;

/**
 *
 * @author Stefan
 */
public class InregistrareServiceImpl implements InregistrariService  {
    
    private final InregistrariRepository inregistrariRepository = new InregistrariHibernateRepository();

    @Override
    public boolean adaugaInregistrare(Inregistrare inregistrare) {
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
    
}
