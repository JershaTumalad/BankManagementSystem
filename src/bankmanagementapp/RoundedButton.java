/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bankmanagementapp;

import java.awt.*;

import javax.swing.*;


//ADDITIONAL CLASS FOR BUTTON SA LAYOUT

/**
 *
 * @author Jen
 */
public class RoundedButton extends JButton {
    
     public RoundedButton(String text) {
        super(text);
        setFocusPainted(false);
        setContentAreaFilled(false);
        setForeground(Color.BLACK);
         setBorderPainted(false); // ❗ removes the outline
        setBorder(null);

    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
        super.paintComponent(g);
    }
}
    

