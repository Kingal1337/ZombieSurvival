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
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * 
 * @author Alan Tsui
 * @since 1.0
 * @version 1.0
 */
public class ChangeControlsPanel extends JPanel{
    private JPanel panel;
    private int currentButton;
    private JButton upButton;
    private JButton downButton;
    private JButton leftButton;
    private JButton rightButton;
    private JButton reloadButton;
    public ChangeControlsPanel(){
        currentButton = 0;
        
        setLayout(null); 
        setPreferredSize(new Dimension(850,600));
        setBounds(0,0,850,600);
        
        JLabel title = new JLabel("Controls",SwingConstants.CENTER);
        title.setFont(StartMenu.myFont.deriveFont(Font.BOLD,65f));
        title.setPreferredSize(new Dimension(800,200));
        title.setBounds((850/2)-(800/2),20,800,200);
        add(title);
        
        panel = new JPanel(){
            @Override
            public void paintComponent(Graphics g){
                super.paintComponent(g);
                Graphics2D gd = (Graphics2D)g;
                try {
                    gd.drawImage(ImageIO.read(getClass().getResource("/res/ZombieSurvivalBackground.png")), null, -255, -210);
                } catch (IOException ex) {
                    Logger.getLogger(StartMenu.class.getName()).log(Level.SEVERE, null, ex);
                }                
            }
        };
        panel.setLayout(null);
        panel.setPreferredSize(new Dimension());
        panel.setBounds((850/2)-(340/2),(600/2)-(180/2),340,180);
        System.out.println(((850/2)-(340/2))+" "+((600/2)-(180/2)));
                
        JLabel upButtonLabel = new JLabel("Move Up");
        upButtonLabel.setForeground(Color.WHITE);
        upButtonLabel.setPreferredSize(new Dimension(250,30));
        upButtonLabel.setBounds(5,5,250,30);
        panel.add(upButtonLabel);
        
        JLabel downButtonLabel = new JLabel("Move Down");
        downButtonLabel.setForeground(Color.WHITE);
        downButtonLabel.setPreferredSize(new Dimension(250,30));
        downButtonLabel.setBounds(5,40,250,30);
        panel.add(downButtonLabel);
        
        
        JLabel leftButtonLabel = new JLabel("Move Left");
        leftButtonLabel.setForeground(Color.WHITE);
        leftButtonLabel.setPreferredSize(new Dimension(250,30));
        leftButtonLabel.setBounds(5,75,250,30);
        panel.add(leftButtonLabel);
        
        
        JLabel rightButtonLabel = new JLabel("Move Right");
        rightButtonLabel.setForeground(Color.WHITE);
        rightButtonLabel.setPreferredSize(new Dimension(250,30));
        rightButtonLabel.setBounds(5,110,250,30);
        panel.add(rightButtonLabel);
        
        
        JLabel reloadButtonLabel = new JLabel("Reload Button");
        reloadButtonLabel.setForeground(Color.WHITE);
        reloadButtonLabel.setPreferredSize(new Dimension(250,30));
        reloadButtonLabel.setBounds(5,145,250,30);
        panel.add(reloadButtonLabel);
        
        
        
        upButton = new JButton(KeyEvent.getKeyText(Controls.upButton));
        upButton.setOpaque(false);
        upButton.setBackground(new Color(0.0f,0.0f,0.0f,0.0f));
        upButton.setBorder(BorderFactory.createEmptyBorder());
        upButton.setForeground(Color.DARK_GRAY);
        upButton.setFocusPainted(false); 
        upButton.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e){
                if(currentButton == 1){
                    currentButton = 0;
                    int key = e.getKeyCode();
                    Controls.upButton = key;
                    refresh();
                }
            }
        });
        upButton.setPreferredSize(new Dimension(75,30));
        upButton.setBounds(260,5,75,30);
        panel.add(upButton);
        upButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {   
                upButton.setOpaque(true);
                upButton.setBackground(Color.LIGHT_GRAY);
            }
            @Override
            public void mouseExited(MouseEvent e) { 
                upButton.setOpaque(false);
            }
        });
        upButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                refresh();
                currentButton = 1;
                upButton.setText("");
            }            
        });
        
        downButton = new JButton(KeyEvent.getKeyText(Controls.downButton));
        downButton.setOpaque(false);
        downButton.setBackground(new Color(0.0f,0.0f,0.0f,0.0f));
        downButton.setBorder(BorderFactory.createEmptyBorder());
        downButton.setForeground(Color.DARK_GRAY);
        downButton.setFocusPainted(false); 
        downButton.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e){                
                if(currentButton == 2){
                    currentButton = 0;
                    int key = e.getKeyCode();
                    Controls.downButton = key;
                    refresh();
                }
            }
        });
        downButton.setPreferredSize(new Dimension(75,30));
        downButton.setBounds(260,40,75,30);
        panel.add(downButton);
        downButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {   
                downButton.setOpaque(true);
                downButton.setBackground(Color.LIGHT_GRAY);
            }
            @Override
            public void mouseExited(MouseEvent e) { 
                downButton.setOpaque(false);
            }
        });
        downButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                refresh();
                currentButton = 2;
                downButton.setText("");
            }            
        });
        
        leftButton = new JButton(KeyEvent.getKeyText(Controls.leftButton));
        leftButton.setOpaque(false);
        leftButton.setBackground(new Color(0.0f,0.0f,0.0f,0.0f));
        leftButton.setBorder(BorderFactory.createEmptyBorder());
        leftButton.setForeground(Color.DARK_GRAY);
        leftButton.setFocusPainted(false); 
        leftButton.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e){
                if(currentButton == 3){
                    currentButton = 0;
                    int key = e.getKeyCode();
                    Controls.leftButton = key;
                    refresh();
                }
            }
        });
        leftButton.setPreferredSize(new Dimension(75,30));
        leftButton.setBounds(260,75,75,30);
        panel.add(leftButton);
        leftButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {   
                leftButton.setOpaque(true);
                leftButton.setBackground(Color.LIGHT_GRAY);
            }
            @Override
            public void mouseExited(MouseEvent e) { 
                leftButton.setOpaque(false);
            }
        });
        leftButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                refresh();
                currentButton = 3;
                leftButton.setText("");
            }            
        });
        
        rightButton = new JButton(KeyEvent.getKeyText(Controls.rightButton));
        rightButton.setOpaque(false);
        rightButton.setBackground(new Color(0.0f,0.0f,0.0f,0.0f));
        rightButton.setBorder(BorderFactory.createEmptyBorder());
        rightButton.setForeground(Color.DARK_GRAY);
        rightButton.setFocusPainted(false); 
        rightButton.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e){
                if(currentButton == 4){
                    currentButton = 0;
                    int key = e.getKeyCode();
                    Controls.rightButton = key;
                    refresh();
                }
            }
        });
        rightButton.setPreferredSize(new Dimension(75,30));
        rightButton.setBounds(260,110,75,30);
        panel.add(rightButton);
        rightButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {   
                rightButton.setOpaque(true);
                rightButton.setBackground(Color.LIGHT_GRAY);
            }
            @Override
            public void mouseExited(MouseEvent e) { 
                rightButton.setOpaque(false);
            }
        });
        rightButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                refresh();
                currentButton = 4;
                rightButton.setText("");
            }            
        });
        
        reloadButton = new JButton(KeyEvent.getKeyText(Controls.reloadButton));
        reloadButton.setOpaque(false);
        reloadButton.setBackground(new Color(0.0f,0.0f,0.0f,0.0f));
        reloadButton.setBorder(BorderFactory.createEmptyBorder());
        reloadButton.setForeground(Color.DARK_GRAY);
        reloadButton.setFocusPainted(false);   
        reloadButton.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e){
                if(currentButton == 5){
                    currentButton = 0;
                    int key = e.getKeyCode();
                    Controls.reloadButton = key;
                    refresh();
                }
            }
        });
        reloadButton.setPreferredSize(new Dimension(75,30));
        reloadButton.setBounds(260,145,75,30);
        panel.add(reloadButton);
        reloadButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {   
                reloadButton.setOpaque(true);
                reloadButton.setBackground(Color.LIGHT_GRAY);
            }
            @Override
            public void mouseExited(MouseEvent e) { 
                reloadButton.setOpaque(false);
            }
        });
        reloadButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                refresh();
                currentButton = 5;
                reloadButton.setText("");
            }            
        });
        JButton cancelButton = new JButton("Back");
        cancelButton.setOpaque(false);
        cancelButton.setBackground(new Color(0.0f,0.0f,0.0f,0.0f));
        cancelButton.setBorder(BorderFactory.createEmptyBorder());
        cancelButton.setForeground(Color.DARK_GRAY);
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
                ZombieSurvival.start.removeControls();
                ZombieSurvival.start.addOptionsPanel();
            }            
        });
        add(cancelButton);
        
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
    
    public void refresh(){
        upButton.setText(KeyEvent.getKeyText(Controls.upButton));
        downButton.setText(KeyEvent.getKeyText(Controls.downButton));
        leftButton.setText(KeyEvent.getKeyText(Controls.leftButton));
        rightButton.setText(KeyEvent.getKeyText(Controls.rightButton));
        reloadButton.setText(KeyEvent.getKeyText(Controls.reloadButton));
    }
}
