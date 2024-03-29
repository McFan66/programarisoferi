/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repositories;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import models.DateRaport;
import org.hibernate.Query;
import org.hibernate.Session;
import utils.HibernateUtil;

/**
 *
 * @author Vlad Apostol
 */
public class DateRaportHibernateRepository implements DateRaportRepository {

    Session session = null;

    public DateRaportHibernateRepository() {
        if (session == null || !session.isOpen()) {
            this.session = HibernateUtil.getSessionFactory().openSession();
        }
    }

    @Override
    public boolean addDateRaport(DateRaport dateRaport) {
        if (!session.isOpen()) {
            this.session = HibernateUtil.getSessionFactory().openSession();
        }
        org.hibernate.Transaction tx = session.beginTransaction();

        if (dateRaport != null && dateRaport.getId() > 0) {
            session.saveOrUpdate(dateRaport);
            tx.commit();
            return true;
        }
        int id = (int) session.save(dateRaport);
        dateRaport.setId(id);
        if (id > 0) {
            tx.commit();
        } else {
            tx.rollback();
        }
        session.close();
        return id > 0;
    }

    @Override
    public void stergeDateRaport(DateRaport dateRaport) {
        if(!session.isOpen()) {
            this.session = HibernateUtil.getSessionFactory().openSession();
        }
        org.hibernate.Transaction tx = session.beginTransaction();
        session.delete(dateRaport);
        tx.commit();
        session.close();
    }

    @Override
    public ArrayList<DateRaport> getAll() {
        if (!session.isOpen()) {
            this.session = HibernateUtil.getSessionFactory().openSession();
        }
        ArrayList<DateRaport> listaDateRaport = new ArrayList<>();
        org.hibernate.Transaction tx = session.beginTransaction();
        String hql = "from DateRaport";
        Query q = session.createQuery(hql);
        listaDateRaport = (ArrayList<DateRaport>) q.list();
        tx.commit();
        session.close();
        return listaDateRaport;
    }

    public DateRaport getDateRaportById(int id) {
        if (!session.isOpen()) {
            this.session = HibernateUtil.getSessionFactory().openSession();
        }
        DateRaport dateRaport = new DateRaport();
        org.hibernate.Transaction tx = session.beginTransaction();
        String hql = "from DateRaport dp where dp.id = :id";
        Query q = session.createQuery(hql).setParameter("id", id);
        dateRaport = (DateRaport) q.uniqueResult();
        tx.commit();
        session.close();
        return dateRaport;
    }

    @Override
    public ArrayList<DateRaport> getDateRaportFromToday() {
        if(!session.isOpen()) {
            this.session = HibernateUtil.getSessionFactory().openSession();
        }
        ArrayList<DateRaport> listaDateRaport = new ArrayList<>();
        org.hibernate.Transaction tx = session.beginTransaction();
        String hql = "from DateRaport dp where dp.dataSubmit >= :dataSubmit";
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.MILLISECOND ,0);
        Query q = session.createQuery(hql).setDate("dataSubmit", c.getTime());
        listaDateRaport = (ArrayList<DateRaport>) q.list();
        tx.commit();
        session.close();
        return listaDateRaport;
    }

    public static void main(String[] args) {
        DateRaportRepository dateRaportRepository = new DateRaportHibernateRepository();
        DateRaport dateRaport = new DateRaport();
        dateRaport.setDataGenerare(Calendar.getInstance().getTime());
        dateRaport.setDataSubmit(Calendar.getInstance().getTime());
        dateRaport.setNumeRaport("Aiausasdasda");
        dateRaport.setReportPath("");
        dateRaport.setStare("AIaus");
//        dateRaport.setUtilizator(2);
        dateRaportRepository.addDateRaport(dateRaport);
        System.out.println(dateRaportRepository.getAll());
    }

    @Override
    public ArrayList<DateRaport> getDateRaportByPath(String path) {
        if(!session.isOpen()) {
            this.session = HibernateUtil.getSessionFactory().openSession();
        }
        ArrayList<DateRaport> dateRaport = new ArrayList<>();
        org.hibernate.Transaction tx = session.beginTransaction();
        String hql = "from DateRaport dp where dp.reportPath = :reportPath";
        Query q = session.createQuery(hql).setParameter("reportPath", path);
        dateRaport = (ArrayList<DateRaport>) q.list();
        tx.commit();
        session.close();
        return dateRaport;
    }
}
