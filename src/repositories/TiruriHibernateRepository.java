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
        session.clear();
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
        ArrayList<Tir> listaTiruri = new ArrayList<>();
        org.hibernate.Transaction tx = session.beginTransaction();
        Query q = session.createQuery("from Tir");
        listaTiruri = (ArrayList<Tir>) q.list();
        tx.commit();
        return listaTiruri;
    }

    @Override
    public ArrayList<Tir> getTirByMarca(Marca marca) {
        ArrayList<Model> listaModele = new ArrayList<>();
        ArrayList<Tir> listaTiruri = new ArrayList<>();
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
        s.setId(3);
        System.out.println(tiruriRepository.getTirByStare(s));
    }

    @Override
    public ArrayList<Tir> getTirByValid(boolean valid) {
        ArrayList<Tir> listaTiruri = new ArrayList<>();
        org.hibernate.Transaction tx = session.beginTransaction();
        Query q = session.createQuery("from Tir where valid= :valid").setParameter("valid", valid);
        listaTiruri = (ArrayList<Tir>) q.list();
        tx.commit();
        return listaTiruri;
    }

}
