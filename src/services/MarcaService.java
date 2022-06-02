/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.util.ArrayList;
import models.Marca;

/**
 *
 * @author Stefan
 */
public interface MarcaService {
    public boolean adaugaMarca(Marca marca);
    public void stergeMarca(Marca marca);
    public ArrayList<Marca> getAll();
}
