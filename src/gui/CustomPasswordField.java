/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.border.Border;

/**
 *
 * @author Stefan
 */
public class CustomPasswordField extends JPasswordField {

    private Icon mIcon = new javax.swing.ImageIcon(getClass().getResource("/resources/hide-password20x20.png"));
    private boolean showPassword;
    private JLabel labelIcon;
    private Cursor defaultCursor;

    public CustomPasswordField() {
        defaultCursor = getCursor();
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                System.out.println("mouse entered");
                int x = (CustomPasswordField.this.getWidth() - mIcon.getIconWidth() - 5);
                if (e.getPoint().x >= x && e.getPoint().x <= (x + 20)) {
                    setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                } else {
                    setCursor(defaultCursor);
                }
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                int x = (CustomPasswordField.this.getWidth() - mIcon.getIconWidth() - 5);
                if (e.getPoint().x >= x && e.getPoint().x <= (x + 20)) {
                    setShowPassword(!showPassword);
                    if (showPassword) {
                        CustomPasswordField.this.setEchoChar((char) 0);
                    } else {
                        CustomPasswordField.this.setEchoChar('*');
                    }
                    setIcon(new javax.swing.ImageIcon(getClass().getResource(showPassword ? "/resources/show-password20x20.png" : "/resources/hide-password20x20.png")));
                }

            }

        });

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (mIcon != null) {
            Insets iconInsets = getBorder().getBorderInsets(this);
            mIcon.paintIcon(this, g, this.getWidth() - mIcon.getIconWidth() - 5, iconInsets.top - 1);
        }
    }

    public void setIcon(Icon icon) {
        mIcon = icon;
    }

    public void setShowPassword(boolean showPassword) {
        this.showPassword = showPassword;
    }

}
