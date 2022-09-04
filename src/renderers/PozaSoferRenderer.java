/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package renderers;

import gui.ImageAvatar;
import java.awt.Component;
import java.awt.Image;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import services.PozaService;
import models.AppSingleTone;

/**
 *
 * @author vladg
 */
public class PozaSoferRenderer extends ImageAvatar implements TableCellRenderer{

    private final PozaService pozaService = AppSingleTone.getAppSingleToneInstance().getPozaService();
    
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        
        File file = new File("./poze/soferi/" + value.toString());
        
        setIcon(new ImageIcon(new ImageIcon(file.getAbsolutePath()).getImage().getScaledInstance(100, 60, Image.SCALE_SMOOTH)));
        
        return (Component) value;
    }
    
}
