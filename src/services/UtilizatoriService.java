/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.util.ArrayList;
import models.Rol;
import models.Utilizator;

/**
 *
 * @author Stefan
 */
public interface UtilizatoriService {
    public boolean adaugaUtilizator(Utilizator utilizator);
    public void stergeUtilizator(Utilizator utilizator);
    public ArrayList<Utilizator> getAll();
    public Utilizator login(String email, String parola);
    public ArrayList<Utilizator> getUtilizatoriByValid(boolean valid);
    public Rol getRolulUtilizatorului(Utilizator utilizator);
    public ArrayList<Rol> getListaRoluriActive(Utilizator utilizator);
}
