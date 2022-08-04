package models;
// Generated Jun 2, 2022 6:30:51 PM by Hibernate Tools 4.3.1


import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Tir generated by hbm2java
 */
public class Tir  implements java.io.Serializable {


     private int id;
     private String nrInmatriculare;
     private int idModel;
     private int idStare;
     private Stare stare;
     private Model model;
     private boolean valid;
  //   private ArrayList<File> poze;
  //   private File folderPoze;
     private Set soferiTiruri = new HashSet(0);

    public Tir() {
    }

	
    public Tir(Model model, String nrInmatriculare, int idStare, Stare stare) {
        this.model = model;
        this.nrInmatriculare = nrInmatriculare;
        this.idStare = idStare;
        this.stare = stare;
    }
    public Tir(Model model, String nrInmatriculare, int idStare, Stare stare, Set soferiTiruri) {
       this.model = model;
       this.nrInmatriculare = nrInmatriculare;
       this.idStare = idStare;
       this.stare = stare;
       this.soferiTiruri = soferiTiruri;
    }
   
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public Model getModel() {
        return this.model;
    }
    
    public void setModel(Model model) {
        this.model = model;
    }
    public String getNrInmatriculare() {
        return this.nrInmatriculare;
    }
    
    public void setNrInmatriculare(String nrInmatriculare) {
        this.nrInmatriculare = nrInmatriculare;
    }
    public int getIdStare() {
        return this.idStare;
    }
    
    public void setIdStare(int idStare) {
        this.idStare = idStare;
    }
    public Stare getStare() {
        return this.stare;
    }
    
    public void setStare(Stare stare) {
        this.stare = stare;
    }
    public Set getSoferiTiruri() {
        return this.soferiTiruri;
    }
    
    public void setSoferiTiruri(Set soferiTiruri) {
        this.soferiTiruri = soferiTiruri;
    }

    public int getIdModel() {
        return idModel;
    }

    public void setIdModel(int idModel) {
        this.idModel = idModel;
    }

//    public ArrayList<File> getPoze() {
//        return poze;
//    }
//
//    public void setPoze(ArrayList<File> poze) {
//        this.poze = poze;
//    }
//
//    public File getFolderPoze() {
//        return folderPoze;
//    }
//
//    public void setFolderPoze(File folderPoze) {
//        this.folderPoze = folderPoze;
//    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    @Override
    public String toString() {
        return "Tir{" + "id=" + id + ", nrInmatriculare=" + nrInmatriculare + ", idModel=" + idModel + ", idStare=" + idStare + ", stare=" + stare + ", model=" + model + ", valid=" + valid + ", soferiTiruri=" + soferiTiruri + '}';
    }




}


