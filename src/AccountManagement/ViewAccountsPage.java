package AccountManagement;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;

public class ViewAccountsPage extends BaseFrame implements ActionListener {

    private AccountFiles files;
    
    private JButton btnBack;
    private JLabel lblStateBank;

    public ViewAccountsPage(AccountFiles files) {
        super("Bank Account Management");
        this.files = files;
        initComponents();
    }

    @Override
    protected void initComponents() {
        JPanel pnlHeader = new JPanel();
        pnlHeader.setBackground(NAVY); 
        pnlHeader.setBounds(0, 0, 430, 120); 
        pnlHeader.setLayout(null);
        add(pnlHeader);

        lblStateBank = new JLabel("STATE-BANK");
        lblStateBank.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblStateBank.setForeground(new Color(170, 190, 220)); 
        lblStateBank.setBounds(30, 20, 350, 25);
        pnlHeader.add(lblStateBank);
        
        JLabel lblTitle = new JLabel("All Records");
        lblTitle.setFont(new Font("Tahoma", Font.BOLD, 26)); 
        lblTitle.setForeground(WHITE); 
        lblTitle.setBounds(30, 45, 350, 45);
        pnlHeader.add(lblTitle);

        JPanel pnlSummary = new JPanel();
        pnlSummary.setBackground(CARD_BG); 
        pnlSummary.setBounds(25, 140, 364, 57); 
        pnlSummary.setLayout(null);
        add(pnlSummary);

        int count = files.getAccountCount();
        JLabel lblCount = new JLabel(count + " account" + (count != 1 ? "s" : "") + " registered");
        lblCount.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblCount.setForeground(new Color(70, 80, 95));
        lblCount.setBounds(15, 18, 200, 20);
        pnlSummary.add(lblCount);

        JLabel lblTotalLabel = new JLabel("Total Balance");
        lblTotalLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
        lblTotalLabel.setForeground(Color.GRAY);
        lblTotalLabel.setBounds(230, 7, 100, 14);
        pnlSummary.add(lblTotalLabel);

        JLabel lblTotal = new JLabel(String.format("P %,.2f", files.getTotalBalance()));
        lblTotal.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblTotal.setForeground(NAVY);
        lblTotal.setBounds(230, 24, 130, 20);
        pnlSummary.add(lblTotal);

        String[] cols = {"Acc. No.", "Name", "Type", "Balance"};

        DefaultTableModel model = new DefaultTableModel(files.getAccountsData(), cols) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        JTable tbl = new JTable(model);
        tbl.setRowHeight(38);
        tbl.setFont(new Font("Tahoma", Font.PLAIN, 12));
        tbl.setShowGrid(false);
        tbl.setIntercellSpacing(new Dimension(0, 0));
        tbl.getTableHeader().setReorderingAllowed(false);
        tbl.setSelectionBackground(new Color(220, 230, 250));
        tbl.setSelectionForeground(NAVY);

        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (!isSelected) {
                    if (row % 2 == 0) {
                        c.setBackground(WHITE);
                    } else {
                        c.setBackground(new Color(245, 248, 255));
                    }
                }
                return c;
            }
        };
        tbl.setDefaultRenderer(Object.class, renderer);

        JTableHeader head = tbl.getTableHeader();
        head.setBackground(NAVY);
        head.setForeground(WHITE);
        head.setFont(new Font("Tahoma", Font.BOLD, 12));
        head.setPreferredSize(new Dimension(head.getWidth(), 38));

        TableColumnModel colModel = tbl.getColumnModel();
        colModel.getColumn(0).setPreferredWidth(69);
        colModel.getColumn(1).setPreferredWidth(135);
        colModel.getColumn(2).setPreferredWidth(80);
        colModel.getColumn(3).setPreferredWidth(80);

        JScrollPane sp = new JScrollPane(tbl);
        sp.setBounds(25, 212, 364, 375); 
        sp.setBorder(BorderFactory.createLineBorder(new Color(215, 225, 240)));
        add(sp);

        btnBack = new JButton("Back");
        btnBack.setBounds(25, 605, 364, 50); 
        btnBack.setBorderPainted(false);
        btnBack.setBackground(ACCENT);  
        btnBack.setForeground(NAVY);     
        btnBack.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnBack.setFocusPainted(false);
        add(btnBack);

        btnBack.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnBack) {
            dispose();
            new dashboardAccMng(files).setVisible(true);
        }
    }
}