/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.util.ArrayList;
import models.Sofer;

/**
 *
 * @author Stefan
 */
public interface SoferService {
    public boolean salveazaSofer(Sofer sofer);
    public void stergeSofer(Sofer sofer);
    public ArrayList<Sofer> getAll();
}
