package com.zskelto;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

public class ImageCombinerListener implements ActionListener, MouseListener, ChangeListener {
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
        gui.setCombineListener(this);
        gui.setSaveListener(this);
        gui.setSliderListener(this);
        gui.setLocationRelativeTo(null);
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
        else if(e.getSource() == gui.getCombine()){
            if(test.getBaseLayer() == null){
                JFrame warning = new JFrame();
                warning.setTitle("ERROR");
                warning.setSize(400,100);
                warning.setLocationRelativeTo(null);
                warning.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
                warning.setVisible(true);

                JLabel message = new JLabel("ERROR: Base Image not loaded!!!");
                message.setForeground(Color.RED);

                warning.add(message,BorderLayout.CENTER);
            }
            else if(test.getTopLayer() == null){
                JFrame warning = new JFrame();
                warning.setTitle("ERROR");
                warning.setSize(400,100);
                warning.setLocationRelativeTo(null);
                warning.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
                warning.setVisible(true);

                JLabel message = new JLabel("ERROR: Top Image not loaded!!!");
                message.setForeground(Color.RED);

                warning.add(message,BorderLayout.CENTER);
            }
            else if(x < 0 || y< 0){
                JFrame warning = new JFrame();
                warning.setTitle("ERROR");
                warning.setSize(400,100);
                warning.setLocationRelativeTo(null);
                warning.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
                warning.setVisible(true);

                JLabel message = new JLabel("ERROR: Invalid X, Y coordinates!!!");
                message.setForeground(Color.RED);

                warning.add(message,BorderLayout.CENTER);
            }
            else if(gui.getSliderData() != 0){
                //Resize Top Layer as Needed
                float percent = (float)gui.getSliderData();
                percent /= 100;
                test.resizeTopLayer(percent);

                //Merge Top Layer on top of Base Layer
                test.mergeImages(x, y);

                //Show New Base
                gui.setImage(new ImageIcon(test.getBaseLayer()));
            }
        }
        else if(e.getSource() == gui.getSave()){
            if(test.getBaseLayer() == null){
                JFrame warning = new JFrame();
                warning.setTitle("ERROR");
                warning.setSize(400,100);
                warning.setLocationRelativeTo(null);
                warning.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
                warning.setVisible(true);

                JLabel message = new JLabel("ERROR: No Base Image is loaded!!!");
                message.setForeground(Color.RED);

                warning.add(message,BorderLayout.CENTER);
            }
            else {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                if (fileChooser.showOpenDialog(gui) == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    test.saveImage(file, gui.getFileType());
                }
            }
        }
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        if(baseDisplaying) {
            x = e.getX();
            y = e.getY();
            gui.setText(" x: " + x + " y:" + y + " ");
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

    @Override
    public void stateChanged(ChangeEvent e) {
        JSlider slider = (JSlider) e.getSource();
        String msg = " Top Layer Scale: " + slider.getValue() + "% ";
        gui.setSliderMessage(msg);
    }
}
