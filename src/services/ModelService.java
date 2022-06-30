/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.util.ArrayList;
import models.Marca;
import models.Model;

/**
 *
 * @author Stefan
 */
public interface ModelService {
    public boolean adaugaModel(Model model);
    public void stergeModel(Model model);
    public ArrayList<Model> getAll();
    public ArrayList<Model> getModeleByMarca(Marca marca);
}
