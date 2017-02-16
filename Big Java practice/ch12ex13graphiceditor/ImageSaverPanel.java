/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ch12ex13graphiceditor;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 *
 * @author McNasty
 */
public class ImageSaverPanel extends JPanel {
    public void save(String imageFile) {
        Rectangle r = getBounds();

        try {
            BufferedImage i = new BufferedImage(r.width, r.height,
                    BufferedImage.TYPE_INT_RGB);
            Graphics g = i.getGraphics();
            paint(g);
            ImageIO.write(i, "png", new File(imageFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
