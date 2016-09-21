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

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import static zombiesurvival.MainFrame.FPS;
import static zombiesurvival.MainFrame.tick;

/**
 * 
 * @author Alan Tsui
 * @since 1.0
 * @version 1.0
 */
public class Weapon extends Items{
    private int currentBullets;
    private int maxMag;
    private double shotBetweenFire;
    private boolean infiniteBullets;
    private int bulletSpeed;
    private int damage;
    private int defaultDamage;
    private boolean shooting;
    private boolean shoot;
    private double reloadSpeed;
    private boolean reloading;
    private boolean defaultInfiniteBullets;

    public Weapon(String itemName,BufferedImage weaponImage,int maxMag, double shotBetweenFire, int currentBullets,double reloadSpeed,boolean infiniteBullets,int bulletSpeed,int damage, int defaultDamage,String sound) {
        super(itemName,weaponImage,sound);
        this.maxMag = maxMag;
        this.shotBetweenFire = shotBetweenFire;
        this.currentBullets = currentBullets;
        this.infiniteBullets = infiniteBullets;
        this.bulletSpeed = bulletSpeed;
        this.damage = damage;
        shooting = false;
        shoot = true;
        this.reloadSpeed = reloadSpeed;
        reloading = false;
        this.defaultDamage = defaultDamage;
        defaultInfiniteBullets = infiniteBullets;
    }
    public void shootBullets(ArrayList<Bullet> bullets,double currAngle,double x,double y){
        System.out.println("Duh Angle "+currAngle);
        if(currentBullets > 0 || infiniteBullets){
            if(ZombieSurvival.allowSound){
                try {
                    SoundClass.sound.playAudio(getSound());
                } catch (Exception ex) {
                    Logger.getLogger(Weapon.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            Random r = new Random();
            int randomCrit = r.nextInt(1000 - 2) - 2;
            Bullet bullet = null;
            Bullet bullet1 = null;
            Bullet bullet2 = null;
            Bullet bullet3 = null;
            Bullet bullet4 = null;
            Bullet bullet5 = null;
            Bullet bullet6 = null;
            if(getItemName().equalsIgnoreCase("shotgun")){
                if(randomCrit == 555){
                    damage+=100;
//                    try {
//                        double angle1;
//                        double angle2;
//                        double angle3;
//                        double currangle;
//                        double angle4;
//                        double angle5;
//                        double angle6;
//                        double tempAngle = Math.toDegrees(currAngle);
//                        angle1 = Math.toRadians(tempAngle + 30);
//                        angle2 = Math.toRadians(tempAngle + 20);
//                        angle3 = Math.toRadians(tempAngle + 10);
//                        currangle = currAngle;
//                        angle4 = Math.toRadians(tempAngle - 10);
//                        angle5 = Math.toRadians(tempAngle - 20);
//                        angle6 = Math.toRadians(tempAngle - 30);
//                        
//                        bullet = new Bullet(x+5/2,y+5/2,5,5,damage+100,bulletSpeed,ImageIO.read(getClass().getResource("/res/Bullet.png")),angle1-Math.PI/2,randomCrit,getSound());
//                        bullet1 = new Bullet(x+5/2,y+5/2,5,5,damage+100,bulletSpeed,ImageIO.read(getClass().getResource("/res/Bullet.png")),angle2-Math.PI/2,randomCrit,getSound());
//                        bullet2 = new Bullet(x+5/2,y+5/2,5,5,damage+100,bulletSpeed,ImageIO.read(getClass().getResource("/res/Bullet.png")),angle3-Math.PI/2,randomCrit,getSound());
//                        bullet3 = new Bullet(x+5/2,y+5/2,5,5,damage+100,bulletSpeed,ImageIO.read(getClass().getResource("/res/Bullet.png")),currangle-Math.PI/2,randomCrit,getSound());
//                        bullet4 = new Bullet(x+5/2,y+5/2,5,5,damage+100,bulletSpeed,ImageIO.read(getClass().getResource("/res/Bullet.png")),angle4-Math.PI/2,randomCrit,getSound());
//                        bullet5 = new Bullet(x+5/2,y+5/2,5,5,damage+100,bulletSpeed,ImageIO.read(getClass().getResource("/res/Bullet.png")),angle5-Math.PI/2,randomCrit,getSound());
//                        bullet6 = new Bullet(x+5/2,y+5/2,5,5,damage+100,bulletSpeed,ImageIO.read(getClass().getResource("/res/Bullet.png")),angle6-Math.PI/2,randomCrit,getSound());
//                    } catch (IOException ex) {
//                        Logger.getLogger(Weapon.class.getName()).log(Level.SEVERE, null, ex);
//                    }                
                }
                try {
                    double angle1;
                    double angle2;
                    double angle3;
                    double currangle;
                    double angle4;
                    double angle5;
                    double angle6;
                    double tempAngle = Math.toDegrees(currAngle);
                    angle1 = Math.toRadians(tempAngle + 30);
                    angle2 = Math.toRadians(tempAngle + 20);
                    angle3 = Math.toRadians(tempAngle + 10);
                    currangle = currAngle;
                    angle4 = Math.toRadians(tempAngle - 10);
                    angle5 = Math.toRadians(tempAngle - 20);
                    angle6 = Math.toRadians(tempAngle - 30);

                    bullet = new Bullet(x+5/2,y+5/2,5,5,damage,bulletSpeed,ImageIO.read(getClass().getResource("/res/Bullet.png")),angle1-Math.PI/2,randomCrit,getSound());
                    bullet1 = new Bullet(x+5/2,y+5/2,5,5,damage,bulletSpeed,ImageIO.read(getClass().getResource("/res/Bullet.png")),angle2-Math.PI/2,randomCrit,getSound());
                    bullet2 = new Bullet(x+5/2,y+5/2,5,5,damage,bulletSpeed,ImageIO.read(getClass().getResource("/res/Bullet.png")),angle3-Math.PI/2,randomCrit,getSound());
                    bullet3 = new Bullet(x+5/2,y+5/2,5,5,damage,bulletSpeed,ImageIO.read(getClass().getResource("/res/Bullet.png")),currangle-Math.PI/2,randomCrit,getSound());
                    bullet4 = new Bullet(x+5/2,y+5/2,5,5,damage,bulletSpeed,ImageIO.read(getClass().getResource("/res/Bullet.png")),angle4-Math.PI/2,randomCrit,getSound());
                    bullet5 = new Bullet(x+5/2,y+5/2,5,5,damage,bulletSpeed,ImageIO.read(getClass().getResource("/res/Bullet.png")),angle5-Math.PI/2,randomCrit,getSound());
                    bullet6 = new Bullet(x+5/2,y+5/2,5,5,damage,bulletSpeed,ImageIO.read(getClass().getResource("/res/Bullet.png")),angle6-Math.PI/2,randomCrit,getSound());
                } catch (IOException ex) {
                    Logger.getLogger(Weapon.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
            else{
                if(randomCrit == 555){
                    damage+=100;
//                    try {
//                        bullet = new Bullet(x+5/2,y+5/2,5,5,damage+100,bulletSpeed,ImageIO.read(getClass().getResource("/res/Bullet.png")),currAngle-Math.PI/2,randomCrit,getSound());
//                    } catch (IOException ex) {
//                        Logger.getLogger(Weapon.class.getName()).log(Level.SEVERE, null, ex);
//                    }                
                }
                    try {
                        bullet = new Bullet(x+5/2,y+5/2,5,5,damage,bulletSpeed,ImageIO.read(getClass().getResource("/res/Bullet.png")),currAngle-Math.PI/2,randomCrit,getSound());
                    } catch (IOException ex) {
                        Logger.getLogger(Weapon.class.getName()).log(Level.SEVERE, null, ex);
                    }
                
            }
            bullet.setX(bullet.getX()-bullet.getWidth()/2);
            bullet.setY(bullet.getY()-bullet.getHeight()/2);
            if(infiniteBullets == false){
                shoot = false;
                currentBullets--;
            }
            if(getItemName().equalsIgnoreCase("shotgun")){
                bullets.add(bullet);   
                bullets.add(bullet1);   
                bullets.add(bullet2);        
                bullets.add(bullet3);   
                bullets.add(bullet4);       
                bullets.add(bullet5);   
                bullets.add(bullet6);                  
            }
            else{
                bullets.add(bullet);                
            }
        }    
    }
    
    public void update(){
        if(infiniteBullets == false){
            if(tick%(FPS*shotBetweenFire)==0){     
                shoot = true;
            }
            if(shooting){
                reloading = false;
            }
            
            if(tick%(FPS*reloadSpeed)==0 && reloading){
                currentBullets++;  
//                reloading = false;
            }
            if(currentBullets == maxMag){
                reloading = false;
            }
        }
    }
    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public boolean isShooting() {
        return shooting;
    }

    public void setShooting(boolean shooting) {
        this.shooting = shooting;
    }

    public boolean canShoot() {
        return shoot;
    }

    public void setShoot(boolean shoot) {
        this.shoot = shoot;
    }

    public boolean isReloading() {
        return reloading;
    }

    public void setReloading(boolean reloading) {
        this.reloading = reloading;
    }

    public int getCurrentBullets() {
        return currentBullets;
    }

    public void setCurrentBullets(int currentBullets) {
        this.currentBullets = currentBullets;
    }

    public int getMaxMag() {
        return maxMag;
    }

    public void setMaxMag(int maxMag) {
        this.maxMag = maxMag;
    }

    public boolean isInfiniteBullets() {
        return infiniteBullets;
    }

    public void setInfiniteBullets(boolean infiniteBullets) {
        this.infiniteBullets = infiniteBullets;
    }

    public int getDefaultDamage() {
        return defaultDamage;
    }

    public void setDefaultDamage(int defaultDamage) {
        this.defaultDamage = defaultDamage;
    }

    public boolean isDefaultInfiniteBullets() {
        return defaultInfiniteBullets;
    }

    public void setDefaultInfiniteBullets(boolean defaultInfiniteBullets) {
        this.defaultInfiniteBullets = defaultInfiniteBullets;
    }
    
    
    
    
    
    
}
