/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repositories;

import java.util.ArrayList;
import models.Utilizator;

/**
 *
 * @author Stefan
 */
public interface UtilizatorRepository {
    public boolean adaugaUtilizator(Utilizator utilizator);
    public void stergeUtilizator(Utilizator utilizator);
    public ArrayList<Utilizator> getAll();
    public Utilizator login(String email, String parola);
    public ArrayList<Utilizator> getUtilizatorByValid(boolean valid);
}
