/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package renderers;

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
import utils.ImageUtils;

/**
 *
 * @author Vlad
 */
public class MeniuPrincipalListRenderer extends JLabel implements ListCellRenderer<Tir>{
    
    private final PozaService pozaService = AppSingleTone.getAppSingleToneInstance().getPozaService();

    
    @Override
    public Component getListCellRendererComponent(JList<? extends Tir> list, Tir value, int index, boolean isSelected, boolean cellHasFocus) {
        
            Poza p = pozaService.getPozaByTipAndObiect(1, value.getId()).get(0);
            File file = new File("./poze/tiruri/" + value.getNrInmatriculare() + "/" + p.getImagePath());
//            ImageIcon i = new ImageIcon(new ImageIcon(file.getAbsolutePath()).getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH));
           ImageIcon i =  ImageUtils.getRoundImageIcon(file);
            setIcon(i);
            setText(value.getDescriere());
        
        return this;
    }
}
