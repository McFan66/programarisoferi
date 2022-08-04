/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repositories;

import java.util.ArrayList;
import models.UtilizatoriRoluri;

/**
 *
 * @author Stefan
 */
public interface UtilizatoriRoluriRepository {
    public boolean adaugaUtilizatoriRoluri(UtilizatoriRoluri utilizatoriRoluri);
    public void stergeUtilizatoriRoluri(UtilizatoriRoluri utilizatoriRoluri);
    public ArrayList<UtilizatoriRoluri> getAll();
}
