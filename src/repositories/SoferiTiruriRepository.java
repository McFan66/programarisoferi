/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repositories;

import java.util.ArrayList;
import models.Sofer;
import models.SoferiTiruri;
import models.Tir;

/**
 *
 * @author Stefan
 */
public interface SoferiTiruriRepository {
    public boolean adaugaSoferTir(SoferiTiruri soferTir);
    public void stergeSoferTir(SoferiTiruri soferTir);
    public ArrayList<SoferiTiruri> getAll();
    public ArrayList<SoferiTiruri> getSoferiTiruriBySofer(Sofer sofer);
    public ArrayList<SoferiTiruri> getSoferiTiruriByTir(Tir tir);
}
