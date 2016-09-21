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
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

/**
 * 
 * @author Alan Tsui
 * @since 1.0
 * @version 1.0
 */
public class Enemy extends Mob{    
    private int health;
    private int maxHealth;
    private int armor;
    private int maxArmor;
    
    private int indexCounter;
    private int counter;
    
    private BufferedImage currentImage;
    private BufferedImage[] images;
    
    public Enemy(int x, int y, int width, int height, int damage, int speed,int health, int maxHealth, int armor, int maxArmor, BufferedImage image, BufferedImage[] images,String sound) {
        super(x, y, width, height, damage, speed, image,sound);
        this.health = health;
        this.maxHealth = maxHealth;
        this.armor = armor;
        this.maxArmor = maxArmor;
        currentImage = images[0];
        this.images = images;
    }
    
    public void update(Graphics2D gd){
        drawHPBarAndArmor(gd);
        moveEnemys(gd);
    }
    
    public void moveEnemys(Graphics2D gd){
        left();
        counter++;
        if(counter%5 == 0){
            indexCounter++;
            if(indexCounter >= images.length){
                indexCounter = 0;
            }
            currentImage = images[indexCounter];
        }
        gd.drawImage(currentImage, null, (int) getX(), (int)getY());         
    }
        
    public void drawHPBarAndArmor(Graphics2D gd){
        int healthOffSet = (int) (getY()-10);
        int armorOffSet = (int) (getY()-5);
        double maxSize = 30.0;
        int heightOfBar = 3;
        double healthRatio = (double)health/(double)maxHealth;
        double armorRatio = (double)armor/(double)maxArmor;
        
        double hpBarSize = healthRatio*maxSize;
        double armorBarSize = armorRatio*maxSize;
        
        gd.setColor(Color.GREEN);
        gd.setColor(Color.RED);
        gd.fill(new Rectangle2D.Double(getX(),healthOffSet,maxSize, heightOfBar)); 
        gd.setColor(Color.GREEN);
        gd.fill(new Rectangle2D.Double(getX(),healthOffSet,hpBarSize, heightOfBar));
        gd.setColor(new Color(0, 255, 255));
        gd.fill(new Rectangle2D.Double(getX(),armorOffSet,armorBarSize, heightOfBar));
        gd.setColor(Color.BLACK);
    }
    
//    public void drawHPBarAndArmorA(Graphics2D gd){
//        int healthOffSet = getRectangle().y-10;
//        int armorOffSet = getRectangle().y-5;
//        double maxSize = 30.0;
//        int heightOfBar = 3;
//        double healthRatio = getHealth()/maxHealth;
//        double armorRatio = (double)armor.getHealth()/(double)armor.getMaxHealth();
//        
//        double hpBarSize = healthRatio*maxSize;
//        double armorBarSize = armorRatio*maxSize;
//        
//        gd.setColor(Color.GREEN);
//        gd.setColor(Color.RED);
//        gd.fill(new Rectangle2D.Double(getRectangle().x,healthOffSet,maxSize, heightOfBar)); 
//        gd.setColor(Color.GREEN);
//        gd.fill(new Rectangle2D.Double(getRectangle().x,healthOffSet,hpBarSize, heightOfBar));
//        gd.setColor(new Color(0, 255, 255));
//        gd.fill(new Rectangle2D.Double(getRectangle().x,armorOffSet,armorBarSize, heightOfBar));
//        gd.setColor(Color.BLACK);
//    }
    
    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public int getArmor() {
        return armor;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }

    public int getMaxArmor() {
        return maxArmor;
    }

    public void setMaxArmor(int maxArmor) {
        this.maxArmor = maxArmor;
    }
    
}
