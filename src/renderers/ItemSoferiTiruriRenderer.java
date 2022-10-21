/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package renderers;

import java.awt.Color;
import java.awt.Component;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import models.AppSingleTone;
import models.SoferiTiruri;
import services.StareService;

/**
 *
 * @author Vlad
 */
public class ItemSoferiTiruriRenderer extends DefaultListCellRenderer {

    private Color defaultBackgroundColor = getBackground();
    private Color defaultForegroundColor = getForeground();
    private StareService stareService = AppSingleTone.getAppSingleToneInstance().getStareService();

    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        if (value instanceof SoferiTiruri) {
            SoferiTiruri sf = (SoferiTiruri) value;

            setText(sf.getSofer().getNumeComplet() + " - " + sf.getTir().getModel().getMarca().getNume() + " " + sf.getTir().getNrInmatriculare());

            if (((SoferiTiruri) value).getTir().getStare().equals(stareService.getStareByNume("Service"))) {
                setBackground(new Color(245, 56, 39));
                setForeground(Color.BLACK);
                setText(sf.getSofer().getNumeComplet() + " - " + sf.getTir().getModel().getMarca().getNume() + " " + sf.getTir().getNrInmatriculare() + " INVALID-IN SERVICE");
            } else {
                if (isSelected) {
                    setBackground(new Color(25, 116, 211));
                    setForeground(Color.white);
                } else {
                    setBackground(Color.white);
                    setForeground(defaultForegroundColor);
                }
            }
        }
        return this;
    }

}
