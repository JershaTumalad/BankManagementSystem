package project;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

public class ViewAccountsPage extends JFrame implements ActionListener {

    private AccountFiles files;
    private JButton btnBack;

    public ViewAccountsPage(AccountFiles files) {
        this.files = files;
        setTitle("Account Records");
        setSize(430, 720);
        setLayout(null);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(200, 210, 230));

        JPanel pnlHeader = new JPanel();
        pnlHeader.setBackground(new Color(25, 42, 86));
        pnlHeader.setBounds(0, 0, 430, 100);
        pnlHeader.setLayout(null);
        add(pnlHeader);

        JLabel lblTitle = new JLabel("ALL ACCOUNT RECORDS");
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTitle.setBounds(30, 35, 300, 30);
        pnlHeader.add(lblTitle);

        String[] cols = {"Acc No.", "Name", "Acc Type", "Balance"};
        
        DefaultTableModel model = new DefaultTableModel(files.getAccountsData(), cols) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; 
            }
        };

        JTable tbl = new JTable(model);
        tbl.setRowHeight(35);
        tbl.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        tbl.getTableHeader().setReorderingAllowed(false);

        tbl.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        TableColumnModel columnModel = tbl.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(70);
        columnModel.getColumn(1).setPreferredWidth(130);
        columnModel.getColumn(2).setPreferredWidth(100);
        columnModel.getColumn(3).setPreferredWidth(80);

        JTableHeader head = tbl.getTableHeader();
        head.setBackground(new Color(0x8bace0));
        head.setFont(new Font("Segoe UI", Font.BOLD, 13));

        JScrollPane sp = new JScrollPane(tbl);
        sp.setBounds(27, 120, 360, 440);
        sp.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        add(sp);

        btnBack = new JButton("BACK") {
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
        btnBack.setBounds(27, 590, 360, 50);
        btnBack.setBackground(new Color(0x8bace0));
        btnBack.setFont(new Font("Segoe UI", Font.PLAIN, 14)); 
        btnBack.setContentAreaFilled(false);
        btnBack.setBorderPainted(false);
        add(btnBack);

        btnBack.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnBack) {
            dispose();
            new GUI1Frame(files).setVisible(true);
        }
    }
}