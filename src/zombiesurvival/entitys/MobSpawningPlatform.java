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
public class MobSpawningPlatform extends NonMovingObjects{
    private double spawningTime;
    private int spawningType;
    private ArrayList<Enemy> enemies;
    private double angle;
    public MobSpawningPlatform(int x, int y, int width, int height,double spawningTime,int spawningType, BufferedImage image) {
        super(x, y, width, height, image);
        this.spawningTime = spawningTime;
        this.spawningType = spawningType;
        enemies = new ArrayList<>();
    }
    
    public void update(Graphics2D gd){
        if(tick%(FPS*spawningTime) == 0){
            Random r = new Random();
            
            if(spawningType == 1){
                int x = r.nextInt(((int)getX() + getWidth()) - ((int)getX() - getWidth())) + ((int)getX()-getWidth());
                int y = r.nextInt(((int)getY() + getHeight()) - ((int)getY() - getHeight())) + ((int)getY()-getHeight());
                int randomSpeed = r.nextInt(6 - 2) + 2;
                int randomHealth = r.nextInt(101 - 25) + 25;
                int randomArmor = r.nextInt(101 - 0) + 0;
                ZombieEnemy enemy = null;
                    try {
                        enemy = new ZombieEnemy(x,y,30,30,1,randomSpeed,randomHealth,randomHealth,randomArmor,randomArmor,getImage(),new BufferedImage[]{
                            ImageIO.read(getClass().getResource("/res/Enemies/Zombie.png")),
                            ImageIO.read(getClass().getResource("/res/Enemies/ZombieWalk1.png")),
                            ImageIO.read(getClass().getResource("/res/Enemies/ZombieWalk2.png"))
                        },null);
                    } catch (IOException ex) {
                        Logger.getLogger(MobSpawningPlatform.class.getName()).log(Level.SEVERE, null, ex);
                    }
                enemies.add(enemy);
            }
            else if(spawningType == 2){
                int x = r.nextInt(((int)getX() + getWidth()) - ((int)getX() - getWidth())) + ((int)getX()-getWidth());
               
                int randomHealth = r.nextInt(151 - 50) + 50; int y = r.nextInt(((int)getY() + getHeight()) - ((int)getY() - getHeight())) + ((int)getY()-getHeight());
                int randomSpeed = r.nextInt(4 - 2) + 2;
                int randomArmor = r.nextInt(101 - 25) + 25;
                ZombieEnemy enemy = null;
                    try {
                        enemy = new ZombieEnemy(x,y,30,30,1,randomSpeed,randomHealth,randomHealth,randomArmor,randomArmor,getImage(),new BufferedImage[]{
                            ImageIO.read(getClass().getResource("/res/Enemies/GurlZombie.png")),
                            ImageIO.read(getClass().getResource("/res/Enemies/GurlZombieWalk1.png")),
                            ImageIO.read(getClass().getResource("/res/Enemies/GurlZombieWalk2.png"))
                        },null);
                    } catch (IOException ex) {
                        Logger.getLogger(MobSpawningPlatform.class.getName()).log(Level.SEVERE, null, ex);
                    }
                enemies.add(enemy);
            }
            else if(spawningType == 3){
                int x = r.nextInt(((int)getX() + getWidth()) - ((int)getX() - getWidth())) + ((int)getX()-getWidth());
                int y = r.nextInt(((int)getY() + getHeight()) - ((int)getY() - getHeight())) + ((int)getY()-getHeight());
                int randomSpeed = r.nextInt(10 - 5) + 5;
                int randomHealth = r.nextInt(50 - 25) + 25;
                int randomArmor = r.nextInt(1 - 0) + 0;
                ZombieEnemy enemy = null;
                    try {
                        enemy = new ZombieEnemy(x,y,30,30,1,randomSpeed,randomHealth,randomHealth,randomArmor,randomArmor,getImage(),new BufferedImage[]{
                            ImageIO.read(getClass().getResource("/res/Enemies/SuperChargedZombie.png")),
                            ImageIO.read(getClass().getResource("/res/Enemies/SuperChargedZombieWalk1.png")),
                            ImageIO.read(getClass().getResource("/res/Enemies/SuperChargedZombieWalk2.png"))
                        },null);
                    } catch (IOException ex) {
                        Logger.getLogger(MobSpawningPlatform.class.getName()).log(Level.SEVERE, null, ex);
                    }
                enemies.add(enemy);
            }
        }
        angle++;
        if(angle >= 360){
            angle = 0;
        }
        rotateImage(gd,(int)getX(),(int)getY(),getImage(),Math.toRadians(angle));
    }

    public double getSpawningTime() {
        return spawningTime;
    }

    public void setSpawningTime(double spawningTime) {
        this.spawningTime = spawningTime;
    }

    public int getSpawningType() {
        return spawningType;
    }

    public void setSpawningType(int spawningType) {
        this.spawningType = spawningType;
    }

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    public void setEnemies(ArrayList<Enemy> enemies) {
        this.enemies = enemies;
    }
    
    
}
