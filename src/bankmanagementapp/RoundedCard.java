/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bankmanagementapp;

import static bankmanagementapp.frontPage.*;
import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;

/**
 *
 * @author Jen
 */  
  
     public class RoundedCard extends JLabel {
        private final int radius;
        RoundedCard(int radius) {
            this.radius = radius;
            setOpaque(false);
            setBackground(CARD_BG);
        }
         
        
        @Override
        protected void paintComponent(Graphics g) {
            
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(getBackground());
            g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), radius, radius));
            g2.dispose();
            super.paintComponent(g);
        }
       
        @Override
        protected void paintBorder(Graphics g) {
            
            
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(new Color(210, 218, 235));
            g2.setStroke(new BasicStroke(1f));
            g2.draw(new RoundRectangle2D.Float(0.5f, 0.5f, getWidth()-1, getHeight()-1, radius, radius));
            g2.dispose();
        }
      }


  class IconCircle extends JLabel {
        private final Color bg, fg;
        IconCircle(Color bg, Color fg) {
            this.bg = bg; this.fg = fg;
            setOpaque(false);
        }
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(bg);
            g2.fillOval(0, 0, getWidth(), getHeight());
            
            
            
            g2.setColor(fg); //drawing ng bahay
            g2.setStroke(new BasicStroke(2f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
            int cx = getWidth()/2, cy = getHeight()/2;
            g2.drawRect(cx-10, cy-4, 20, 12);
            g2.drawLine(cx-14, cy-4, cx+14, cy-4);
            g2.drawLine(cx, cy-4, cx, cy-10);
            g2.drawLine(cx-14, cy-4, cx, cy-10);
            g2.drawLine(cx+14, cy-4, cx, cy-10);
            g2.dispose();
        }
    }



    