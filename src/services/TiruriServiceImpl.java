/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.util.ArrayList;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import models.Marca;
import models.Stare;
import models.Tir;
import observers.Fereastra;
import observers.VObserver;
import repositories.TiruriHibernateRepository;
import repositories.TiruriRepository;

/**
 *
 * @author Stefan
 */
public class TiruriServiceImpl implements TiruriService {
    
    private final TiruriRepository tiruriRepository = new TiruriHibernateRepository();
    private ArrayList<VObserver> observere = new ArrayList<>();

    public TiruriServiceImpl() {
        System.out.println("[DEBUG]"+getClass().getName()+" s-a creat o instanta");
    }
    
    
    
    
    @Override
    public boolean adaugaTir(Tir tir) {
        System.out.println("[DEBUG] "+getClass().getName()+" adaugaTir() "+tir);
        notifyObservers(tir);
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
    
    @Override
    public void addObserver(VObserver observer) {
        System.out.println("[DEBUG]"+getClass().getName()+" addObserver() observerClassName="+observer.getClass().getName());
        this.observere.add(observer);
        System.out.println("[DEBUG]"+getClass().getName()+" addObserver() observersSize="+observere.size());
        
    }
    
    @Override
    public void removeObserver(VObserver observer) {
        this.observere.remove(observer);
    }
    
    @Override
    public void notifyObservers(Object subject) {
        System.out.println("[DEBUG]"+getClass().getName()+" notifyObservers() inainte de for "+observere.size());
            
        for (VObserver observer : observere) {
            System.out.println("[DEBUG]"+getClass().getName()+" notifyObservers() "+subject.toString()+" observerClassName="+observer.getClass().getName());
            observer.update(subject);
        }
    }
}
