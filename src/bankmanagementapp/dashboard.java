
package bankmanagementapp;

import javax.swing.*;
import java.awt.*;


public class dashboard extends JFrame{
    
    JButton homepage;
    public dashboard(){
        
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(430, 720);
        setLocationRelativeTo(null);
        setLayout(null);
        setTitle("Dashboard");
        
        
         homepage = new JButton ("Back to Homepage");
            homepage.setBounds(150, 420, 200, 30);
            add(homepage); 
            homepage.addActionListener( e-> {
            
            new frontPage().setVisible(true);
            this.dispose();
            
            
            });
    }
}
