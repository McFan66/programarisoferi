/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.Date;

/**
 *
 * @author Vlad Apostol
 */
public class DateRaport {
   private int id;
   private String numeRaport;
   private int utilizator;
   private Date dataSubmit;
   private Date dataGenerare;
   private String stare;
   private String reportPath;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumeRaport() {
        return numeRaport;
    }

    public void setNumeRaport(String numeRaport) {
        this.numeRaport = numeRaport;
    }

    public int getUtilizator() {
        return utilizator;
    }

    public void setUtilizator(int utilizator) {
        this.utilizator = utilizator;
    }

    public Date getDataSubmit() {
        return dataSubmit;
    }

    public void setDataSubmit(Date dataSubmit) {
        this.dataSubmit = dataSubmit;
    }

    public Date getDataGenerare() {
        return dataGenerare;
    }

    public void setDataGenerare(Date dataGenerare) {
        this.dataGenerare = dataGenerare;
    }

    public String getStare() {
        return stare;
    }

    public void setStare(String stare) {
        this.stare = stare;
    }

    public String getReportPath() {
        return reportPath;
    }

    public void setReportPath(String reportPath) {
        this.reportPath = reportPath;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DateRaport other = (DateRaport) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DateRaport{" + "id=" + id + ", numeRaport=" + numeRaport + ", utilizator=" + utilizator + ", dataSubmit=" + dataSubmit + ", dataGenerare=" + dataGenerare + ", stare=" + stare + ", reportPath=" + reportPath + '}';
    }
   
}
