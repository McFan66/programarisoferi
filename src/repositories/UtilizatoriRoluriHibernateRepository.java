/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repositories;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import models.InfoRoluriUtilizatori;
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
        org.hibernate.Transaction tx = session.beginTransaction();
        Calendar c = Calendar.getInstance();
        Date azi = c.getTime();
        //select count(distinct utilizator) from utilizatori_roluri where rol=1 and data_inceput <= '2022-10-28' and data_sfarsit >= '2022-09-20'

        Query q = session.createSQLQuery("select count(distinct utilizator) from utilizatori_roluri where rol= :idRol and data_inceput <= :azi and data_sfarsit >= :azi");
        q.setParameter("idRol", rol.getId());
        q.setParameter("azi", azi);
        int numar = Integer.parseInt(q.uniqueResult().toString());
        tx.commit();
        return numar;
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

    @Override
    public List<InfoRoluriUtilizatori> getRoluriCuNumarUtilizatoriAsociati() {
        org.hibernate.Transaction tx = session.beginTransaction();
        String sql = "select r.NUME as numerol,COUNT(ur.UTILIZATOR) AS numarUtilizatori "
                + "from roluri r left join utilizatori_roluri ur on ur.ROL=r.ID where "
                + "(ur.data_sfarsit is null or (ur.data_sfarsit >=CURRENT_DATE AND ur.DATA_INCEPUT<=CURRENT_DATE)) "
                + "GROUP BY ur.ROL,r.nume";
        Query query = session.createSQLQuery(sql);
        List<InfoRoluriUtilizatori> results = query.list();
        tx.commit();
        return results;
    }

}
