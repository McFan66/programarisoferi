/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repositories;

import java.util.ArrayList;
import models.Marca;
import org.hibernate.Query;
import org.hibernate.Session;
import utils.HibernateUtil;

/**
 *
 * @author Stefan
 */
public class MarcaHibernateRepository implements MarcaRepository {

    Session session = null;

    public MarcaHibernateRepository() {
        if(session  == null || !session.isOpen()){
           this.session = HibernateUtil.getSessionFactory().openSession();
        }
    }

    @Override
    public boolean adaugaMarca(Marca marca) {
        if(!session.isOpen()){
           this.session = HibernateUtil.getSessionFactory().openSession();
        }
        org.hibernate.Transaction tx = session.beginTransaction();  
        if (marca != null && marca.getId() > 0) {
            session.saveOrUpdate(marca);
            tx.commit();
            return true;
        }
        int id = (int) session.save(marca);
        marca.setId(id);
        if (id > 0) {
            tx.commit();
        } else {
            tx.rollback();
        }
        session.close();
        return id > 0;
    }

    @Override
    public void stergeMarca(Marca marca) {
        org.hibernate.Transaction tx = session.beginTransaction();
        session.delete(marca);
        tx.commit();
    }

    @Override
    public ArrayList<Marca> getAll() {
        ArrayList<Marca> listaMarci = new ArrayList<>();
        if(!session.isOpen()){
           this.session = HibernateUtil.getSessionFactory().openSession();
        }
        org.hibernate.Transaction tx = session.beginTransaction();
        Query q = session.createQuery("from Marca");
        listaMarci = (ArrayList<Marca>) q.list();
        tx.commit();
        session.close();
        return listaMarci;
    }
    
    public static void main(String[] args) {
        MarcaRepository marcaRepository = new MarcaHibernateRepository();
        System.out.println(marcaRepository.getAll());
    }

}
