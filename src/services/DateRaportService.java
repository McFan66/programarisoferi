/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.util.ArrayList;
import models.DateRaport;

/**
 *
 * @author Vlad Apostol
 */
public interface DateRaportService {
    public boolean addDateRaport(DateRaport dateRaport);
    public void stergeDateRaport(DateRaport dateRaport);
    public ArrayList<DateRaport> getAll();
    public DateRaport getDateRaportById(int id);
    public ArrayList<DateRaport> getDateRaportByPath(String path);
    public ArrayList<DateRaport> getDateRaportFromToday();
}
