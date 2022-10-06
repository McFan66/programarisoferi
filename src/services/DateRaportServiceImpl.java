/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.util.ArrayList;
import models.DateRaport;
import repositories.DateRaportHibernateRepository;
import repositories.DateRaportRepository;

/**
 *
 * @author Vlad Apostol
 */
public class DateRaportServiceImpl implements DateRaportService{

    private DateRaportRepository dateRaportRepository = new DateRaportHibernateRepository();
    
    @Override
    public boolean addDateRaport(DateRaport dateRaport) {
        return dateRaportRepository.addDateRaport(dateRaport);
    }

    @Override
    public void stergeDateRaport(DateRaport dateRaport) {
        dateRaportRepository.stergeDateRaport(dateRaport);
    }

    @Override
    public ArrayList<DateRaport> getAll() {
        return dateRaportRepository.getAll();
    }
    
}
