package project;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

public class viewAccountsPage extends JFrame implements ActionListener{
    
    private JButton  btnBack;
    private JLabel lblTitle, lblfName, lbllName, lblbday, lblaccType, lblaccNo, lbliniBal;
    private JTextArea areaView;
    accountFiles files;
    
    viewAccountsPage(accountFiles files){
        this.files = files;
        
        setSize(500,480);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        
        lblTitle = new JLabel ("View Accounts");
        lblTitle.setBounds(180, 30, 300, 30);
        lblTitle.setFont(new Font("Arial", Font.PLAIN, 24));
        add(lblTitle);
        
        areaView = new JTextArea ();
        areaView.setBounds(20, 79, 450, 291);
        areaView.setText(files.showExistingAccounts());
        areaView.setEditable(false);
        add(areaView);
        
        btnBack = new JButton ("Back");
        btnBack.setBounds(190, 390, 120, 30);
        add(btnBack);
        
        btnBack.addActionListener(this); 
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        dispose();
        
        if (e.getSource() == btnBack){
            menuFrame hp = new menuFrame(files);
            hp.setVisible(true);
        }
    }

}
