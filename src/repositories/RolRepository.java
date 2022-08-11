/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repositories;

import java.util.ArrayList;
import models.Rol;

/**
 *
 * @author Stefan
 */
public interface RolRepository {
    public boolean adaugaRol(Rol rol);
    public void stergeRol(Rol rol);
    public ArrayList<Rol> getAll();
    public ArrayList<Rol> getRolByValid(boolean valid);
}
