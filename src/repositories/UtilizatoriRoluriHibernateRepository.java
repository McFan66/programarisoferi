/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repositories;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import models.Rol;
import models.Utilizator;
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

        if (utilizatoriRoluri != null && utilizatoriRoluri.getId() > 0) {
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

    @Override
    public ArrayList<UtilizatoriRoluri> getUtilizatoriRoluriByValid(boolean valid) {
        ArrayList<UtilizatoriRoluri> listaUtilizatoriRoluri = new ArrayList<>();
        org.hibernate.Transaction tx = session.beginTransaction();
        Calendar c = Calendar.getInstance();
        Date azi = c.getTime();
        Query q;
        if (valid) {
            q = session.createQuery("from UtilizatoriRoluri where dataSfarsit is null or :azi <= dataSfarsit").setParameter("azi", azi);
        } else {
            q = session.createQuery("from UtilizatoriRoluri where dataSfarsit is not null and :azi > dataSfarsit").setParameter("azi", azi);
        }
        listaUtilizatoriRoluri = (ArrayList<UtilizatoriRoluri>) q.list();
        tx.commit();
        return listaUtilizatoriRoluri;
    }

    @Override
    public ArrayList<UtilizatoriRoluri> getUtilizatoriRoluriByRoluri(Rol rol) {
        ArrayList<UtilizatoriRoluri> listaUtilizatoriRoluri = new ArrayList<>();
        org.hibernate.Transaction tx = session.beginTransaction();
        Query q = session.createQuery("from UtilizatoriRoluri where idRol= :idRol").setParameter("idRol", rol.getId());
        listaUtilizatoriRoluri = (ArrayList<UtilizatoriRoluri>) q.list();
        tx.commit();
        return listaUtilizatoriRoluri;
    }

    @Override
    public int getUtilizatoriCuRol(Rol rol) {
        ArrayList<UtilizatoriRoluri> listaUtilizatoriRoluri = new ArrayList<>();
        org.hibernate.Transaction tx = session.beginTransaction();
        Query q = session.createQuery("from UtilizatoriRoluri where idRol= :idRol").setParameter("idRol", rol.getId());
        listaUtilizatoriRoluri = (ArrayList<UtilizatoriRoluri>) q.list();
        tx.commit();
        return listaUtilizatoriRoluri.size();
    }

    @Override
    public ArrayList<UtilizatoriRoluri> getUtilizatoriRoluriByUtilizator(Utilizator utilizator) {
        ArrayList<UtilizatoriRoluri> listaUtilizatoriRoluri = new ArrayList<>();
        org.hibernate.Transaction tx = session.beginTransaction();
        Query q = session.createQuery("from UtilizatoriRoluri where idUtilizator= :idUtilizator").setParameter("idUtilizator", utilizator.getId());
        listaUtilizatoriRoluri = (ArrayList<UtilizatoriRoluri>) q.list();
        tx.commit();
        return listaUtilizatoriRoluri;
    }

    @Override
    public ArrayList<UtilizatoriRoluri> getUtilizatoriRoluriActiveAndUpcomingByUtilizator(Utilizator utilizator) {
        ArrayList<UtilizatoriRoluri> listaUtilizatoriRoluri = new ArrayList<>();
        org.hibernate.Transaction tx = session.beginTransaction();
        Calendar c = Calendar.getInstance();
        Date azi = c.getTime();
        Query q = session.createQuery("from UtilizatoriRoluri where idUtilizator= :idUtilizator and (dataSfarsit is null or :azi <= dataSfarsit)");
        q.setParameter("idUtilizator", utilizator.getId());
        q.setParameter("azi", azi);
        listaUtilizatoriRoluri = (ArrayList<UtilizatoriRoluri>) q.list();
        tx.commit();
        return listaUtilizatoriRoluri;
    }

}
