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
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import static zombiesurvival.OptionsPanel.controlPanel;

/**
 * 
 * @author Alan Tsui
 * @since 1.0
 * @version 1.0
 */
public class StartMenu extends JFrame{
    public static OptionsPanel optionsPanel;
    public static JPanel mainPanel;
    public static Font zombieFont;    
    public static Font myFont;
    public static WeaponSelector weaponSelector;
    public StartMenu(){
        setTitle("Zombie Survival");
        setPreferredSize(new Dimension(850,600));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        pack();
        setLocationRelativeTo(null);       
        
        try {
            setIconImage(ImageIO.read(getClass().getResource("/res/ZombieSurvivalLogo.png")));
        } catch (IOException ex) {
            Logger.getLogger(StartMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        InputStream fnt_stream = getClass().getResourceAsStream("/res/TheDeadAreComing.ttf");
        try {
            myFont = Font.createFont(Font.TRUETYPE_FONT, fnt_stream);
        } catch (FontFormatException ex) {
            Logger.getLogger(StartMenu.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(StartMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
        zombieFont = myFont.deriveFont(Font.BOLD, 65f);
        
        mainPanel = new JPanel(){
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
        };
        mainPanel.setLayout(null);
        
        Fader fader = new Fader(new ImageIcon(StartMenu.class.getClassLoader().getResource("res/bearian-inc-logo.png")), 20, 2500);
        
        
        JLabel label = new JLabel("Zombie Survival",SwingConstants.CENTER);
        label.setFont(zombieFont);
        label.setForeground(Color.DARK_GRAY);
        label.setPreferredSize(new Dimension(850,100));
        label.setBounds(0,50,850,100);
        mainPanel.add(label);
        
        JButton playButton = new JButton("Play");        
        playButton.setOpaque(false);
        playButton.setBackground(new Color(0.0f,0.0f,0.0f,0.0f));
        playButton.setBorder(BorderFactory.createEmptyBorder());
        playButton.setForeground(Color.DARK_GRAY);
        playButton.setFocusPainted(false); 
        playButton.setFont(myFont.deriveFont(Font.BOLD,30f));
        playButton.setPreferredSize(new Dimension(200,50));
        playButton.setBounds(50,200,200,50);
        playButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {     
                playButton.setOpaque(true);
                playButton.setBackground(Color.LIGHT_GRAY);
            }
            @Override
            public void mouseExited(MouseEvent e) {     
                playButton.setOpaque(false);
            }
        });
        playButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                playButton.setOpaque(false);
                try {
                    ZombieSurvival.survive.preStarter();
                    preGameStarter(4,8,5,200,ZombieSurvival.weapons.get(0));
                } catch (IOException ex) {
                    Logger.getLogger(ZombieSurvival.class.getName()).log(Level.SEVERE, null, ex);
                }                
            }            
        });
        mainPanel.add(playButton);
        
        JButton optionsButton = new JButton("Options");        
        optionsButton.setOpaque(false);
        optionsButton.setBackground(new Color(0.0f,0.0f,0.0f,0.0f));
        optionsButton.setBorder(BorderFactory.createEmptyBorder());
        optionsButton.setForeground(Color.DARK_GRAY);
        optionsButton.setFocusPainted(false); 
        optionsButton.setFont(myFont.deriveFont(Font.BOLD,30f));
        optionsButton.setPreferredSize(new Dimension(200,50));
        optionsButton.setBounds(50,260,200,50);
        optionsButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {   
                optionsButton.setOpaque(true);
                optionsButton.setBackground(Color.LIGHT_GRAY);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                optionsButton.setOpaque(false);
            }
        });
        optionsButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                optionsButton.setOpaque(false);
                removeStartPanel();
                addOptionsPanel();                
            }            
        });
        mainPanel.add(optionsButton);
        
        JButton quitButton = new JButton("Quit");        
        quitButton.setOpaque(false);
        quitButton.setBackground(new Color(0.0f,0.0f,0.0f,0.0f));
        quitButton.setBorder(BorderFactory.createEmptyBorder());
        quitButton.setForeground(Color.DARK_GRAY);
        quitButton.setFocusPainted(false); 
        quitButton.setFont(myFont.deriveFont(Font.BOLD,30f));
        quitButton.setPreferredSize(new Dimension(200,50));
        quitButton.setBounds(50,320,200,50);
        quitButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {   
                quitButton.setOpaque(true);
                quitButton.setBackground(Color.LIGHT_GRAY);
            }
            @Override
            public void mouseExited(MouseEvent e) { 
                quitButton.setOpaque(false);
            }
        });
        quitButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                quitButton.setOpaque(false);
                System.exit(0);
            }            
        });
        mainPanel.add(quitButton);
        
        JLabel credits = new JLabel("Created By Alan tsui");
        credits.setFont(myFont.deriveFont(Font.BOLD,18f));
        credits.setForeground(Color.DARK_GRAY);
        credits.setPreferredSize(new Dimension(300,25));
        credits.setBounds(20,530,300,25);
        mainPanel.add(credits);
        
        add(fader);
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                while(true){
                    if(fader.isComplete()){
                        changePanel(fader, mainPanel);
                        break;
                    }
                    try {
                        Thread.sleep(20);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(StartMenu.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
        setVisible(true);
    }
    public void changePanel(JPanel pre , JPanel panel){
        remove(pre);
        revalidate();
        repaint();
        add(panel);
        revalidate();
        repaint();
    }
    public void preGameStarter(int damage, int speed, int lives, int lineCannotCross,Weapon weapon) throws IOException{    
        removeStartPanel();
        Character character = new Character(50,50,30,30,damage,speed,lives,ImageIO.read(getClass().getResource("/res/StickMan.png")),lineCannotCross,weapon,null);  
        weaponSelector = new WeaponSelector(character,ZombieSurvival.weapons);    
        weaponSelector.setPreferredSize(new Dimension(850,600));
        weaponSelector.setBounds(0,0,850,600);
        add(weaponSelector);
        repaint();
    }
    public void addOptionsPanel(){ 
        optionsPanel = new OptionsPanel();
        add(optionsPanel);
        repaint();
    }
    public void removeOptionsPanel(){
        remove(optionsPanel);
        repaint();
    }
    public void removeWeaponSelector(){
        remove(weaponSelector);   
        repaint();
    }
    public void removeStartPanel(){
        remove(mainPanel);
    }
    public void addStartPanel(){
        add(mainPanel);
    }
    public void removeControls(){
        remove(OptionsPanel.controlPanel);
        repaint();
    }
    public void addControls(){
        controlPanel = new ChangeControlsPanel();
        add(OptionsPanel.controlPanel);
        repaint();        
    }
}
