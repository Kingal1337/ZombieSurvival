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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import static zombiesurvival.StartMenu.myFont;

/**
 * 
 * @author Alan Tsui
 * @since 1.0
 * @version 1.0
 */
public class PauseMenu extends JPanel{
    public PauseMenu(){
        setLayout(null);
        
        JLabel title = new JLabel("Paused",SwingConstants.CENTER);
        title.setForeground(Color.WHITE);
        title.setOpaque(false);
        title.setPreferredSize(new Dimension(200,40));
        title.setBounds(0,0,200,40);
        title.setFont(myFont.deriveFont(Font.BOLD,20f));
        add(title);
        
        JButton resume = new JButton("Resume");  
        resume.setOpaque(false);
        resume.setBackground(new Color(0.0f,0.0f,0.0f,0.0f));
        resume.setBorder(BorderFactory.createEmptyBorder());
        resume.setFocusPainted(false); 
        resume.setForeground(Color.DARK_GRAY);
        resume.setFont(myFont.deriveFont(Font.BOLD,18f));
        resume.setPreferredSize(new Dimension(75,30));
        resume.setBounds(15,60,75,30);
        resume.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {   
                resume.setOpaque(true);
                resume.setBackground(Color.LIGHT_GRAY);
            }
            @Override
            public void mouseExited(MouseEvent e) { 
                resume.setOpaque(false);
            }
        });
        resume.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                ZombieSurvival.mainPane.resume();
            }            
        });
        add(resume);
        
        JButton quit = new JButton("Exit");  
        quit.setOpaque(false);
        quit.setBackground(new Color(0.0f,0.0f,0.0f,0.0f));
        quit.setBorder(BorderFactory.createEmptyBorder());
        quit.setFocusPainted(false); 
        quit.setForeground(Color.DARK_GRAY);
        quit.setFont(myFont.deriveFont(Font.BOLD,18f));
        quit.setPreferredSize(new Dimension(75,30));
        quit.setBounds(110,60,75,30);
        quit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {   
                quit.setOpaque(true);
                quit.setBackground(Color.LIGHT_GRAY);
            }
            @Override
            public void mouseExited(MouseEvent e) { 
                quit.setOpaque(false);
            }
        });
        quit.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                ZombieSurvival.survive.mainFrameClosing();
            }            
        });
        add(quit);
    }
}
