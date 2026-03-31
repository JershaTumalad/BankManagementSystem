import javax.swing.*;
import javax.swing.table.*;
import java.util.*;


public class HistoryGUI extends JFrame {
    private TransactionManager manager;
    String[] columns = {"ID", "Type", "Amount", "Date", "Status"};
    JTable table;
    JButton backBTN;

    public HistoryGUI(TransactionManager manager, BankAppGUI mainWindow) {
        this.manager = manager;
        setTitle("Transaction History");
        setSize(390, 700);
        setLayout(null);
        setLocationRelativeTo(null);
        
        DefaultTableModel tableModel = new DefaultTableModel(columns, 0);

        ArrayList<Transaction> list = manager.getTransactionList();
        for(Transaction t : list) {
            tableModel.addRow(new Object[]{
                t.getTransactionID(),
                t.getTransactionType(),
                t.getTransactionType().equals("Deposit") ? "+PHP " + String.format("%.2f", t.getAmount()) : "-PHP " + String.format("%.2f", t.getAmount()),
                t.getDate(),
                t.getStatus()
            });
        }
        
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(20, 80, 340, 500);
        add(scrollPane);
        
        backBTN= new JButton("Back");
        backBTN.setLayout(null);
        backBTN.setBounds(20, 15, 80, 40);
        add(backBTN);
        
        backBTN.addActionListener(e -> {
            mainWindow.setVisible(true); 
            dispose();                   
        });

        setVisible(true);
    }
}