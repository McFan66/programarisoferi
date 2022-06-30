/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repositories;

import java.util.ArrayList;
import models.Poza;
import org.hibernate.Query;
import org.hibernate.Session;
import utils.HibernateUtil;

/**
 *
 * @author Stefan
 */
public class PozaHibernateRepository implements PozaRepository {

    Session session = null;

    public PozaHibernateRepository() {
        this.session = HibernateUtil.getSessionFactory().openSession();
    }

    @Override
    public boolean adaugaPoza(Poza poza) {
        org.hibernate.Transaction tx = session.beginTransaction();

        if (poza != null && poza.getId() > 0) {
            session.saveOrUpdate(poza);
            tx.commit();
            return true;
        }
        int id = (int) session.save(poza);
        poza.setId(id);
        if (id > 0) {
            tx.commit();
        } else {
            tx.rollback();
        }
        return id > 0;
    }

    @Override
    public void stergePoza(Poza poza) {
        org.hibernate.Transaction tx = session.beginTransaction();
        session.delete(poza);
        tx.commit();
    }

    @Override
    public ArrayList<Poza> getAll() {
        ArrayList<Poza> listaPoze = new ArrayList<>();
        org.hibernate.Transaction tx = session.beginTransaction();
        Query q = session.createQuery("from Poza");
        listaPoze = (ArrayList<Poza>) q.list();
        tx.commit();
        return listaPoze;
    }

    @Override
    public ArrayList<Poza> getPozeByTipAndId(int tip, int id) {
        ArrayList<Poza> listaPoze = new ArrayList<>();
        org.hibernate.Transaction tx = session.beginTransaction();
        Query q = session.createQuery("from Poza where tipObiect= :tip and idObiect= :id");
        q.setParameter("tip", tip);
        q.setParameter("id", id);
        listaPoze = (ArrayList<Poza>) q.list();
        tx.commit();
        return listaPoze;
    }

}
