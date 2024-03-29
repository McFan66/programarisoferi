package models;
// Generated May 12, 2022 5:57:09 PM by Hibernate Tools 4.3.1

import java.io.File;
import java.util.HashSet;
import java.util.Set;
import services.PozaService;
import services.PozaServiceImpl;

/**
 * Soferi generated by hbm2java
 */
public class Sofer implements java.io.Serializable {

    private int id;
    private String nume;
    private String prenume;
    private String cnp;
    private boolean valid;
    private Set<SoferiTiruri> soferiTiruri = new HashSet<SoferiTiruri>(0);
    private Set<Poza> poze = new HashSet<>(0);

    public Sofer() {
    }

    public Sofer(int id, String nume, String prenume, String cnp) {
        this.id = id;
        this.nume = nume;
        this.prenume = prenume;
        this.cnp = cnp;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNume() {
        return this.nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getPrenume() {
        return this.prenume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public String getCnp() {
        return this.cnp;
    }

    public void setCnp(String cnp) {
        this.cnp = cnp;
    }

    public Set<SoferiTiruri> getSoferiTiruri() {
        return soferiTiruri;
    }

    public void setSoferiTiruri(Set<SoferiTiruri> soferiTiruri) {
        this.soferiTiruri = soferiTiruri;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public File getPoza() {
        File currentDirectory = new File(".");
        PozaService pozaService = new PozaServiceImpl();
        if (!pozaService.getPozaByTipAndObiect(2, this.getId()).isEmpty()) {
            String imagePath = pozaService.getPozaByTipAndObiect(2, this.getId()).get(0).getImagePath();
            return new File(currentDirectory, String.format("/poze/soferi/%s", imagePath));
        }
        return new File(currentDirectory, String.format("/poze/no-image-icon.png"));
    }

//    public void setPoza(File poza){
//        this.imagepath=String.format("%s", poza.getName());
//    }
    public String getNumeComplet() {
        return String.format("%s %s", this.nume, this.prenume);
    }

    public Set<Poza> getPoze() {
        return poze;
    }

    public void setPoze(Set<Poza> poze) {
        this.poze = poze;
    }

    @Override
    public String toString() {
        return "Sofer{" + "id=" + id + ", nume=" + nume + ", prenume=" + prenume + ", cnp=" + cnp + ", valid=" + valid + ", soferiTiruri=" + soferiTiruri + ", poze=" + poze + '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Sofer other = (Sofer) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

}
