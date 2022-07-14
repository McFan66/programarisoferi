/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repositories;

import java.util.ArrayList;
import models.Sofer;
import models.SoferiTiruri;
import models.Tir;
import org.hibernate.Query;
import org.hibernate.Session;
import utils.HibernateUtil;

/**
 *
 * @author Stefan
 */
public class SoferiTiruriHibernateRepository implements SoferiTiruriRepository {
    
    Session session = null;

    public SoferiTiruriHibernateRepository() {
        this.session = HibernateUtil.getSessionFactory().openSession();
    }
    
    

    @Override
    public boolean adaugaSoferTir(SoferiTiruri soferTir) {
        session.clear();
        org.hibernate.Transaction tx = session.beginTransaction();

        if (soferTir != null && soferTir.getId() > 0) {
            session.saveOrUpdate(soferTir);
            tx.commit();
            return true;
        }
        int id = (int) session.save(soferTir);
        soferTir.setId(id);
        if (id > 0) {
            tx.commit();
        } else {
            tx.rollback();
        }
        session.clear();
        return id > 0;
    }

    @Override
    public void stergeSoferTir(SoferiTiruri soferTir) {
        org.hibernate.Transaction tx = session.beginTransaction();
        session.delete(soferTir);
        tx.commit();
        session.clear();
    }

    @Override
    public ArrayList<SoferiTiruri> getAll() {
        ArrayList<SoferiTiruri> listaSoferiTiruri = new ArrayList<>();
        org.hibernate.Transaction tx = session.beginTransaction();
        Query q = session.createQuery("from SoferiTiruri");
        listaSoferiTiruri = (ArrayList<SoferiTiruri>) q.list();
        tx.commit();
        return listaSoferiTiruri;
    }

    @Override
    public ArrayList<SoferiTiruri> getSoferiTiruriBySofer(Sofer sofer) {
        ArrayList<SoferiTiruri> listaSoferiTiruri = new ArrayList<>();
        org.hibernate.Transaction tx = session.beginTransaction();
        SoferiTiruri st=new SoferiTiruri();
        st.setSofer(sofer);
        Query q = session.createQuery("from SoferiTiruri where idSofer= :idSofer").setProperties(st);
        listaSoferiTiruri = (ArrayList<SoferiTiruri>) q.list();
        tx.commit();
        return listaSoferiTiruri;
    }

    @Override
    public ArrayList<SoferiTiruri> getSoferiTiruriByTir(Tir tir) {
        ArrayList<SoferiTiruri> listaSoferiTiruri = new ArrayList<>();
        org.hibernate.Transaction tx = session.beginTransaction();
        SoferiTiruri st = new SoferiTiruri();
        st.setTir(tir);
        Query q = session.createQuery("from SoferiTiruri where idTir= :idTir").setProperties(st);
        listaSoferiTiruri = (ArrayList<SoferiTiruri>) q.list();
        tx.commit();
        return listaSoferiTiruri;
    }
    
    public static void main(String[] args) {
        SoferiTiruriRepository soferiTiruriRepository = new SoferiTiruriHibernateRepository();
        Tir t = new Tir();
        t.setId(1);
        System.out.println(soferiTiruriRepository.getSoferiTiruriByTir(t));
    }
    
}
