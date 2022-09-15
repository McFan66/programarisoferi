/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package renderers;

import gui.CustomLabel;
import gui.ImageAvatar;
import java.awt.Component;
import java.awt.Font;
import java.awt.Image;
import java.io.File;
import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JList;
import models.AppSingleTone;
import models.Sofer;
import services.PozaService;

/**
 *
 * @author vladg
 */
public class MeniuPrincipalSoferRenderer extends DefaultListCellRenderer {

    private CustomLabel customLabel = new CustomLabel();
    private PozaService pozaService = AppSingleTone.getAppSingleToneInstance().getPozaService();
            
    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        
        File file;
        
        if (value instanceof Sofer) {
            if(!pozaService.getPozaByTipAndObiect(2, ((Sofer) value).getId()).isEmpty()) {
                 file = new File("./poze/soferi/" + pozaService.getPozaByTipAndObiect(2, ((Sofer) value).getId()).get(0).getImagePath());
            } else {
                file = new File("./poze/soferi/default-sofer-image.jpg");
            }
            customLabel.getImageAvatar1().setIcon(new ImageIcon(new ImageIcon(file.getPath()).getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH)));
            customLabel.getImageAvatar1().setBorderSize(3);
            customLabel.getLblTextTir().setText(((Sofer) value).getNumeComplet());
            customLabel.getLblTextTir().setFont(new Font("Dialog", Font.BOLD, 13));
        }
        return customLabel;
    }

}
