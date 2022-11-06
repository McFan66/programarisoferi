/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.File;
import services.InregistrareServiceImpl;
import services.InregistrariService;
import services.MarcaService;
import services.MarcaServiceImpl;
import services.ModelService;
import services.ModelServiceImpl;
import services.PozaService;
import services.PozaServiceImpl;
import services.SoferService;
import services.SoferServiceImpl;
import services.SoferiTiruriService;
import services.SoferiTiruriServiceImpl;
import services.StareService;
import services.StareServiceImpl;
import services.TiruriService;
import services.TiruriServiceImpl;
import services.UtilizatoriService;
import services.UtilizatoriServiceImpl;

/**
 *
 * @author Stefan
 */
public class AppSingleTone {
    
    private static AppSingleTone singleInstance;
    private InregistrariService inregistrariService;
    private TiruriService tiruriService;
    private StareService stareService;
    private SoferiTiruriService soferiTiruriService;
    private SoferService soferService;
    private PozaService pozaService;
    private MarcaService marcaService;
    private ModelService modelService;
    private UtilizatoriService utilizatoriService;
    
    private Utilizator utilizatorAutentificat;
    
    private File userConfig;

    private AppSingleTone() {
    }
    
    public static AppSingleTone getAppSingleToneInstance(){
        if (singleInstance == null){
            singleInstance = new AppSingleTone();
        }
        return singleInstance;
    }

    public Utilizator getUtilizatorAutentificat() {
        return utilizatorAutentificat;
    }

    public void setUtilizatorAutentificat(Utilizator utilizatorAutentificat) {
        this.utilizatorAutentificat = utilizatorAutentificat;
    }

    public InregistrariService getInregistrariService() {
        if(inregistrariService==null)
            inregistrariService=new InregistrareServiceImpl();
        return inregistrariService;
    }

    public PozaService getPozaService() {
        if(pozaService==null)
            pozaService=new PozaServiceImpl();
        return pozaService;
    }

    public SoferService getSoferService() {
        if(soferService==null)
            soferService=new SoferServiceImpl();
        return soferService;
    }

    public SoferiTiruriService getSoferiTiruriService() {
        if(soferiTiruriService==null)
            soferiTiruriService=new SoferiTiruriServiceImpl();
        return soferiTiruriService;
    }

    public StareService getStareService() {
        if(stareService==null)
            stareService=new StareServiceImpl();
        return stareService;
    }

    public TiruriService getTiruriService() {
        if(tiruriService==null)
            tiruriService=new TiruriServiceImpl();
        return tiruriService;
    }

    public MarcaService getMarcaService() {
        if(marcaService==null)
            marcaService=new MarcaServiceImpl();
        return marcaService;
    }

    public ModelService getModelService() {
        if(modelService==null)
            modelService=new ModelServiceImpl();
        return modelService;
    }

    public UtilizatoriService getUtilizatoriService() {
        if(utilizatoriService==null)
            utilizatoriService=new UtilizatoriServiceImpl();
        return utilizatoriService;
    }
    
    public File getUserConfig() {
        if(userConfig == null) {
            userConfig = new File("./userconfig.txt");
        }
        return userConfig;
    }
}
