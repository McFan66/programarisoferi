/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package renderers;

import gui.CustomLabel;
import gui.ImageAvatar;
import java.awt.Component;
import java.awt.Image;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import models.AppSingleTone;
import models.Poza;
import models.Tir;
import services.PozaService;
import services.SoferiTiruriService;
import services.StareService;
import utils.ImageUtils;

/**
 *
 * @author Vlad
 */
public class MeniuPrincipalListRenderer extends CustomLabel implements ListCellRenderer<Tir>{
    
    private final PozaService pozaService = AppSingleTone.getAppSingleToneInstance().getPozaService();
    private final SoferiTiruriService soferiTiruriService = AppSingleTone.getAppSingleToneInstance().getSoferiTiruriService();
    private final StareService stareService = AppSingleTone.getAppSingleToneInstance().getStareService();

    
    @Override
    public Component getListCellRendererComponent(JList<? extends Tir> list, Tir value, int index, boolean isSelected, boolean cellHasFocus) {
        
        Poza p;
        File file;
        if(value == null) {
            return this;
        }
        if(value.getIdStare() == 8) {
            p = pozaService.getPozaByTipAndObiect(2, soferiTiruriService.getSoferiTiruriInCursaByTir(value).getIdSofer()).get(0);
            file = new File("./poze/soferi" + "/" + p.getImagePath());
        }
        else {
            p = pozaService.getPozaByTipAndObiect(1, value.getId()).get(0);
            file = new File("./poze/tiruri/" + value.getNrInmatriculare() + "/" + p.getImagePath());
        }
    
        super.getLblTextTir().setText(value.getDescriere());
        super.getImageAvatar1().setIcon(new ImageIcon(file.getPath()));
        
        return this;
    }
}
