/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JPasswordField;

/**
 *
 * @author Stefan
 */
public class CustomPasswordField extends JPasswordField {
    private ImageIcon icon;

    @Override
    public void paint(Graphics gr) {
        super.paint(gr);
    }
    
    

    public void setIcon(ImageIcon icon) {
        this.icon = icon;
    }
    
    
}
