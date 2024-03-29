/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repositories;

import utils.HibernateUtil;
import java.util.ArrayList;
import models.Sofer;
import org.hibernate.Filter;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Stefan
 */
public class SoferiHibernateRepository implements SoferiRepository {

    Session session = null;
    
    Filter filterPoze;
    
    public SoferiHibernateRepository(){
        if(session == null || !session.isOpen()){
           this.session = HibernateUtil.getSessionFactory().openSession();
           filterPoze = session.enableFilter("filterPozeSoferi");
           filterPoze.setParameter("tipObiectParam", 1);
        }
    }
    
    @Override
    public boolean adaugaSofer(Sofer sofer) {
                if(!session.isOpen()){
           this.session = HibernateUtil.getSessionFactory().openSession();
        }
        org.hibernate.Transaction tx = session.beginTransaction();
       
        if (sofer!=null && sofer.getId()>0){
            session.saveOrUpdate(sofer);
            tx.commit();
            return true;
        }
        int id = (int) session.save(sofer);
        sofer.setId(id);
        if (id > 0) {
            tx.commit();
        } else {
            tx.rollback();
        }
        session.close();
        return id > 0; 
    }

    @Override
    public void stergeSofer(Sofer sofer) {
        org.hibernate.Transaction tx = session.beginTransaction();
        session.delete(sofer);
        tx.commit();
    }

    @Override
    public ArrayList<Sofer> getAll() {
        ArrayList<Sofer> listaSoferi = new ArrayList<>();
                if(!session.isOpen()){
           this.session = HibernateUtil.getSessionFactory().openSession();
        }
        org.hibernate.Transaction tx = session.beginTransaction();
        Query q = session.createQuery("from Sofer");
        listaSoferi = (ArrayList<Sofer>) q.list();
        tx.commit();
        session.close();
        return listaSoferi;
    }
    public static void main(String[] args) {
        SoferiRepository soferiRepository = new SoferiHibernateRepository();
        System.out.println(soferiRepository.getAll());
    }

    @Override
    public ArrayList<Sofer> getSoferByValid(boolean valid) {
        ArrayList<Sofer> listaSoferi = new ArrayList<>();
                if(!session.isOpen()){
           this.session = HibernateUtil.getSessionFactory().openSession();
        }
        org.hibernate.Transaction tx = session.beginTransaction();
        Query q = session.createQuery("from Sofer where valid = :valid").setParameter("valid", valid);
        listaSoferi = (ArrayList<Sofer>) q.list();
        tx.commit();
        session.close();
        return listaSoferi;
    }

//    @Override
//    public ArrayList<Sofer> getSoferiLiberi() {
//        ArrayList<Sofer> listaSoferi = new ArrayList<>();
//        org.hibernate.Transaction tx = session.beginTransaction();
//        String hql = "from Sofer where soferiTiruri = :soferiTiruri";
//        Query q = session.createQuery(hql).setParameter("soferiTiruri", null);
//        listaSoferi = (ArrayList<Sofer>) q.list();
//        tx.commit();
//        return listaSoferi;
//    }
    
}
