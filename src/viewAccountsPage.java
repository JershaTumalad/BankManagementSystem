package BAMS;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.JTableHeader;

public class ViewAccountsPage extends JFrame implements ActionListener {
        JButton btnClose;

        private AccountFiles accountFiles;

        Color primaryBlue = new Color(25, 45, 85);
        Color backgroundColor = new Color(180, 190, 210);
        Color buttonColor = new Color(130, 160, 210);

        public ViewAccountsPage(AccountFiles files){
            this.accountFiles = files;

            setSize(440,720); 
            setLayout(null); 
            setLocationRelativeTo(null);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            getContentPane().setBackground(backgroundColor);
            JPanel header = new JPanel(); 
            header.setBackground(primaryBlue);
            header.setBounds(0,0,430,100); 
            header.setLayout(null);
            add(header);
            
            JLabel title = new JLabel("ALL ACCOUNTS"); 
            title.setForeground(Color.WHITE);
            title.setFont(new Font("Segoe UI",Font.BOLD,18)); 
            title.setBounds(30,35,300,30);
            header.add(title);
            
            JTable table = new JTable(accountFiles.getAccountsData(), new String[]{"Acc No","Name","Type","Balance"});
            table.setRowHeight(30);
            JTableHeader th = table.getTableHeader();
            th.setBackground(buttonColor); 
            th.setForeground(Color.WHITE);
            JScrollPane sp = new JScrollPane(table);
            sp.setBounds(20,120,390,420); 
            add(sp);
            
            btnClose = new JButton("Close");
            btnClose.setBounds(30,570,360,50);
            btnClose.setBackground(buttonColor);
            btnClose.setForeground(Color.WHITE); 
            add(btnClose);
            
            btnClose.addActionListener(this);
        }
        public void actionPerformed(ActionEvent e){ 
            dispose(); 
        new GUI1Frame(accountFiles).setVisible(true); }
    }
}
