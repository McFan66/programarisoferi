/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.util.ArrayList;
import models.Marca;
import models.Stare;
import models.Tir;
import repositories.TiruriHibernateRepository;
import repositories.TiruriRepository;

/**
 *
 * @author Stefan
 */
public class TiruriServiceImpl implements TiruriService {

    private final TiruriRepository tiruriRepository = new TiruriHibernateRepository();
    
    @Override
    public boolean adaugaTir(Tir tir) {
        return tiruriRepository.adaugaTir(tir);
    }

    @Override
    public void stergeTir(Tir tir) {
        tiruriRepository.stergeTir(tir);
    }

    @Override
    public ArrayList<Tir> getAll() {
        return tiruriRepository.getAll();
    }

    @Override
    public ArrayList<Tir> getTirByMarca(Marca marca) {
        return tiruriRepository.getTirByMarca(marca);
    }

    @Override
    public ArrayList<Tir> getTirByStare(Stare stare) {
        return tiruriRepository.getTirByStare(stare);
    }

    @Override
    public ArrayList<Tir> getTirByNumarInmatriculare(String nrInmatriculare) {
        return tiruriRepository.getTirByNumarInmatriculare(nrInmatriculare);
    }

    @Override
    public ArrayList<Tir> getTirByValid(boolean valid) {
        return tiruriRepository.getTirByValid(valid);
    }
    
}
