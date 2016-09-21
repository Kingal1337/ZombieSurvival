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
import java.awt.image.BufferedImage;
import java.io.IOException;
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
public class PowerUps extends NonMovingObjects{
    private int type;
    private boolean powerUpGiven;
    private BufferedImage background;
    private double amountLasted;
    private boolean collected;
    private boolean delete;
    
    private int angle;
    
    //type 1 = +Life
    //type 2 = infiniteBullets
    //type 3 = gives strength
    public PowerUps(double x, double y, int width, int height, BufferedImage image, int type, boolean collected) {
        super(x, y, width, height, image);
        this.type = type;
        powerUpGiven = false;
        this.collected = collected;
        try {
            background = ImageIO.read(getClass().getResource("/res/PowerUps/PowerUpBackground.png"));
        } catch (IOException ex) {
            Logger.getLogger(PowerUps.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(type == 1){
            amountLasted = .1;
        }
        else if(type == 2){
            amountLasted = 10;
        }
        else if(type == 3){
            amountLasted = 10;
        }
        
    }
    public void update(Graphics2D gd,Character character){
        if(!collected){
            angle++;
            if(angle >= 360){
                angle = 0;
            }
//            gd.drawImage(background, null, (int)getX(), (int)getY());
            rotateImage(gd, (int)getX(), (int)getY(), background, angle);
            gd.drawImage(getImage(), null, (int)getX(), (int)getY());
        }
        if(!powerUpGiven && collected){
            powerUpGiven = true;
            givePowerUp(character);
//            System.out.println("Type "+type);
        }
        
    }
    public void givePowerUp(Character character){
        if(type == 1){
           character.setLives(character.getLives()+1);
        }        
        else if(type == 2){
            character.getWeapon().setInfiniteBullets(true);
            character.getWeapon().setShoot(true);
        }
        else if(type == 3){
            character.getWeapon().setDamage(((character.getWeapon().getDamage()/2)/2)+character.getWeapon().getDamage());
        }
    }
    public void removePowerUp(Character character){
        if(type == 2){
            character.getWeapon().setInfiniteBullets(character.getWeapon().isDefaultInfiniteBullets());
        }
        else if(type == 3){
            character.getWeapon().setDamage(character.getWeapon().getDefaultDamage());
        }
        delete = true;
    }

    public boolean isDelete() {
        return delete;
    }

    public void setDelete(boolean delete) {
        this.delete = delete;
    }

    public boolean isCollected() {
        return collected;
    }

    public void setCollected(boolean collected) {
        this.collected = collected;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public double getAmountLasted() {
        return amountLasted;
    }

    public void setAmountLasted(double amountLasted) {
        this.amountLasted = amountLasted;
    }
    
    
    
}
