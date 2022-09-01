/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import com.sun.javafx.iio.common.ImageTools;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 *
 * @author Vlad
 */
public class ImageUtils {

    public static void applyQualityRenderingHints(Graphics2D g2d) {

        g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
        g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);

    }

    public static Image getScaledImage(Image srcImg, int w, int h){
        BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = resizedImg.createGraphics();

        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(srcImg, 0, 0, w, h, null);
        g2.dispose();

        return resizedImg;
    }
    
    public static ImageIcon getRoundImageIcon(File imgFile) {
        try {
            Image master = ImageIO.read(imgFile);
            
            master = getScaledImage(master, 60, 60);
            
            int diameter = Math.min(60, 60);
            BufferedImage mask = new BufferedImage(60, 60, BufferedImage.TYPE_INT_ARGB);
            
            Graphics2D g2d = mask.createGraphics();
            applyQualityRenderingHints(g2d);
            g2d.fillOval(0 , 0 , diameter -1, diameter -1);
            g2d.dispose();

            BufferedImage masked = new BufferedImage(diameter, diameter, BufferedImage.TYPE_INT_ARGB);
            g2d = masked.createGraphics();
            applyQualityRenderingHints(g2d);
            int x = (diameter - 60) / 2;
            int y = (diameter - 60) / 2;
           // g2d.drawImage(master, x, y, null);
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.DST_IN));
            g2d.fillOval(x, y, diameter+20, diameter+20);
            g2d.drawImage(mask, 0, 0, null);
            g2d.dispose();
            return new ImageIcon(mask);
        } catch (IOException ex) {
            Logger.getLogger(ImageUtils.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

}
