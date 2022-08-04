/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.util.ArrayList;
import models.Rol;
import repositories.RolHibernateRepository;
import repositories.RolRepository;

/**
 *
 * @author Stefan
 */
public class RoluriServiceImpl implements RoluriService{
    
    private RolRepository rolRepository = new RolHibernateRepository();

    @Override
    public boolean adaugaRol(Rol rol) {
        return rolRepository.adaugaRol(rol);
    }

    @Override
    public void stergeRol(Rol rol) {
        rolRepository.stergeRol(rol);
    }

    @Override
    public ArrayList<Rol> getAll() {
        return rolRepository.getAll();
    }
    
}
