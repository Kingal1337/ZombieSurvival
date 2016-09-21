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
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

/**
 * 
 * @author Alan Tsui
 * @since 1.0
 * @version 1.0
 */
public class MainFrame extends JPanel implements ActionListener,KeyListener,MouseListener,MouseMotionListener{    
    public static Character character;
    private ArrayList<MobSpawningPlatform> spawningPlatforms;
    private Timer timer;
    private JPanel pausePanel;
        
    public static int tick;
    public static final int TICK = 20;
    public static final int FPS = 1000/TICK;  
    
    private boolean paused;
    
    private ArrayList<PowerUps> onScreenPowerUps;
    private ArrayList<PowerUps> offScreenPowerUps;
        
    public MainFrame(Character character,ArrayList<MobSpawningPlatform> spawningPlatforms){
        tick = 1;
        paused = false;
        onScreenPowerUps = new ArrayList<>();
        offScreenPowerUps = new ArrayList<>();
        this.setBackground(new Color(228,221,181));
        setLayout(null);
        
        pausePanel = new PauseMenu();
        pausePanel.setPreferredSize(new Dimension(200,100));
        pausePanel.setBackground(Color.BLACK);
        pausePanel.setBounds((850/2)-(200/2),(550/2)-(100/2),200,100);
        add(pausePanel);
        pausePanel.setVisible(false);
        
        timer = new Timer(TICK,this);
        this.character = character;
        this.spawningPlatforms = spawningPlatforms;
        addMouseListener(this);
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        addMouseMotionListener(this);
        timer.start();
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D gd = (Graphics2D)g;
        
        gd.setColor(Color.BLACK);
        
        gd.fill(new Rectangle(-40,0,50,850));
        gd.fill(new Rectangle(0,-40,850,50));
        gd.fill(new Rectangle(0,561,1000,50));
        gd.fill(new Rectangle(840,0,10,1000));
                
        drawEnemies(gd,spawningPlatforms);
        drawSpawningPlatform(gd,spawningPlatforms);
        checkEnemies(spawningPlatforms);
        
        character.update(gd);
        
        if(character.getLives() < 1){
            timer.stop();
//            gameOverLabel.setVisible(true);
            String gameOver = "GAME OVER";
            AffineTransform affinetransform = new AffineTransform();     
            FontRenderContext frc = new FontRenderContext(affinetransform,true,true);     
            Font font = new Font("Gothic", Font.BOLD, 72);
            int textWidth = (int)(font.getStringBounds(gameOver, frc).getWidth());
            int textHeight = (int)(font.getStringBounds(gameOver, frc).getHeight());
//            System.out.println("Text "+textWidth+" "+textHeight);
            gd.setFont(font);
            gd.setColor(Color.BLACK);
            gd.drawString(gameOver, (850/2)-(textWidth/2), (600/2)-(textHeight/2)+25);
        }
        generatePowerUp();
        character.checkPowerUp(gd,onScreenPowerUps,offScreenPowerUps);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        tick++;        
        ZombieSurvival.sidePanel.update();
        repaint();
    }
    
    private void generatePowerUp(){
        Random r = new Random();
        int chance = r.nextInt(3000 - 0)+0;
        if(chance >= 0 && chance <= 1){
            int randomType = r.nextInt(4 - 1)+1;
//            int randomType = 2;
            int randomX = r.nextInt(800-(character.getLineCannotCross())-character.getLineCannotCross()+50) + character.getLineCannotCross()+50;
            int randomY = r.nextInt(500 - 50) + 50;
            if(randomType == 1){
                try {
                    PowerUps powerup = new PowerUps(randomX, randomY, 50, 50, ImageIO.read(getClass().getResource("/res/PowerUps/PowerUpLife1.png")), randomType,false);
                    onScreenPowerUps.add(powerup);
                } catch (IOException ex) {
                    Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(randomType == 2){
                try {
                    PowerUps powerup = new PowerUps(randomX, randomY, 50, 50, ImageIO.read(getClass().getResource("/res/PowerUps/PowerUpInfiniteBullet.png")), randomType,false);
                    onScreenPowerUps.add(powerup);
                } catch (IOException ex) {
                    Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(randomType == 3){
                try {
                    PowerUps powerup = new PowerUps(randomX, randomY, 50, 50, ImageIO.read(getClass().getResource("/res/PowerUps/PowerUpStrength.png")), randomType,false);
                    onScreenPowerUps.add(powerup);
                } catch (IOException ex) {
                    Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    private void checkEnemies(ArrayList<MobSpawningPlatform> spawn){
        for(int i=0;i<spawn.size();i++){
            for(int j=0;j<spawn.get(i).getEnemies().size();j++){
                if(spawn.get(i).getEnemies().get(j).getX() < 0-spawn.get(i).getEnemies().get(j).getWidth()){
                    spawn.get(i).getEnemies().remove(j);
//                    System.out.println("Test");
                    character.setLives(character.getLives()-1);
                }
            }
        }
    }
    
    private void drawEnemies(Graphics2D gd,ArrayList<MobSpawningPlatform> spawn){
        for(int i=0;i<spawn.size();i++){
            for(int j=0;j<spawn.get(i).getEnemies().size();j++){
                //gd.drawRect((int)spawn.get(i).getEnemies().get(j).getX(), (int)spawn.get(i).getEnemies().get(j).getY(), spawn.get(i).getEnemies().get(j).getWidth(), spawn.get(i).getEnemies().get(j).getHeight());
                spawn.get(i).getEnemies().get(j).update(gd);
                character.checkBulletIntersecting(spawn.get(i).getEnemies());
            }
        }
    }
    private void drawSpawningPlatform(Graphics2D gd,ArrayList<MobSpawningPlatform> spawn){
        for(int i=0;i<spawn.size();i++){
            spawn.get(i).update(gd);
        }
    }
    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if(key == Controls.upButton){
            character.setUp(true);            
        }
        if(key == Controls.downButton){
            character.setDown(true);            
        }
        if(key == Controls.leftButton){
            character.setLeft(true);            
        }
        if(key == Controls.rightButton){
            character.setRight(true);
        }
        if(key == Controls.reloadButton){
            character.reload();
        }
        if(key == KeyEvent.VK_ESCAPE){
            if(!paused){
                paused = true;
                pausePanel.setVisible(true);
                timer.stop();
            }
            else{
                resume();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if(key == Controls.upButton){
            character.setUp(false);            
        }
        if(key == Controls.downButton){
            character.setDown(false);            
        }
        if(key == Controls.leftButton){
            character.setLeft(false);            
        }
        if(key == Controls.rightButton){
            character.setRight(false);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int x = 0;
        int y = 0;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        character.getWeapon().setShooting(true);        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        character.getWeapon().setShooting(false);          
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        character.setMouseX(x);
        character.setMouseY(y);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        character.setMouseX(x);
        character.setMouseY(y);
    } 
    
    public void stop(){
        timer.stop();
    }
    
    public void resume(){
        paused = false;
        pausePanel.setVisible(false);
        timer.start();              
    }
    
}
