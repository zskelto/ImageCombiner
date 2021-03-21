package com.zskelto;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionListener;

public class ImageCombinerGUI extends JFrame {
    private JButton base_layer_load, top_layer_load;
    private JButton base_layer_show, top_layer_show;
    private JButton combine,save;
    private JLabel displayImage;
    private JLabel currentCoords;
    private JPanel coords, setupButtons, combineButtons, image;

    public ImageCombinerGUI(){
        //Create Buttons
        base_layer_load = new JButton("Load Base Image");
        top_layer_load = new JButton("Load Top Image");
        base_layer_show = new JButton("Show Base Image");
        top_layer_show = new JButton("Show Top Image");
        combine = new JButton("Combine");
        save = new JButton("Save");

        //Add Buttons to Panels
        setupButtons = new JPanel();
        combineButtons = new JPanel();

        setupButtons.add(base_layer_load);
        setupButtons.add(base_layer_show);
        setupButtons.add(top_layer_load);
        setupButtons.add(top_layer_show);

        combineButtons.add(combine);
        combineButtons.add(save);

        //Create Panel for Showing Mouse Coordinates
        coords = new JPanel();
        currentCoords = new JLabel("x:0 , y:0");
        currentCoords.setOpaque(true);
        currentCoords.setFont(new Font(currentCoords.getFont().getName(),Font.PLAIN, 14));
        currentCoords.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        currentCoords.setSize(new Dimension(120,120));
        coords.add(currentCoords);

        //Create Image Display Panel
        displayImage =  new JLabel();
        displayImage.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));

        image = new JPanel();
        image.add(displayImage);


        //Set Frame Settings
        this.setTitle("ImageCombiner");
        this.setSize(800,800);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        //Add Panels
        this.getContentPane().add(setupButtons, BorderLayout.PAGE_START);
        this.getContentPane().add(coords,BorderLayout.WEST);
        this.getContentPane().add(image,BorderLayout.CENTER);
        this.getContentPane().add(combineButtons,BorderLayout.PAGE_END);
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

    public void setImageMouseListener (ImageCombinerListener a) { displayImage.addMouseListener(a); }

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

    public void setText(String s) {currentCoords.setText(s);}

    public void startGUI(){
        this.setVisible(true);
    }
}
