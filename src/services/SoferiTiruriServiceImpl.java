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
import repositories.SoferiTiruriHibernateRepository;
import repositories.SoferiTiruriRepository;

/**
 *
 * @author Stefan
 */
public class SoferiTiruriServiceImpl implements SoferiTiruriService{

    private final SoferiTiruriRepository soferiTiruriRepository = new SoferiTiruriHibernateRepository();
    
    @Override
    public boolean adaugaSoferTir(SoferiTiruri soferTir) {
        return soferiTiruriRepository.adaugaSoferTir(soferTir);
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
    
}
