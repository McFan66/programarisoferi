/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repositories;

import java.util.ArrayList;
import models.Sofer;

/**
 *
 * @author Stefan
 */
public interface SoferiRepository {
    public boolean adaugaSofer(Sofer sofer);
    public void stergeSofer(Sofer sofer);
    public ArrayList<Sofer> getAll();
    public ArrayList<Sofer> getSoferByValid(boolean valid);
}
