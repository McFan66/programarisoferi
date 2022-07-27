/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repositories;

import java.util.ArrayList;
import models.Stare;

/**
 *
 * @author Stefan
 */
public interface StareRepository {
    public boolean adaugaStare(Stare stare);
    public void stergeStare(Stare stare);
    public ArrayList<Stare> getAll();
    public Stare getStareByNume(String nume);
}
