/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repositories;

import java.util.ArrayList;
import models.Poza;

/**
 *
 * @author Stefan
 */
public interface PozaRepository { 
    public boolean adaugaPoza(Poza poza);
    public void stergePoza(Poza poza);
    public ArrayList<Poza> getAll();
    public ArrayList<Poza> getPozeByTipAndId(int tip, int id);
}
