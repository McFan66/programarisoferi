/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repositories;

import java.util.ArrayList;
import models.Stare;
import org.hibernate.Query;
import org.hibernate.Session;
import utils.HibernateUtil;

/**
 *
 * @author Stefan
 */
public class StareHibernateRepository implements StareRepository {

    Session session = null;

    public StareHibernateRepository() {
        this.session = HibernateUtil.getSessionFactory().openSession();        
    }
    
    
    
    @Override
    public boolean adaugaStare(Stare stare) {
        org.hibernate.Transaction tx = session.beginTransaction();

        if (stare != null && stare.getId() > 0) {
            session.saveOrUpdate(stare);
            tx.commit();
            return true;
        }
        int id = (int) session.save(stare);
        stare.setId(id);
        if (id > 0) {
            tx.commit();
        } else {
            tx.rollback();
        }
        return id > 0;
    }

    @Override
    public void stergeStare(Stare stare) {
       org.hibernate.Transaction tx = session.beginTransaction();
        session.delete(stare);
        tx.commit();
    }

    @Override
    public ArrayList<Stare> getAll() {
        ArrayList<Stare> listaStari = new ArrayList<>();
        org.hibernate.Transaction tx = session.beginTransaction();
        Query q = session.createQuery("from Stare");
        listaStari = (ArrayList<Stare>) q.list();
        tx.commit();
        return listaStari;
    }
    
    public static void main(String[] args) {
        StareRepository stareRepository = new StareHibernateRepository();
        Stare s = new Stare();
        s.setValid(1);
        s.setNume("Service");
        stareRepository.adaugaStare(s);
    }
    
}
