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

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import static zombiesurvival.ZombieSurvival.allowSound;

/**
 * 
 * @author Alan Tsui
 * @since 1.0
 * @version 1.0
 */
public class MovingObjects extends Entity{
    private int speed;
    private int damage;
    private String sound;
    public MovingObjects(double x, double y, int width, int height,int damage,int speed, BufferedImage image,String sound) {
        super(x, y, width, height, image);
        this.speed = speed;
        this.damage = damage;
        this.sound = sound;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }    

    public String getSound() {
        return sound;
    }

    public void setSound(String sound) {
        this.sound = sound;
    }
    
    public void playSound(String sound){
        if(allowSound){
            try {
                SoundClass.sound.playAudio(sound);
            } catch (IOException ex) {
                Logger.getLogger(MovingObjects.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(MovingObjects.class.getName()).log(Level.SEVERE, null, ex);
            }        
        }
    }
    public void stopSound(){        
        if(allowSound){
            try {
                SoundClass.sound.stop();
            } catch (IOException ex) {
                Logger.getLogger(MovingObjects.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(MovingObjects.class.getName()).log(Level.SEVERE, null, ex);
            }        
        }
    }
    
    public void up(){
        setY(getY()-getSpeed());
    }
    public void down(){
        setY(getY()+getSpeed());        
    }
    public void left(){
        setX(getX()-getSpeed());        
    }
    public void right(){
        setX(getX()+getSpeed());        
    }
    
    
    
    
}
