/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repositories;

import java.util.ArrayList;
import models.Rol;
import org.hibernate.Query;
import org.hibernate.Session;
import utils.HibernateUtil;

/**
 *
 * @author Stefan
 */
public class RolHibernateRepository implements RolRepository{
    
    Session session = null;

    public RolHibernateRepository() {       
        this.session = HibernateUtil.getSessionFactory().openSession();
    }
    
    

    @Override
    public boolean adaugaRol(Rol rol) {
        org.hibernate.Transaction tx = session.beginTransaction();
       
        if (rol!=null && rol.getId()>0){
            session.saveOrUpdate(rol);
            tx.commit();
            return true;
        }
        int id = (int) session.save(rol);
        rol.setId(id);
        if (id > 0) {
            tx.commit();
        } else {
            tx.rollback();
        }
        return id > 0;
    }

    @Override
    public void stergeRol(Rol rol) {
        org.hibernate.Transaction tx = session.beginTransaction();
        session.delete(rol);
        tx.commit();
    }

    @Override
    public ArrayList<Rol> getAll() {
        ArrayList<Rol> listaRoluri = new ArrayList<>();
        org.hibernate.Transaction tx = session.beginTransaction();
        Query q = session.createQuery("from Rol");
        listaRoluri = (ArrayList<Rol>) q.list();
        tx.commit();
        return listaRoluri;
    }
    
}
