/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repositories;

import java.util.ArrayList;
import models.Marca;
import models.Model;
import org.hibernate.Query;
import org.hibernate.Session;
import utils.HibernateUtil;

/**
 *
 * @author Stefan
 */
public class ModelHibernateRepository implements ModelRepository{

    Session session = null;
    
    public ModelHibernateRepository(){
        this.session = HibernateUtil.getSessionFactory().openSession();
    }
    
    @Override
    public boolean adaugaModel(Model model) {
        org.hibernate.Transaction tx = session.beginTransaction();
        session.clear();
        if (model != null && model.getId() > 0) {
            session.saveOrUpdate(model);
            tx.commit();
            return true;
        }
        int id = (int) session.save(model);
        model.setId(id);
        if (id > 0) {
            tx.commit();
        } else {
            tx.rollback();
        }
        session.clear();
        return id > 0;
    }

    @Override
    public void stergeModel(Model model) {
        session.clear();
        org.hibernate.Transaction tx = session.beginTransaction();
        session.delete(model);
        tx.commit();
    }

    @Override
    public ArrayList<Model> getAll() {
        ArrayList<Model> listaModele = new ArrayList<>();
        org.hibernate.Transaction tx = session.beginTransaction();
        Query q = session.createQuery("from Model");
        listaModele = (ArrayList<Model>) q.list();
        tx.commit();
        return listaModele;
    }

    @Override
    public ArrayList<Model> getModeleByMarca(Marca marca) {
        ArrayList<Model> listaModele = new ArrayList<>();
        org.hibernate.Transaction tx = session.beginTransaction();
       
        Query q = session.createQuery("from Model where marca= :marca").setParameter("marca", marca);
        listaModele = (ArrayList<Model>) q.list();
        tx.commit();
        return listaModele;
    }
    
    public static void main(String[] args) {
        ModelRepository modelRepository = new ModelHibernateRepository();
        Marca marca = new Marca();
        marca.setId(1);
        ArrayList<Model> listaModele= modelRepository.getModeleByMarca(marca);
        System.out.println(listaModele);
    }
}
