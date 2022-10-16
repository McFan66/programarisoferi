/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repositories;

import java.util.ArrayList;
import models.DateRaport;

/**
 *
 * @author Vlad Apostol
 */
public interface DateRaportRepository {
    public boolean addDateRaport(DateRaport dateRaport);
    public void stergeDateRaport(DateRaport dateRaport);
     public DateRaport getDateRaportById(int id);
    public ArrayList<DateRaport> getAll();
    public ArrayList<DateRaport> getDateRaportFromToday();
}
