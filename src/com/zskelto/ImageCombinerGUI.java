package com.zskelto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ImageCombinerGUI extends JFrame {
    private JButton base_layer_load, top_layer_load;
    private JButton base_layer_show, top_layer_show;
    private JButton combine, back;
    private JLabel displayImage;
    private JPanel buttons;

    public ImageCombinerGUI(){
        base_layer_load = new JButton("Load Base Image");
        top_layer_load = new JButton("Load Top Image");
        base_layer_show = new JButton("Show Base Image");
        top_layer_show = new JButton("Show Top Image");

        displayImage =  new JLabel();

        buttons = new JPanel();
        buttons.add(base_layer_load);
        buttons.add(base_layer_show);
        buttons.add(top_layer_load);
        buttons.add(top_layer_show);

        setTitle("ImageCombiner");
        setSize(800,800);

        this.getContentPane().add(buttons, BorderLayout.PAGE_START);
        this.getContentPane().add(displayImage,BorderLayout.CENTER);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void setBaseLayerLoadListener(ImageCombinerListener a){
        base_layer_load.addActionListener(a);
    }

    public void setTopLayerLoadListener(ImageCombinerListener a){
        top_layer_load.addActionListener(a);
    }

    public void setBaseLayerShowListener(ImageCombinerListener a){
        base_layer_show.addActionListener(a);
    }

    public void setTopLayerShowListener(ImageCombinerListener a){
        top_layer_show.addActionListener(a);
    }

    public JButton getBaseLayerLoad(){
        return this.base_layer_load;
    }

    public JButton getBaseLayerShow(){
        return this.base_layer_show;
    }

    public JButton getTopLayerLoad(){
        return this.top_layer_load;
    }

    public JButton getTopLayerShow(){
        return this.top_layer_show;
    }

    public void setImage(ImageIcon icon){
        displayImage.setIcon(icon);
    }

    public void startGUI(){
        this.setVisible(true);
    }
}
