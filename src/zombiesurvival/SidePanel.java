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
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * 
 * @author Alan Tsui
 * @since 1.0
 * @version 1.0
 */
public class SidePanel extends JPanel{
    private JLabel livesLabel;
    private JLabel ammoLabel;
    private JLabel reloadingLabel;;
    private JLabel scoreLabel;
    
    public SidePanel(){
//        setBackground(new Color(104,57,16));
        
        
        setPreferredSize(new Dimension(150,600));
        setBounds(850,0,150,600);
        
        JLabel livesTextLabel = new JLabel("Lives");
        livesTextLabel.setForeground(Color.BLACK);
        livesTextLabel.setFont(new Font("Gothic",1,18));
        livesTextLabel.setPreferredSize(new Dimension(150,30));
        livesTextLabel.setBounds(850,20,150,30);
        add(livesTextLabel);
        
        livesLabel = new JLabel(ZombieSurvival.mainPane.character.getLives()+"");
        livesLabel.setForeground(Color.BLACK);
        livesLabel.setFont(new Font("Gothic",1,14));
        livesLabel.setPreferredSize(new Dimension(150,30));
        livesLabel.setBounds(850,60,150,30);
        add(livesLabel);
        
        JLabel ammoTextLabel = new JLabel("Ammo");
        ammoTextLabel.setForeground(Color.BLACK);
        ammoTextLabel.setFont(new Font("Gothic",1,18));
        ammoTextLabel.setPreferredSize(new Dimension(150,30));
        ammoTextLabel.setBounds(850,100,150,30);
        add(ammoTextLabel);
        
        ammoLabel = new JLabel(ZombieSurvival.mainPane.character.getWeapon().getCurrentBullets()+"/"+ZombieSurvival.mainPane.character.getWeapon().getMaxMag());
        ammoLabel.setForeground(Color.BLACK);
        ammoLabel.setFont(new Font("Gothic",1,14));
        ammoLabel.setPreferredSize(new Dimension(150,30));
        ammoLabel.setBounds(850,140,150,30);
        add(ammoLabel);
        
        JLabel scoreTextLabel = new JLabel("Score");
        scoreTextLabel.setForeground(Color.BLACK);
        scoreTextLabel.setFont(new Font("Gothic",1,18));
        scoreTextLabel.setPreferredSize(new Dimension(150,30));
        scoreTextLabel.setBounds(850,180,150,30);
        add(scoreTextLabel);
        
        scoreLabel = new JLabel(ZombieSurvival.mainPane.character.getScore()+"");
        scoreLabel.setForeground(Color.BLACK);
        scoreLabel.setFont(new Font("Gothic",1,14));
        scoreLabel.setPreferredSize(new Dimension(150,30));
        scoreLabel.setBounds(850,220,150,30);
        add(scoreLabel);
        
        reloadingLabel = new JLabel("",SwingConstants.CENTER);
        reloadingLabel.setForeground(Color.BLACK);
        reloadingLabel.setFont(new Font("Gothic",1,24));
        reloadingLabel.setPreferredSize(new Dimension(150,30));
        reloadingLabel.setBounds(850,260,150,30);
        add(reloadingLabel);
        
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D gd = (Graphics2D)g;
        
        try {
            gd.drawImage(ImageIO.read(getClass().getResource("/res/ToolBar.png")), null, 0,0);
        } catch (IOException ex) {
            Logger.getLogger(SidePanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void update(){
        livesLabel.setText(ZombieSurvival.mainPane.character.getLives()+"");   
        scoreLabel.setText(ZombieSurvival.mainPane.character.getScore()+"");   
        ammoLabel.setText(ZombieSurvival.mainPane.character.getWeapon().getCurrentBullets()+"/"+ZombieSurvival.mainPane.character.getWeapon().getMaxMag());
        if(ZombieSurvival.mainPane.character.getWeapon().isReloading()){
            reloadingLabel.setText("Reloading...");
        } 
        if(!ZombieSurvival.mainPane.character.getWeapon().isReloading()){
            reloadingLabel.setText("");
        }
    }
}
