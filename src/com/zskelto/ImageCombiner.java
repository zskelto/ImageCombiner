package com.zskelto;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageCombiner {
    private BufferedImage base_layer;
    private BufferedImage top_layer;

    public ImageCombiner(){
    }

    public void setBaseLayer(){
        FileDialog fd = new FileDialog(new JFrame());
        fd.setVisible(true);
        File[] f = fd.getFiles();
        if (f.length > 0){
            try {
                base_layer = ImageIO.read(f[0]);
            }
            catch (IOException e){
                System.out.println(e.getMessage());
            }
        }
    }

    public void setTopLayer(){
        FileDialog fd = new FileDialog(new JFrame());
        fd.setVisible(true);
        File[] f = fd.getFiles();
        if (f.length > 0){
            try {
                top_layer = ImageIO.read(f[0]);
            }
            catch (IOException e){
                System.out.println(e.getMessage());
            }
        }
    }

    public BufferedImage getBaseLayer(){
        return this.base_layer;
    }

    public BufferedImage getTopLayer(){
        return this.top_layer;
    }

}
