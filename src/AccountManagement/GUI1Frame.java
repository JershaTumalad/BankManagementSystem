package project;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GUI1Frame extends JFrame implements ActionListener {

    private JPanel pnlHeader, pnlCard;
    private JLabel lblWelcome, lblTitle;
    private JButton btnAdd, btnRemove, btnSearch, btnView, btnExit;
    private AccountFiles files;

    private final Color navy = new Color(25, 42, 86);
    private final Color lightBlue = new Color(0x8bace0);
    private final Color bgColor = new Color(200, 210, 230);

    public GUI1Frame(AccountFiles files) {
        this.files = files;

        setTitle("Bank App");
        setSize(430, 720); 
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().setBackground(bgColor);

        pnlHeader = new JPanel();
        pnlHeader.setBackground(navy);
        pnlHeader.setBounds(0, 0, 430, 160);
        pnlHeader.setLayout(null);
        add(pnlHeader);

        lblWelcome = new JLabel("Welcome Back!");
        lblWelcome.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        lblWelcome.setForeground(Color.WHITE);
        lblWelcome.setBounds(30, 20, 300, 30);
        pnlHeader.add(lblWelcome);

        lblTitle = new JLabel("ACCOUNT MANAGEMENT", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTitle.setForeground(navy);
        lblTitle.setBackground(Color.WHITE);
        lblTitle.setOpaque(true);
        lblTitle.setBounds(27, 65, 360, 60); 
        pnlHeader.add(lblTitle);

        pnlCard = new JPanel();
        pnlCard.setBackground(Color.WHITE);
        pnlCard.setBounds(27, 180, 360, 420); 
        pnlCard.setLayout(null);
        add(pnlCard);

        btnAdd = createBtn("ADD ACCOUNT", 50);
        btnRemove = createBtn("REMOVE ACCOUNT", 140);
        btnSearch = createBtn("SEARCH ACCOUNT", 230);
        btnView = createBtn("VIEW ALL RECORDS", 320);

        pnlCard.add(btnAdd);
        pnlCard.add(btnRemove);
        pnlCard.add(btnSearch);
        pnlCard.add(btnView);

        btnExit = new JButton("BACK");
        btnExit.setBounds(115, 620, 200, 40);
        btnExit.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnExit.setForeground(new Color(200, 70, 70));
        btnExit.setContentAreaFilled(false);
        btnExit.setBorderPainted(false);
        add(btnExit);

        btnAdd.addActionListener(this);
        btnRemove.addActionListener(this);
        btnSearch.addActionListener(this);
        btnView.addActionListener(this);
        btnExit.addActionListener(this);
    }

    private JButton createBtn(String text, int y) {
        JButton btn = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
                g2.dispose();
                super.paintComponent(g);
            }
        };
        btn.setBounds(30, y, 300, 55);
        btn.setBackground(lightBlue);
        btn.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        btn.setContentAreaFilled(false);
        btn.setBorderPainted(false);
        return btn;
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnExit) {
            System.exit(0);
        } else {
            dispose();
            if (e.getSource() == btnAdd) 
                new AddPage(files).setVisible(true);
            else if (e.getSource() == btnRemove)
                new RemovePage(files).setVisible(true);
            else if (e.getSource() == btnSearch)
                new SearchPage(files).setVisible(true);
            else if (e.getSource() == btnView)
                new ViewAccountsPage(files).setVisible(true);
        }
    }
}