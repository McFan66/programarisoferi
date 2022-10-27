/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repositories;

import java.util.ArrayList;
import java.util.List;
import models.InfoRoluriUtilizatori;
import models.Rol;
import models.Utilizator;
import models.UtilizatoriRoluri;

/**
 *
 * @author Stefan
 */
public interface UtilizatoriRoluriRepository {
    public boolean adaugaUtilizatoriRoluri(UtilizatoriRoluri utilizatoriRoluri);
    public void stergeUtilizatoriRoluri(UtilizatoriRoluri utilizatoriRoluri);
    public ArrayList<UtilizatoriRoluri> getAll();
    public ArrayList<UtilizatoriRoluri> getUtilizatoriRoluriByValid(boolean valid);
    public ArrayList<UtilizatoriRoluri> getUtilizatoriRoluriByRoluri(Rol rol);
    public int getUtilizatoriCuRol(Rol rol);
    public List<InfoRoluriUtilizatori> getRoluriCuNumarUtilizatoriAsociati();
    public ArrayList<UtilizatoriRoluri> getUtilizatoriRoluriByUtilizator(Utilizator utilizator);
    public ArrayList<UtilizatoriRoluri> getUtilizatoriRoluriActiveAndUpcomingByUtilizator(Utilizator utilizator);
}
