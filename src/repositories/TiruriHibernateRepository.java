/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repositories;

import java.util.ArrayList;
import models.Marca;
import models.Stare;
import models.Tir;
import org.hibernate.Query;
import org.hibernate.Session;
import utils.HibernateUtil;

/**
 *
 * @author Stefan
 */
public class TiruriHibernateRepository implements TiruriRepository {

    Session session = null;

    public TiruriHibernateRepository() {
        this.session = HibernateUtil.getSessionFactory().openSession();
    }
    
    
    
    @Override
    public boolean adaugaTir(Tir tir) {
        org.hibernate.Transaction tx = session.beginTransaction();
       
        if (tir!=null && tir.getId()>0){
            session.saveOrUpdate(tir);
            tx.commit();
            return true;
        }
        int id = (int) session.save(tir);
        tir.setId(id);
        if (id > 0) {
            tx.commit();
        } else {
            tx.rollback();
        }
        return id > 0; 
    }

    @Override
    public void stergeTir(Tir tir) {
        org.hibernate.Transaction tx = session.beginTransaction();
        session.delete(tir);
        tx.commit();
    }

    @Override
    public ArrayList<Tir> getAll() {
        ArrayList<Tir> listaTiruri = new ArrayList<>();
        org.hibernate.Transaction tx = session.beginTransaction();
        Query q = session.createQuery("from Tir");
        listaTiruri = (ArrayList<Tir>) q.list();
        tx.commit();
        return listaTiruri;
    }

    @Override
    public ArrayList<Tir> getTirByMarca(Marca marca) {
        ArrayList<Tir> listaTiruri = new ArrayList<>();
        org.hibernate.Transaction tx = session.beginTransaction();
        Tir t = new Tir();
        t.setMarca(marca);
        Query q = session.createQuery("from Tir where marca= :marca").setProperties(t);
        listaTiruri = (ArrayList<Tir>) q.list();
        tx.commit();
        return listaTiruri;
    }

    @Override
    public ArrayList<Tir> getTirByStare(Stare stare) {
        ArrayList<Tir> listaTiruri = new ArrayList<>();
        org.hibernate.Transaction tx = session.beginTransaction();
        Tir t = new Tir();
        t.setStare(stare);
        Query q = session.createQuery("from Tir where stare= :stare").setProperties(t);
        listaTiruri = (ArrayList<Tir>) q.list();
        tx.commit();
        return listaTiruri;
    }

    @Override
    public ArrayList<Tir> getTirByNumarInmatriculare(String nrInmatriculare) {
        ArrayList<Tir> listaTiruri = new ArrayList<>();
        org.hibernate.Transaction tx = session.beginTransaction();
        Tir tir = new Tir();
        tir.setNrInmatriculare(nrInmatriculare);
        Query q = session.createQuery("from Tir where nrInmatriculare= :nrInmatriculare").setProperties(tir);
        listaTiruri = (ArrayList<Tir>) q.list();
        tx.commit();
        return listaTiruri;
    }
    
    public static void main(String[] args) {
        TiruriRepository tiruriRepository = new TiruriHibernateRepository();
        Stare s = new Stare();
        Marca marca = new Marca();
        marca.setId(1);
        s.setId(1);
        System.out.println(tiruriRepository.getTirByStare(s));
    }
    
}
