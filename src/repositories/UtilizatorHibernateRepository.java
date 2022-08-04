/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repositories;

import java.util.ArrayList;
import models.Utilizator;
import org.hibernate.Query;
import org.hibernate.Session;
import utils.HibernateUtil;

/**
 *
 * @author Stefan
 */
public class UtilizatorHibernateRepository implements UtilizatorRepository{
    
    Session session = null;

    public UtilizatorHibernateRepository() {
        this.session = HibernateUtil.getSessionFactory().openSession();
    }
    
    @Override
    public boolean adaugaUtilizator(Utilizator utilizator) {
        org.hibernate.Transaction tx = session.beginTransaction();
       
        if (utilizator!=null && utilizator.getId()>0){
            session.saveOrUpdate(utilizator);
            tx.commit();
            return true;
        }
        int id = (int) session.save(utilizator);
        utilizator.setId(id);
        if (id > 0) {
            tx.commit();
        } else {
            tx.rollback();
        }
        return id > 0;
    }

    @Override
    public void stergeUtilizator(Utilizator utilizator) {
        org.hibernate.Transaction tx = session.beginTransaction();
        session.delete(utilizator);
        tx.commit();
    }

    @Override
    public ArrayList<Utilizator> getAll() {
        ArrayList<Utilizator> listaUtilizatori = new ArrayList<>();
        org.hibernate.Transaction tx = session.beginTransaction();
        Query q = session.createQuery("from Utilizator");
        listaUtilizatori = (ArrayList<Utilizator>) q.list();
        tx.commit();
        return listaUtilizatori;
    }

    @Override
    public Utilizator login(String email, String parola) {
        org.hibernate.Transaction tx = session.beginTransaction();
        Utilizator utilizator = new Utilizator();
        utilizator.setEmail(email);
        utilizator.setParola(parola);
        Query q = session.createQuery("from Utilizator where email= :email and parola= :parola").setProperties(utilizator);
        Utilizator u = (Utilizator) q.uniqueResult();
        tx.commit();
        return u;
    }

    @Override
    public ArrayList<Utilizator> getUtilizatorByValid(boolean valid) {
        ArrayList<Utilizator> listaUtilizatori = new ArrayList<>();
        org.hibernate.Transaction tx = session.beginTransaction();
        Query q = session.createQuery("from Utilizator where valid= :valid").setParameter("valid", valid);
        listaUtilizatori = (ArrayList<Utilizator>) q.list();
        tx.commit();
        return listaUtilizatori;
    }
    
    public static void main(String[] args) {
        UtilizatorRepository utilizatorRepository = new UtilizatorHibernateRepository();
        Utilizator u = new Utilizator();
        u.setNume("Test");
        u.setPrenume("Tester");
        u.setValid(true);
        u.setEmail("test@test.com");
        u.setParola("test");
        utilizatorRepository.adaugaUtilizator(u);
    }
    
}
