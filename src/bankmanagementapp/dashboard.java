
package bankmanagementapp;

import javax.swing.*;
import java.awt.*;


public class dashboard extends JFrame{
    
    JButton homepage;
    public dashboard(accountFiles files){
        
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
        setLayout(null);
        setTitle("Dashboard");
        
        JButton btnAccMgmt = new JButton("Account Management");
            btnAccMgmt.setBounds(150, 150, 200, 40);
            add(btnAccMgmt);

            JButton btnTransactions = new JButton("Transactions");
            btnTransactions.setBounds(150, 210, 200, 40);
            add(btnTransactions);

            btnAccMgmt.addActionListener(e -> {
                new GUI1frame(files).setVisible(true);
                this.dispose();
            });

            btnTransactions.addActionListener(e -> {
               new dashboard_transactions.BankAppGUI();
                this.dispose();
            });

        
         homepage = new JButton ("Back to Homepage");
            homepage.setBounds(150, 420, 200, 30);
            add(homepage); 
            homepage.addActionListener( e-> {
            
            new frontPage().setVisible(true);
            this.dispose();
            
            
            });
    }
}
