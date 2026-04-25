package BAMS;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.util.*;

public class HistoryGUI extends JFrame {

    private TransactionManager manager;

    public HistoryGUI(TransactionManager manager, BankAppGUI mainWindow) {
        this.manager = manager;

        setTitle("Transaction History");
        setSize(400, 700);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setBackground(new Color(245, 245, 250));

        JPanel header = new JPanel(null);
        header.setBounds(0, 0, 420, 60);
        header.setBackground(new Color(30, 50, 100));
        add(header);

        JButton backBTN = new JButton("← Back");
        backBTN.setBounds(8, 12, 80, 30);
        backBTN.setBackground(new Color(50, 80, 160));
        backBTN.setForeground(Color.WHITE);
        backBTN.setFocusPainted(false);
        backBTN.setFont(new Font("Arial", Font.BOLD, 11));
        backBTN.setBorderPainted(false);
        backBTN.setOpaque(true);
        backBTN.setCursor(new Cursor(Cursor.HAND_CURSOR));
        header.add(backBTN);

        JLabel title = new JLabel("Transaction History");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Arial", Font.BOLD, 16));
        title.setBounds(100, 15, 250, 30);
        header.add(title);

        JLabel balLbl = new JLabel("Current Balance: PHP " + String.format("%.2f", manager.getBalance()));
        balLbl.setBounds(20, 70, 380, 25);
        balLbl.setFont(new Font("Arial", Font.BOLD, 13));
        balLbl.setForeground(new Color(30, 50, 100));
        add(balLbl);

        String[] columns = {"ID", "Type", "Amount", "Date", "Status"};
        DefaultTableModel tableModel = new DefaultTableModel(columns, 0) {
            public boolean isCellEditable(int row, int col) { return false; }
        };

        ArrayList<Transaction> list = manager.getTransactionList();
        for (Transaction t : list) {
            String amtStr = t.getTransactionType().equals("Deposit")
                    ? "+PHP " + String.format("%.2f", t.getAmount())
                    : "-PHP " + String.format("%.2f", t.getAmount());
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
        table.setFont(new Font("Arial", Font.PLAIN, 12));
        table.setRowHeight(28);
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        table.getTableHeader().setBackground(new Color(30, 50, 100));
        table.getTableHeader().setForeground(Color.WHITE);
        table.setSelectionBackground(new Color(180, 200, 255));

        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            public Component getTableCellRendererComponent(JTable tbl, Object value,
                    boolean isSelected, boolean hasFocus, int row, int col) {
                Component c = super.getTableCellRendererComponent(tbl, value, isSelected, hasFocus, row, col);
                if (!isSelected) {
                    String type = (String) tbl.getValueAt(row, 1);
                    if (type.equals("Deposit"))         c.setBackground(new Color(230, 255, 230));
                    else if (type.equals("Withdrawal")) c.setBackground(new Color(255, 230, 230));
                    else                                c.setBackground(new Color(245, 245, 255));
                }
                return c;
            }
        });

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(20, 105, 370, 420);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 220)));
        add(scrollPane);

        backBTN.addActionListener(e -> { mainWindow.setVisible(true); dispose(); });

        setVisible(true);
    }
}