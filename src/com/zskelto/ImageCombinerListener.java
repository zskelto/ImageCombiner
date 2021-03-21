package com.zskelto;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ImageCombinerListener implements ActionListener {
    private ImageCombiner test;
    private ImageCombinerGUI gui;

    public ImageCombinerListener() {
        gui = new ImageCombinerGUI();
        test = new ImageCombiner();

        gui.setBaseLayerLoadListener(this);
        gui.setTopLayerLoadListener(this);
        gui.setBaseLayerShowListener(this);
        gui.setTopLayerShowListener(this);
        gui.startGUI();
    }

    public void actionPerformed(ActionEvent e){
        if (e.getSource() == gui.getBaseLayerLoad()){
            test.setBaseLayer();
        }
        else if(e.getSource() == gui.getBaseLayerShow()){
            ImageIcon icon = new ImageIcon(test.getBaseLayer());
            gui.setImage(icon);
        }
        else if(e.getSource() == gui.getTopLayerLoad()){
            test.setTopLayer();
        }
        else if(e.getSource() == gui.getTopLayerShow()){
            ImageIcon icon = new ImageIcon(test.getTopLayer());
            gui.setImage(icon);
        }
    }
}
