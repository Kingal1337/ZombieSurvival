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
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
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
public class Character extends Mob{
    private int lineCannotCross;
    private Weapon weapon;
    private ArrayList<Bullet> bullets;
    private double angle;
    
    private boolean up;
    private boolean down;
    private boolean left;
    private boolean right;
    
    private int lives;
    private int score;
    private double mouseX;
    private double mouseY;
    
    public Character(double x, double y, int width, int height, int damage, int speed,int maxLives,BufferedImage image,int lineCannotCross,Weapon weapon,String sound) {
        super(x, y, width, height, damage, speed, image,sound);
        this.lineCannotCross = lineCannotCross;
        this.weapon = weapon;
        lives = maxLives;
        up = false;
        down = false;
        left = false;
        right = false;
        bullets = new ArrayList<>();
        angle = 0;
    }
    
    public void checkBulletIntersecting(ArrayList<Enemy> enemies){
        for(int i=bullets.size()-1;i>=0;i--){
            for(int j=enemies.size() - 1;j>= 0;j--){
                try{
                    if(new Rectangle.Double(bullets.get(i).getX(),bullets.get(i).getY(),bullets.get(i).getWidth(),bullets.get(i).getHeight()).intersects(new Rectangle.Double(enemies.get(j).getX(),enemies.get(j).getY(),enemies.get(j).getWidth(),enemies.get(j).getHeight())))
                    {
//                        System.out.println("Armor "+enemies.get(j).getArmor()+" Health "+enemies.get(j).getHealth());
//                        System.out.println("Power "+bullets.get(i).getDamage());
                        if(enemies.get(j).getArmor() > 0){
                            enemies.get(j).setArmor(enemies.get(j).getArmor()-bullets.get(i).getDamage());
                        }
                        else if(enemies.get(j).getArmor() < 1){
                            enemies.get(j).setHealth(enemies.get(j).getHealth()-bullets.get(i).getDamage());
                        }
                        if(enemies.get(j).getHealth() < 1){
                            enemies.remove(j);
                            score++;
                        }
                        bullets.remove(i);
                    }
                }catch(NullPointerException e){
////                   System.out.println(e); 
                }
            }
        }
    }
    
    public void checkPowerUp(Graphics2D gd,ArrayList<PowerUps> onScreenPowerUps,ArrayList<PowerUps> offScreenPowerUps){
        for(int i=onScreenPowerUps.size()-1;i>=0;i--){
            onScreenPowerUps.get(i).update(gd, MainFrame.character);
            for(int j=bullets.size()-1;j>=0;j--){
                try{
                    if(new Rectangle.Double(bullets.get(j).getX(),bullets.get(j).getY(),bullets.get(j).getWidth(),bullets.get(j).getHeight()).intersects(new Rectangle.Double(onScreenPowerUps.get(i).getX(),onScreenPowerUps.get(i).getY(),onScreenPowerUps.get(i).getWidth(),onScreenPowerUps.get(i).getHeight()))){
                        bullets.remove(j);
                        onScreenPowerUps.get(i).setCollected(true);
                        offScreenPowerUps.add(new PowerUps(onScreenPowerUps.get(i).getX(), onScreenPowerUps.get(i).getY(), onScreenPowerUps.get(i).getWidth(), onScreenPowerUps.get(i).getHeight(), onScreenPowerUps.get(i).getImage(), onScreenPowerUps.get(i).getType(),onScreenPowerUps.get(i).isCollected()));
                        onScreenPowerUps.remove(i);
                    }
                }catch(Exception e){
                    
                }
            }
        }
//        System.out.println("Size "+offScreenPowerUps.size());
        for(int i=0;i<offScreenPowerUps.size();i++){
            offScreenPowerUps.get(i).update(gd, MainFrame.character);
            if(offScreenPowerUps.get(i).isCollected()){
                if(tick%(FPS*offScreenPowerUps.get(i).getAmountLasted()) == 0){
                    offScreenPowerUps.get(i).removePowerUp(MainFrame.character);
                }
            }
            if(offScreenPowerUps.get(i).isDelete()){
                offScreenPowerUps.remove(i);
            }
        }
    }
    
    public void checkAngle(){
        double angle = Math.atan2(mouseY - (double)getY() - (double)(getHeight()/2), mouseX - (double)getX() - (double)(getWidth()/2))+(Math.PI/2);
        this.angle = angle;
    }
    
    public void update(Graphics2D gd){
        checkAngle();
        if(getX() < 10){
            setX(10);
        }
        if(getY() < 10){
            setY(10);
        }
        if(getY()+getHeight() > 561){
            setY(561-getHeight());
        }
        if(up){
            up();
        }
        if(down){
            down();
        }
        if(left){
            left();
        }
        if(right){
            right();
        }
        weapon.update();
        if(getX()+getHeight() >= lineCannotCross){
            setX(lineCannotCross-getHeight());
        }
        moveBullets();
        checkBullets();
        shootBullets();
        gd.fill(new Rectangle(lineCannotCross,0,2,600));
        gd.setColor(Color.BLACK);
        drawBullets(gd);
        rotateImage(gd,(int)getX(), (int)getY(),getImage(),getAngle());
        
        
    }
    public void drawBullets(Graphics2D gd){
        for(int i=0;i<bullets.size();i++){
            if(bullets.get(i).getType() == 555){
                gd.setColor(Color.RED);
            }
            else if(bullets.get(i).getDamage() > weapon.getDefaultDamage()){
                gd.setColor(Color.BLUE);
            }
            else{
                gd.setColor(Color.BLACK);
            
            }
            gd.fill(new Rectangle.Double(bullets.get(i).getX()+getWidth()/2,bullets.get(i).getY()+getHeight()/2,bullets.get(i).getWidth(),bullets.get(i).getHeight()));
//            rotateImage(gd,(int)bullets.get(i).getX(),(int)bullets.get(i).getY(),bullets.get(i).getImage(),bullets.get(i).getAngle());
        }      
    }
    public void moveBullets(){
        for(int i=0;i<bullets.size();i++){
            bullets.get(i).move();            
        }
    }
    
    public void checkBullets(){
        for(int j=0;j<bullets.size();j++){
            if(bullets.get(j).getX()<-20){
                bullets.remove(j);
            }
            else if(bullets.get(j).getX() > 1060){
                bullets.remove(j);
            }
            else if(bullets.get(j).getY()<-20){
                bullets.remove(j);
            }
            else if(bullets.get(j).getY() > 580){
                bullets.remove(j);
            }
        }
    }
    public void reload(){
        if(!weapon.isInfiniteBullets()){
            weapon.setReloading(true);
        }
    }
    public void shootBullets(){
        if(weapon.isShooting() && weapon.canShoot()){
            weapon.shootBullets(bullets, angle, getX(), getY());
        }
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }        
    
    public int getLineCannotCross() {
        return lineCannotCross;
    }

    public void setLineCannotCross(int lineCannotCross) {
        this.lineCannotCross = lineCannotCross;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public ArrayList<Bullet> getBullets() {
        return bullets;
    }

    public void setBullets(ArrayList<Bullet> bullets) {
        this.bullets = bullets;
    }

    public double getAngle() {
        return angle;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public double getMouseX() {
        return mouseX;
    }

    public void setMouseX(double mouseX) {
        this.mouseX = mouseX;
    }

    public double getMouseY() {
        return mouseY;
    }

    public void setMouseY(double mouseY) {
        this.mouseY = mouseY;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
    
    
    
    
    
    
    
        
}
