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
    private BufferedImage scaledTopLayer;

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

    public void resizeTopLayer(float percent){
        int scaledWidth = (int) ((float)top_layer.getWidth() * percent);
        int scaledHeight = (int) ((float)top_layer.getHeight() * percent);
        scaledTopLayer = new BufferedImage(scaledWidth, scaledHeight, top_layer.getType());

        Graphics2D g2d = scaledTopLayer.createGraphics();
        g2d.drawImage(top_layer, 0, 0, scaledWidth, scaledHeight, null);
        g2d.dispose();
    }

    public void mergeImages(int x, int y){
        int xi, yi;
        int topX, topY;
        int minTopX, minTopY;
        int maxTopX, maxTopY;

        minTopX = x - (scaledTopLayer.getWidth()/2);
        minTopY = y - (scaledTopLayer.getHeight()/2);
        maxTopX = x + (scaledTopLayer.getWidth()/2);
        maxTopY = y + (scaledTopLayer.getHeight()/2);

        for(xi=0;xi<base_layer.getWidth();xi++){
            for(yi=0;yi<base_layer.getHeight();yi++){
                if(xi >= minTopX && xi < maxTopX){
                    if(yi >= minTopY && yi < maxTopY){
                        topX = xi - (x - (scaledTopLayer.getWidth()/2));
                        topY = yi - (y - (scaledTopLayer.getHeight()/2));
                        Color color = new Color(scaledTopLayer.getRGB(topX,topY),true);
                        if( color.getRed() < 250 || color.getBlue() < 250 || color.getGreen() < 250){
                            base_layer.setRGB(xi,yi,scaledTopLayer.getRGB(topX,topY));
                        }
                    }
                }
            }
        }
    }

    public void saveImage(File file){
        File output = new File(file, "output.png");
        try {
            ImageIO.write(base_layer,"png",output);
        }
        catch(IOException e){
            System.out.println(e.getMessage());
        }
    }

}
