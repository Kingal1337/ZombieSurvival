/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2016 Alan Tsui
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 */
package zombiesurvival;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * 
 * @author Alan Tsui
 * @since 1.0
 * @version 1.0
 */
public class OptionsPanel extends JPanel{
    public static ChangeControlsPanel controlPanel;
    private JPanel panel;
    public OptionsPanel(){        
        setLayout(null);
        setPreferredSize(new Dimension(850,600));
        setBounds(0,0,850,600);
        
        panel = new JPanel(){
            @Override
            public void paintComponent(Graphics g){
                super.paintComponent(g);
                Graphics2D gd = (Graphics2D)g;
                
                try {
                    gd.drawImage(ImageIO.read(getClass().getResource("/res/ZombieSurvivalBackground.png")), null, -100, -235);
                } catch (IOException ex) {
                    Logger.getLogger(StartMenu.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
        panel.setLayout(null);
        panel.setPreferredSize(new Dimension(650,200));
        panel.setBounds((850/2)-(650/2),(600/2)-(130/2),650,200);
        panel.setLayout(null);
        
        
        JLabel title = new JLabel("Options",SwingConstants.CENTER);
        title.setFont(StartMenu.myFont.deriveFont(Font.BOLD,65f));
        title.setPreferredSize(new Dimension(800,200));
        title.setForeground(Color.DARK_GRAY);
        title.setBounds((850/2)-(800/2),20,800,200);
        add(title);
        
        JButton controls = new JButton("Controls");
        controls.setOpaque(true);
        controls.setBackground(new Color(0.0f,0.0f,0.0f,0.0f));
        controls.setBorder(BorderFactory.createEmptyBorder());
        controls.setFocusPainted(false); 
        controls.setFont(StartMenu.myFont.deriveFont(Font.BOLD,30f));
        controls.setPreferredSize(new Dimension(200,50));
        controls.setBounds((650/2)-(200/2),10,200,50);
        controls.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {     
                controls.setOpaque(true);
                controls.setBackground(Color.LIGHT_GRAY);
            }
            @Override
            public void mouseExited(MouseEvent e) {     
                controls.setOpaque(false);
            }
        });
        controls.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                controls.setOpaque(false);
                ZombieSurvival.start.removeOptionsPanel();
                ZombieSurvival.start.addControls();
            }            
        });
        panel.add(controls);
        
//        JButton credits = new JButton("Credits");        
//        credits.setOpaque(false);
//        credits.setBackground(new Color(0.0f,0.0f,0.0f,0.0f));
//        credits.setBorder(BorderFactory.createEmptyBorder());
//        credits.setFocusPainted(false); 
//        credits.setFont(StartMenu.myFont.deriveFont(Font.BOLD,30f));
//        credits.setPreferredSize(new Dimension(200,50));
//        credits.setBounds((650/2)-(200/2),80,200,50);
//        credits.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseEntered(MouseEvent e) {     
//                credits.setOpaque(true);
//                credits.setBackground(Color.LIGHT_GRAY);
//            }
//            @Override
//            public void mouseExited(MouseEvent e) {     
//                credits.setOpaque(false);
//            }
//        });
//        credits.addActionListener(new ActionListener(){
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                credits.setOpaque(false);
//            }            
//        });
//        panel.add(credits);
        
        JButton cancelButton = new JButton("Back");
        cancelButton.setOpaque(false);
        cancelButton.setBackground(new Color(0.0f,0.0f,0.0f,0.0f));
        cancelButton.setBorder(BorderFactory.createEmptyBorder());
        cancelButton.setFocusPainted(false); 
        cancelButton.setFont(StartMenu.myFont.deriveFont(Font.BOLD,20f));
        cancelButton.setPreferredSize(new Dimension(150,30));    
        cancelButton.setBounds(10,10,150,30);
        cancelButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {   
                cancelButton.setOpaque(true);
                cancelButton.setBackground(Color.LIGHT_GRAY);
            }
            @Override
            public void mouseExited(MouseEvent e) { 
                cancelButton.setOpaque(false);
            }
        });
        cancelButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {  
                ZombieSurvival.start.removeOptionsPanel();
                ZombieSurvival.start.addStartPanel();
            }            
        });
        add(cancelButton);
        
        JButton soundOnOff = new JButton();
        try {
            soundOnOff.setIcon(new ImageIcon(ImageIO.read(getClass().getResource("/res/SoundOn.png"))));
        } catch (IOException ex) {
            Logger.getLogger(OptionsPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        soundOnOff.setOpaque(false);
        soundOnOff.setBackground(new Color(0.0f,0.0f,0.0f,0.0f));
        soundOnOff.setBorder(BorderFactory.createEmptyBorder());
        soundOnOff.setFocusPainted(false); 
        soundOnOff.setFont(StartMenu.myFont.deriveFont(Font.BOLD,20f));
        soundOnOff.setPreferredSize(new Dimension(50,50));    
        soundOnOff.setBounds(775,10,50,50);
        soundOnOff.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {   
                soundOnOff.setOpaque(true);
                soundOnOff.setBackground(Color.LIGHT_GRAY);
            }
            @Override
            public void mouseExited(MouseEvent e) { 
                soundOnOff.setOpaque(false);
            }
        });
        soundOnOff.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {  
                if(ZombieSurvival.allowSound){
                    System.out.println("Off");
                    ZombieSurvival.allowSound = false;
                    try {
                        soundOnOff.setIcon(new ImageIcon(ImageIO.read(getClass().getResource("/res/SoundOff.png"))));
                    } catch (IOException ex) {
                        Logger.getLogger(OptionsPanel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                else if(!ZombieSurvival.allowSound){
                    System.out.println("On");
                    ZombieSurvival.allowSound = true;
                    try {
                        soundOnOff.setIcon(new ImageIcon(ImageIO.read(getClass().getResource("/res/SoundOn.png"))));
                    } catch (IOException ex) {
                        Logger.getLogger(OptionsPanel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }            
        });
        add(soundOnOff);
        
        add(panel);
    }    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D gd = (Graphics2D)g;
        
        try {
            gd.drawImage(ImageIO.read(getClass().getResource("/res/ZombieSurvivalBackground.png")), null, 0, 0);
        } catch (IOException ex) {
            Logger.getLogger(StartMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
