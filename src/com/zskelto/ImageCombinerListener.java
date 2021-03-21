package com.zskelto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ImageCombinerListener implements ActionListener, MouseListener {
    private ImageCombiner test;
    private ImageCombinerGUI gui;
    private boolean baseDisplaying;
    private int x,y;

    public ImageCombinerListener() {
        baseDisplaying = false;
        gui = new ImageCombinerGUI();
        test = new ImageCombiner();

        gui.setBaseLayerLoadListener(this);
        gui.setTopLayerLoadListener(this);
        gui.setBaseLayerShowListener(this);
        gui.setTopLayerShowListener(this);
        gui.setImageMouseListener(this);
        gui.startGUI();
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if (e.getSource() == gui.getBaseLayerLoad()){
            test.setBaseLayer();
        }
        else if(e.getSource() == gui.getBaseLayerShow()){
            if(test.getBaseLayer() != null) {
                ImageIcon icon = new ImageIcon(test.getBaseLayer());
                gui.setImage(icon);
                baseDisplaying = true;
            }
        }
        else if(e.getSource() == gui.getTopLayerLoad()){
            test.setTopLayer();
        }
        else if(e.getSource() == gui.getTopLayerShow()){
            if(test.getTopLayer() != null) {
                ImageIcon icon = new ImageIcon(test.getTopLayer());
                gui.setImage(icon);
                baseDisplaying = false;
            }
        }
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        if(baseDisplaying) {
            x = e.getX();
            y = e.getY();
            gui.setText("x: " + x + " y:" + y);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
