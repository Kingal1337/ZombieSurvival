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
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
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
import static zombiesurvival.StartMenu.myFont;
import static zombiesurvival.ZombieSurvival.start;

/**
 * 
 * @author Alan Tsui
 * @since 1.0
 * @version 1.0
 */
public class WeaponSelector extends JPanel{
    private ArrayList<Weapon> weapons;
    private ArrayList<JButton> allJButtons;    
    private JButton laser;        
    private JButton sniper;
    private JButton minigun;
    private JButton pistol;
    private JButton ak47;
    private JButton shotgun;
    private JButton opweapon;
    private JButton OKButton;
    private JButton cancelButton;
    private JPanel panel;
    public WeaponSelector(Character character,ArrayList<Weapon> weapons){
        laser = new JButton();
        sniper = new JButton();
        minigun = new JButton();
        pistol = new JButton();
        ak47 = new JButton();
        shotgun = new JButton();
        opweapon = new JButton();
        setLayout(null);
        allJButtons = new ArrayList<>();
        this.weapons = weapons;
        changeBackground();
        
        panel = new JPanel();        
        panel.setBackground(new Color(0,0,0,0));
        panel.setLayout(null);
        panel.setPreferredSize(new Dimension(650,130));
        panel.setBounds((850/2)-(650/2),(600/2)-(130/2),650,130);
        System.out.println((850/2)-(650/2));
        
        JLabel title = new JLabel("Choose A Weapon",SwingConstants.CENTER);
        title.setFont(StartMenu.myFont.deriveFont(Font.BOLD,65f));
        title.setForeground(Color.DARK_GRAY);
        title.setPreferredSize(new Dimension(800,200));
        title.setBounds((850/2)-(800/2),20,800,200);
        add(title);
        
        
        allJButtons.add(laser);
        allJButtons.add(sniper);
        allJButtons.add(minigun);
        allJButtons.add(pistol);
        allJButtons.add(ak47);
        allJButtons.add(shotgun);
        allJButtons.add(opweapon);
        OKButton = new JButton("Confirm");        
        cancelButton = new JButton("Back");
        add(panel);
        
        makeJButton();
        checkWeapon(character);
        
        laser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                character.setWeapon(weapons.get(0)); 
                laser.setBackground(Color.YELLOW);   
                sniper.setBackground(Color.WHITE);   
                minigun.setBackground(Color.WHITE);  
                pistol.setBackground(Color.WHITE);  
                ak47.setBackground(Color.WHITE);   
                shotgun.setBackground(Color.WHITE);   
                opweapon.setBackground(Color.WHITE);    
            }
        });
        sniper.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                character.setWeapon(weapons.get(1));     
                laser.setBackground(Color.WHITE);   
                sniper.setBackground(Color.YELLOW);   
                minigun.setBackground(Color.WHITE);  
                pistol.setBackground(Color.WHITE);  
                ak47.setBackground(Color.WHITE);   
                shotgun.setBackground(Color.WHITE); 
                opweapon.setBackground(Color.WHITE);    
            }
        });
        minigun.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                character.setWeapon(weapons.get(2));    
                laser.setBackground(Color.WHITE);   
                sniper.setBackground(Color.WHITE);   
                minigun.setBackground(Color.YELLOW);  
                pistol.setBackground(Color.WHITE);  
                ak47.setBackground(Color.WHITE);   
                shotgun.setBackground(Color.WHITE); 
                opweapon.setBackground(Color.WHITE);    
            }
        });
        pistol.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                character.setWeapon(weapons.get(3));   
                laser.setBackground(Color.WHITE);   
                sniper.setBackground(Color.WHITE);   
                minigun.setBackground(Color.WHITE);  
                pistol.setBackground(Color.YELLOW);  
                ak47.setBackground(Color.WHITE);   
                shotgun.setBackground(Color.WHITE); 
                opweapon.setBackground(Color.WHITE);    
            }
        });
        ak47.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                character.setWeapon(weapons.get(4));  
                laser.setBackground(Color.WHITE);   
                sniper.setBackground(Color.WHITE);   
                minigun.setBackground(Color.WHITE);  
                pistol.setBackground(Color.WHITE);  
                ak47.setBackground(Color.YELLOW);   
                shotgun.setBackground(Color.WHITE); 
                opweapon.setBackground(Color.WHITE);         
            }
        });
        shotgun.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                character.setWeapon(weapons.get(5));     
                laser.setBackground(Color.WHITE);   
                sniper.setBackground(Color.WHITE);   
                minigun.setBackground(Color.WHITE);  
                pistol.setBackground(Color.WHITE);  
                ak47.setBackground(Color.WHITE);   
                shotgun.setBackground(Color.YELLOW); 
                opweapon.setBackground(Color.WHITE);      
            }
        });  
        opweapon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                character.setWeapon(weapons.get(6));     
                laser.setBackground(Color.WHITE);   
                sniper.setBackground(Color.WHITE);   
                minigun.setBackground(Color.WHITE);  
                pistol.setBackground(Color.WHITE);  
                ak47.setBackground(Color.WHITE);   
                shotgun.setBackground(Color.WHITE); 
                opweapon.setBackground(Color.YELLOW);      
            }
        });  
        OKButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {  
                ZombieSurvival.survive.gameStarter(character);
                ZombieSurvival.start.removeWeaponSelector();
                ZombieSurvival.start.addStartPanel();
                start.setVisible(false);
            }
            
        });
        cancelButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {  
                ZombieSurvival.start.removeWeaponSelector();
                ZombieSurvival.start.addStartPanel();
            }            
        });
        
        
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
    public void makeJButton(){
        int x = 10;
        for(int i=0;i<allJButtons.size();i++){
            allJButtons.get(i).setIcon(new ImageIcon(weapons.get(i).getWeaponImage()));
            allJButtons.get(i).setPreferredSize(new Dimension(80,53));
            allJButtons.get(i).setBounds(x,5,80,53);
            allJButtons.get(i).setOpaque(true);
            allJButtons.get(i).setBackground(Color.WHITE);
            allJButtons.get(i).setBorder(BorderFactory.createLineBorder(Color.BLACK));
            allJButtons.get(i).setFocusPainted(false);
            panel.add(allJButtons.get(i));
            x+=90;
        }
        
        OKButton.setOpaque(false);
        OKButton.setBackground(new Color(0.0f,0.0f,0.0f,0.0f));
        OKButton.setBorder(BorderFactory.createEmptyBorder());
        OKButton.setFocusPainted(false); 
        OKButton.setForeground(Color.DARK_GRAY);
        OKButton.setFont(myFont.deriveFont(Font.BOLD,20f));
        OKButton.setPreferredSize(new Dimension(150,30));    
        OKButton.setBounds(690,10,150,30);
        OKButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {   
                OKButton.setOpaque(true);
                OKButton.setBackground(Color.LIGHT_GRAY);
            }
            @Override
            public void mouseExited(MouseEvent e) { 
                OKButton.setOpaque(false);
            }
        });
        add(OKButton);
        
        cancelButton.setOpaque(false);
        cancelButton.setBackground(new Color(0.0f,0.0f,0.0f,0.0f));
        cancelButton.setBorder(BorderFactory.createEmptyBorder());
        cancelButton.setFocusPainted(false); 
        cancelButton.setForeground(Color.DARK_GRAY);
        cancelButton.setFont(myFont.deriveFont(Font.BOLD,20f));
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
        add(cancelButton);
    }
    public void changeBackground(){  
        laser.setBackground(Color.WHITE);   
        sniper.setBackground(Color.WHITE);   
        minigun.setBackground(Color.WHITE);  
        pistol.setBackground(Color.WHITE);  
        ak47.setBackground(Color.WHITE);   
        shotgun.setBackground(Color.WHITE); 
        opweapon.setBackground(Color.WHITE);          
    }
    public void checkWeapon(Character character){
        String weapon = character.getWeapon().getItemName();
        System.out.println(weapon);
        if(weapon.equalsIgnoreCase("laser")){            
            laser.setBackground(Color.YELLOW);
        } 
        else if (weapon.equalsIgnoreCase("sniper")) {
            sniper.setBackground(Color.YELLOW);
        } 
        else if (weapon.equalsIgnoreCase("mini gun")) {
            minigun.setBackground(Color.YELLOW);
        } 
        else if (weapon.equalsIgnoreCase("9mm")) {
            pistol.setBackground(Color.YELLOW);
        } 
        else if (weapon.equalsIgnoreCase("AK-47")) {
            ak47.setBackground(Color.YELLOW);
        } 
        else if (weapon.equalsIgnoreCase("shotgun")) {
            shotgun.setBackground(Color.YELLOW);
        }
        else if (weapon.equalsIgnoreCase("OP-WEAPON")) {
            opweapon.setBackground(Color.YELLOW);
        }
    }
}
