/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repositories;

import java.util.ArrayList;
import models.UtilizatoriRoluri;
import org.hibernate.Query;
import org.hibernate.Session;
import utils.HibernateUtil;

/**
 *
 * @author Stefan
 */
public class UtilizatoriRoluriHibernateRepository implements UtilizatoriRoluriRepository {
    
    Session session = null;

    public UtilizatoriRoluriHibernateRepository() {
        this.session = HibernateUtil.getSessionFactory().openSession();
    }
    
    

    @Override
    public boolean adaugaUtilizatoriRoluri(UtilizatoriRoluri utilizatoriRoluri) {
        org.hibernate.Transaction tx = session.beginTransaction();
       
        if (utilizatoriRoluri!=null && utilizatoriRoluri.getId()>0){
            session.saveOrUpdate(utilizatoriRoluri);
            tx.commit();
            return true;
        }
        int id = (int) session.save(utilizatoriRoluri);
        utilizatoriRoluri.setId(id);
        if (id > 0) {
            tx.commit();
        } else {
            tx.rollback();
        }
        return id > 0;
    }

    @Override
    public void stergeUtilizatoriRoluri(UtilizatoriRoluri utilizatoriRoluri) {
        org.hibernate.Transaction tx = session.beginTransaction();
        session.delete(utilizatoriRoluri);
        tx.commit();
    }

    @Override
    public ArrayList<UtilizatoriRoluri> getAll() {
        ArrayList<UtilizatoriRoluri> listaUtilizatoriRoluri = new ArrayList<>();
        org.hibernate.Transaction tx = session.beginTransaction();
        Query q = session.createQuery("from UtilizatoriRoluri");
        listaUtilizatoriRoluri = (ArrayList<UtilizatoriRoluri>) q.list();
        tx.commit();
        return listaUtilizatoriRoluri;
    }
    
}
