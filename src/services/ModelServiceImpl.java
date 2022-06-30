/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.util.ArrayList;
import models.Marca;
import models.Model;
import repositories.ModelHibernateRepository;
import repositories.ModelRepository;

/**
 *
 * @author Stefan
 */
public class ModelServiceImpl implements ModelService {

    private ModelRepository modelRepository = new ModelHibernateRepository();
    
    @Override
    public boolean adaugaModel(Model model) {
        return modelRepository.adaugaModel(model);
    }

    @Override
    public void stergeModel(Model model) {
        modelRepository.stergeModel(model);
    }

    @Override
    public ArrayList<Model> getAll() {
        return modelRepository.getAll();
    }

    @Override
    public ArrayList<Model> getModeleByMarca(Marca marca) {
        return modelRepository.getModeleByMarca(marca);
    }
    
}
