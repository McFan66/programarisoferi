/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.util.ArrayList;
import java.util.Date;
import models.Inregistrare;
import models.SoferiTiruri;

/**
 *
 * @author Stefan
 */
public interface InregistrariService {
    public boolean adaugaInregistrare(Inregistrare inregistrare);
    public void stergeInregistrare(Inregistrare inregistrare);
    public ArrayList<Inregistrare> getAll();
    public ArrayList<Inregistrare> getInregistrariBySoferiTiruri(SoferiTiruri soferTir);
    public ArrayList<Inregistrare> getInregistrariByDataSosire(Date dataSosire);
    public ArrayList<Inregistrare> getInregistrareByDataPlecare(Date dataPlecare);
    public ArrayList<Inregistrare> getInregistrareByNoPlecare();
    public ArrayList<Inregistrare> getInregistrariFinalizate();
    public ArrayList<Inregistrare> getInregistrariInDesfasurare();
    public ArrayList<Inregistrare> getInregistrariByDates(Date dataPlecare , Date dataSosire);
}
