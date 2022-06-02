/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JTextField;

public class CustomTextField extends JTextField implements KeyListener{

    public CustomTextField() {
        setFont(new java.awt.Font("Dialog", 1, 12));
        addKeyListener(this);
    }

    @Override
    public String getText() {
        return super.getText(); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
        if(Character.isLowerCase(e.getKeyChar()) && this.getText().trim().length() == 0) {
            e.setKeyChar((char) (e.getKeyChar() - 32));
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
    
}
