package project;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.JTableHeader;
import javax.swing.border.LineBorder;

public class ViewAccountsPage extends JFrame implements ActionListener {

    AccountFiles files;
    JButton back;

    Color PRIMARY = new Color(25,45,85);
    Color BG = new Color(180,190,210);
    Color BTN = new Color(130,160,210);

    public ViewAccountsPage(AccountFiles files){
        this.files = files;

        setSize(440,720);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(BG);

        JPanel header = new JPanel();
        header.setBackground(PRIMARY);
        header.setBounds(0,0,430,100);
        header.setLayout(null);
        add(header);

        JLabel title = new JLabel("ALL ACCOUNTS");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Segoe UI",Font.BOLD,18));
        title.setBounds(30,35,300,30);
        header.add(title);

        String[] cols = {"Acc No","Name","Type","Balance"};
        JTable table = new JTable(files.getAccountsData(), cols);

        table.setRowHeight(30);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 13));

        JTableHeader th = table.getTableHeader();
        th.setBackground(BTN);
        th.setForeground(Color.WHITE);
        th.setFont(new Font("Segoe UI", Font.BOLD, 13));

        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(20,120,390,420);
        add(sp);

        back = new JButton("Close");
        back.setBounds(30,570,360,50);
        back.setBackground(BTN);
        back.setForeground(Color.WHITE);
        back.setFont(new Font("Segoe UI", Font.BOLD, 14));
        back.setBorder(new LineBorder(Color.GRAY,1,true));
        back.setFocusPainted(false);
        add(back);

        back.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e){
        if(e.getSource() == back){
            dispose();
            new GUI1Frame(files).setVisible(false);
        }
    }
}