
package BAMS;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class GUI1frame extends JFrame implements ActionListener{
    
    private JButton btnAdd, btnRemove, btnView, btnSearch;
    private JLabel lblTitle;
    
    accountFiles files;
    
    public GUI1frame (accountFiles files){
        this.files = files;
        
        setSize(360,480);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        
        lblTitle = new JLabel ("Account Management");
        lblTitle.setBounds(61, 30, 300, 30);
        lblTitle.setFont(new Font("Arial", Font.PLAIN, 24));
        add(lblTitle);
        
        btnAdd = new JButton ("Add");
        btnAdd.setBounds(93, 90, 150, 50);
        add(btnAdd);
        
        btnRemove = new JButton ("Remove");
        btnRemove.setBounds(93, 170, 150, 50);
        add(btnRemove);
        
        btnSearch = new JButton ("Search Account");
        btnSearch.setBounds(93, 250, 150, 50);
        add(btnSearch);
        
        btnView = new JButton ("View Accounts");
        btnView.setBounds(93, 330, 150, 50);
        add(btnView);
        
        btnAdd.addActionListener(this);
        btnRemove.addActionListener(this);
        btnView.addActionListener(this);
        btnSearch.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        
        if (e.getSource() == btnAdd){
            dispose();
            addPage aP = new addPage(files);
            aP.setVisible(true);
        }
        
        if (e.getSource() == btnRemove){
            dispose();
            removePage rP =new removePage(files);
            rP.setVisible(true);
        }
        
        if (e.getSource() == btnView){
            dispose();
            viewAccountsPage vP =new viewAccountsPage(files);
            vP.setVisible(true);

        }
        
        if (e.getSource() == btnSearch){
            dispose();
            searchPage sP = new searchPage(files);
            sP.setVisible(true);
        }
    }
}
