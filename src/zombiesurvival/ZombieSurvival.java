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

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFrame;

/**
 * 
 * @author Alan Tsui
 * @since 1.0
 * @version 1.0
 */
public class ZombieSurvival {
    public static ZombieSurvival survive = new ZombieSurvival();
    public static JFrame mainFrame;
    public static MainFrame mainPane;
    public static SidePanel sidePanel;
    public static ArrayList<Weapon> weapons;
    public static StartMenu start;
    public static boolean allowSound;
    public static void main(String[] args) {
        allowSound = true;
        try {
            survive.preStarter();
        } catch (IOException ex) {
            Logger.getLogger(ZombieSurvival.class.getName()).log(Level.SEVERE, null, ex);
        }
        start = new StartMenu();
    }
    public void preStarter() throws IOException{  
        weapons = new ArrayList<>();
        Weapon laser = new Weapon("Laser",ImageIO.read(getClass().getResource("/res/Weapons/laser.png")),2000,.01,2000,.01,false,5,5,5,"/res/sounds/laserSound.wav");
        Weapon sniper = new Weapon("Sniper",ImageIO.read(getClass().getResource("/res/Weapons/sniper.png")),5,.01,5,1,false,10,5000,5000,"/res/sounds/sniperSound.wav");
        Weapon miniGun = new Weapon("Mini Gun",ImageIO.read(getClass().getResource("/res/Weapons/minigun.png")),300,.1,300,.5,false,30,15,15,"/res/sounds/miniGunSound.wav");
        Weapon pistol = new Weapon("9mm",ImageIO.read(getClass().getResource("/res/Weapons/pistol.png")),36,1,36,2,false,10,100,100,"/res/sounds/pistol.wav");
        Weapon ak47 = new Weapon("AK-47",ImageIO.read(getClass().getResource("/res/Weapons/ak47.png")),100,.2,100,1,false,30,100,100,"/res/sounds/ak47Sound.wav");
        Weapon shotgun = new Weapon("Shotgun",ImageIO.read(getClass().getResource("/res/Weapons/shotgun.png")),16,.5,16,.2,false,10,20,20,"/res/sounds/shotgunSound.wav");
        Weapon OPWEAPON = new Weapon("OP-WEAPON",ImageIO.read(getClass().getResource("/res/Weapons/OPWEAPONIMAGE.png")),100,.2,100,.1,true,50,1000,1000,"/res/sounds/OPWEAPONSound.wav");        
        
        weapons.add(laser);
        weapons.add(sniper);
        weapons.add(miniGun);
        weapons.add(pistol);  
        weapons.add(ak47);  
        weapons.add(shotgun);  
        weapons.add(OPWEAPON);  
        
    }
    public void gameStarter(Character character){        
        ArrayList spawningPlatform = generateSpawningPlatforms(5,character);
        try {
            realGameStarter(character,spawningPlatform);
        } catch (IOException ex) {
            Logger.getLogger(ZombieSurvival.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }
    public void realGameStarter(Character character,ArrayList<MobSpawningPlatform> spawningPlat) throws IOException{
        mainFrame = new JFrame("Zombie Survival");
        mainPane = new MainFrame(character,spawningPlat);
        
        try {
            mainFrame.setIconImage(ImageIO.read(getClass().getResource("/res/ZombieSurvivalLogo.png")));
        } catch (IOException ex) {
            Logger.getLogger(StartMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        mainFrame.setLayout(null);
        mainFrame.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
        
        sidePanel = new SidePanel();
        mainFrame.add(sidePanel);
        
        mainPane.setPreferredSize(new Dimension(850,600));
        mainPane.setBounds(0,0,850,600);
        
        mainFrame.add(mainPane);
        mainFrame.setVisible(true);
        mainFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        mainFrame.setPreferredSize(new Dimension(1000,600));
        mainFrame.setResizable(false);
        mainFrame.pack();
        mainFrame.setLocationRelativeTo(null);
        mainFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e){
                mainFrameClosing();
            }
        });
    }
    public void mainFrameClosing(){
        mainPane.stop();
        mainFrame.dispose();
        start.setVisible(true);
        start.requestFocus();        
    }
    
    private ArrayList<MobSpawningPlatform> generateSpawningPlatforms(int amount,Character character){
        ArrayList<MobSpawningPlatform> spawningPlatforms = new ArrayList<>();
        Random r = new Random();   
        BufferedImage spawningPlatformImage = null;
        try {
            spawningPlatformImage = ImageIO.read(getClass().getResource("/res/SpawningPortal.png"));
        } catch (IOException ex) {
            Logger.getLogger(ZombieSurvival.class.getName()).log(Level.SEVERE, null, ex);
        }
        for(int i=0;i<amount;i++){
            int spawningType = r.nextInt(4 - 1) + 1;
            int spawningTime = r.nextInt(21 - 3) + 3;
            int randomX = r.nextInt(700-(character.getLineCannotCross())-character.getLineCannotCross()+150) + character.getLineCannotCross()+150;
            int randomY = r.nextInt(500 - 50) + 50;   
            
            spawningPlatforms.add(new MobSpawningPlatform(randomX, randomY, 50, 50, spawningTime, spawningType, spawningPlatformImage));
        }
        return spawningPlatforms;
    }
    
//        spawningPlatform.add(new MobSpawningPlatform(650, 50, 50, 50, 12, 1, ImageIO.read(getClass().getResource("/res/SpawningPortal.png"))));
//        spawningPlatform.add(new MobSpawningPlatform(650, 50, 50, 50, 12, 1, ImageIO.read(getClass().getResource("/res/SpawningPortal.png"))));
//        spawningPlatform.add(new MobSpawningPlatform(700, 300, 50, 50, 10, 1, ImageIO.read(getClass().getResource("/res/SpawningPortal.png"))));
//        spawningPlatform.add(new MobSpawningPlatform(500, 150, 50, 50, 5, 1, ImageIO.read(getClass().getResource("/res/SpawningPortal.png"))));
//        spawningPlatform.add(new MobSpawningPlatform(640, 230, 50, 50, 7, 1, ImageIO.read(getClass().getResource("/res/SpawningPortal.png"))));
//        spawningPlatform.add(new MobSpawningPlatform(565, 465, 50, 50, 15, 1, ImageIO.read(getClass().getResource("/res/SpawningPortal.png"))));
//        spawningPlatform.add(new MobSpawningPlatform(654, 485, 50, 50, 6, 1, ImageIO.read(getClass().getResource("/res/SpawningPortal.png"))));
//        spawningPlatform.add(new MobSpawningPlatform(456, 434, 50, 50, 9, 1, ImageIO.read(getClass().getResource("/res/SpawningPortal.png"))));
//        spawningPlatform.add(new MobSpawningPlatform(433, 350, 50, 50, 20, 1, ImageIO.read(getClass().getResource("/res/SpawningPortal.png"))));
//        spawningPlatform.add(new MobSpawningPlatform(544, 348, 50, 50, 23, 1, ImageIO.read(getClass().getResource("/res/SpawningPortal.png"))));      
    
}
