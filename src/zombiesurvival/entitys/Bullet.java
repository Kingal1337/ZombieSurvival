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
import static zombiesurvival.MainFrame.FPS;
import static zombiesurvival.MainFrame.TICK;

/**
 * 
 * @author Alan Tsui
 * @since 1.0
 * @version 1.0
 */
public class Bullet extends MovingObjects{    
    private double speed;
    private double angle;
    private double origx;
    private double origy;
    private double distance;
    private boolean inAir;
    private int type;
    
    public Bullet(double x, double y, int width, int height, int damage, int speed, BufferedImage image,double angle,int type,String sound) {
        super(x, y, width, height, damage, speed, image,sound);
        this.angle = angle;
        this.type = type;
        this.speed = speed;
        this.distance = 0;
        inAir = true;
        origx = getX();
        origy = getY();
    }    
    public void update(){
        if(inAir){
            move();
        }
    }
    public void move(){
        distance += speed;
        setX(origx+calcSpeedX(angle));
        setY(origy+calcSpeedY(angle));
    }
    public double calcSpeedX(double angle){
        return distance*Math.cos(angle);
    }
    public double calcSpeedY(double angle){
        return distance*Math.sin(angle);
    }  
    
    public double getAngle(){
        return angle;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

    public boolean isInAir() {
        return inAir;
    }

    public void setInAir(boolean inAir) {
        this.inAir = inAir;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
    
    
    
    
}
