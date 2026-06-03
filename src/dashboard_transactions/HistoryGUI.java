package dashboard_transactions;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.util.*;

public class HistoryGUI extends JFrame {

    public HistoryGUI(TransactionManager manager, BankAppGUI mainWindow) {

        final Color PRIMARY    = new Color(25, 48, 90);
        final Color LIGHT_BLUE = new Color(139, 172, 224);
        final Color BG         = new Color(200, 210, 230);
        final Color TEXT_MUTED = new Color(100, 110, 130);

        setTitle("Transaction History");
        setSize(430, 720);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setBackground(BG);

        JPanel navPanel = new JPanel(null);
        navPanel.setBounds(0, 0, 430, 90);
        navPanel.setBackground(PRIMARY);
        add(navPanel);

        JLabel bankName = new JLabel("STATE BANK · HISTORY");
        bankName.setBounds(0, 18, 430, 16);
        bankName.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        bankName.setForeground(LIGHT_BLUE);
        bankName.setHorizontalAlignment(SwingConstants.CENTER);
        navPanel.add(bankName);

        JLabel navTitle = new JLabel("Transactions");
        navTitle.setBounds(0, 36, 430, 36);
        navTitle.setFont(new Font("Segoe UI", Font.BOLD, 26));
        navTitle.setForeground(Color.WHITE);
        navTitle.setHorizontalAlignment(SwingConstants.CENTER);
        navPanel.add(navTitle);

        JPanel balCard = new JPanel(null);
        balCard.setBounds(20, 108, 380, 80);
        balCard.setBackground(PRIMARY);
        balCard.setBorder(BorderFactory.createLineBorder(LIGHT_BLUE, 1));
        add(balCard);

        JLabel balLbl = new JLabel("CURRENT BALANCE");
        balLbl.setBounds(15, 12, 350, 16);
        balLbl.setFont(new Font("Segoe UI", Font.PLAIN, 10));
        balLbl.setForeground(LIGHT_BLUE);
        balCard.add(balLbl);

        JLabel balAmount = new JLabel(String.format("PHP %,.2f", manager.getBalance()));
        balAmount.setBounds(15, 30, 350, 36);
        balAmount.setFont(new Font("Segoe UI", Font.BOLD, 26));
        balAmount.setForeground(Color.WHITE);
        balCard.add(balAmount);

        String[] columns = {"ID", "Type", "Amount", "Date", "Status"};
        DefaultTableModel tableModel = new DefaultTableModel(columns, 0) {
            public boolean isCellEditable(int row, int col) { return false; }
        };

        ArrayList<Transaction> list = manager.getTransactionList();
        for (int i = 0; i < list.size(); i++) {
            Transaction t = list.get(i);
            String amtStr;
            if (t.getTransactionType().equals("Deposit")) {
                amtStr = "+PHP " + String.format("%.2f", t.getAmount());
            } else {
                amtStr = "-PHP " + String.format("%.2f", t.getAmount());
            }
            tableModel.addRow(new Object[]{
                t.getTransactionID(),
                t.getTransactionType(),
                amtStr,
                t.getDate(),
                t.getStatus()
            });
        }

        if (list.isEmpty()) {
            tableModel.addRow(new Object[]{"—", "No transactions yet", "—", "—", "—"});
        }

        JTable table = new JTable(tableModel);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        table.setRowHeight(28);
        table.setBackground(Color.WHITE);
        table.setForeground(PRIMARY);
        table.setGridColor(LIGHT_BLUE);
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        table.getTableHeader().setBackground(PRIMARY);
        table.getTableHeader().setForeground(Color.WHITE);
        table.setSelectionBackground(LIGHT_BLUE);
        table.setSelectionForeground(Color.WHITE);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(20, 205, 380, 390);
        scrollPane.setBorder(BorderFactory.createLineBorder(LIGHT_BLUE, 1));
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        add(scrollPane);

        JButton backDashBTN = new JButton("Back to dashboard");
        backDashBTN.setBounds(20, 610, 380, 50);
        backDashBTN.setFont(new Font("Segoe UI", Font.BOLD, 15));
        backDashBTN.setBackground(Color.WHITE);
        backDashBTN.setForeground(PRIMARY);
        backDashBTN.setFocusPainted(false);
        backDashBTN.setBorder(BorderFactory.createLineBorder(LIGHT_BLUE, 1));
        backDashBTN.setOpaque(true);
        backDashBTN.setCursor(new Cursor(Cursor.HAND_CURSOR));
        add(backDashBTN);

        backDashBTN.addActionListener(e -> { mainWindow.setVisible(true); dispose(); });

        setVisible(true);
    }
}