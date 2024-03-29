/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repositories;

import java.util.ArrayList;
import models.Marca;
import models.Model;
import models.Stare;
import models.Tir;
import org.hibernate.Filter;
import org.hibernate.Query;
import org.hibernate.Session;
import utils.HibernateUtil;

/**
 *
 * @author Stefan
 */
public class TiruriHibernateRepository implements TiruriRepository {

    Session session = null;
    
    Filter filterPoze;

    public TiruriHibernateRepository() {
        if (session == null || !session.isOpen()) {
            this.session = HibernateUtil.getSessionFactory().openSession();
            filterPoze = session.enableFilter("filterPoze");
            filterPoze.setParameter("tipObiectParam", 1);
        }
    }

    @Override
    public boolean adaugaTir(Tir tir) {
        if(!session.isOpen()) {
            this.session = HibernateUtil.getSessionFactory().openSession();
        }
        org.hibernate.Transaction tx = session.beginTransaction();
        session.clear();
        if (tir != null && tir.getId() > 0) {
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
        session.close();
        return id > 0;
    }

    @Override
    public void stergeTir(Tir tir) {
        session.clear();
        org.hibernate.Transaction tx = session.beginTransaction();
        session.delete(tir);
        tx.commit();
    }

    @Override
    public ArrayList<Tir> getAll() {
        if(!session.isOpen()){
           this.session = HibernateUtil.getSessionFactory().openSession();
        }
        ArrayList<Tir> listaTiruri = new ArrayList<>();
        org.hibernate.Transaction tx = session.beginTransaction();
        Query q = session.createQuery("from Tir");
        listaTiruri = (ArrayList<Tir>) q.list();
        tx.commit();
        session.close();
        return listaTiruri;
    }

    @Override
    public ArrayList<Tir> getTirByMarca(Marca marca) {
        ArrayList<Model> listaModele = new ArrayList<>();
        ArrayList<Tir> listaTiruri = new ArrayList<>();
                if(!session.isOpen()){
           this.session = HibernateUtil.getSessionFactory().openSession();
        }
        org.hibernate.Transaction tx = session.beginTransaction();
        Model model = new Model();
        model.setMarca(marca);
        Query q = session.createQuery("from Modele where marca= :marca").setProperties(model);
        listaModele = (ArrayList<Model>) q.list();
        for (Model m : listaModele) {
            Tir tir = new Tir();
            tir.setModel(m);
            Query qq = session.createQuery("from Tiruri where model= :model").setProperties(tir);
            listaTiruri = (ArrayList<Tir>) qq.list();
        }
        tx.commit();
        session.close();
        return listaTiruri;
    }

    @Override
    public ArrayList<Tir> getTirByStare(Stare stare) {
        ArrayList<Tir> listaTiruri = new ArrayList<>();
        if(!session.isOpen()){
           this.session = HibernateUtil.getSessionFactory().openSession();
        }
        org.hibernate.Transaction tx = session.beginTransaction();
        Tir t = new Tir();
        t.setStare(stare);
        Query q = session.createQuery("from Tir where stare= :stare").setProperties(t);
        listaTiruri = (ArrayList<Tir>) q.list();
        tx.commit();
        session.close();
        return listaTiruri;
    }

    @Override
    public ArrayList<Tir> getTirByNumarInmatriculare(String nrInmatriculare) {
        ArrayList<Tir> listaTiruri = new ArrayList<>();
                if(!session.isOpen()){
           this.session = HibernateUtil.getSessionFactory().openSession();
        }
        org.hibernate.Transaction tx = session.beginTransaction();
        Tir tir = new Tir();
        tir.setNrInmatriculare(nrInmatriculare);
        Query q = session.createQuery("from Tir where nrInmatriculare= :nrInmatriculare").setProperties(tir);
        listaTiruri = (ArrayList<Tir>) q.list();
        tx.commit();
        session.close();
        return listaTiruri;
    }

    public static void main(String[] args) {
        TiruriRepository tiruriRepository = new TiruriHibernateRepository();
        System.out.println(tiruriRepository.getAll());
    }

    @Override
    public ArrayList<Tir> getTirByValid(boolean valid) {
        ArrayList<Tir> listaTiruri = new ArrayList<>();
                if(!session.isOpen()){
           this.session = HibernateUtil.getSessionFactory().openSession();
        }
        org.hibernate.Transaction tx = session.beginTransaction();
        Query q = session.createQuery("from Tir where valid= :valid").setParameter("valid", valid);
        listaTiruri = (ArrayList<Tir>) q.list();
        tx.commit();
        session.close();
        return listaTiruri;
    }

}
