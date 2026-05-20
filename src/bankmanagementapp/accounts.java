
package bankmanagementapp;

    


import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.util.*;



    public class accounts extends JFrame {

    JTextField tfEmail, tfuserId, tfpass, tfMobNum;
         JButton submit, logInPage;
    JComboBox cbAccType;

    public accounts() {

                    setSize(430, 720);
                setLocationRelativeTo(null);
                     setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                     setTitle("Account Registration");
                      setResizable(false);

        JPanel mainPanel = new JPanel();
                mainPanel.setLayout(null);
        mainPanel.setBackground(BG);
        setContentPane(mainPanel);

      
            JPanel headerCard = new JPanel(null);
        headerCard.setBounds(10, 15, 390, 60);
                headerCard.setBackground(PRIMARY);
        mainPanel.add(headerCard);

        JLabel titleLabel = new JLabel("Create an Account");
        titleLabel.setFont(FONT_TITLE);
            titleLabel.setForeground(Color.WHITE);
        titleLabel.setBounds(18, 8, 260, 26);
      headerCard.add(titleLabel);

              JLabel subLabel = new JLabel("Fill in the details below to get started.");
        subLabel.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        subLabel.setForeground(LIGHT_BLUE);
              subLabel.setBounds(18, 34, 230, 16);
        headerCard.add(subLabel);

    
            JPanel formCard = new JPanel(null);
        formCard.setBounds(10, 85, 390, 580);
            formCard.setBackground(CARD_BG);
        mainPanel.add(formCard);

        int labelX = 20, fieldX = 150, w = 220, h = 30;
        int y = 20, gap = 45;

    
        addField(formCard, "First Name", y + gap * 0, labelX, fieldX, w, h);
            addField(formCard, "Last Name",  y + gap * 1, labelX, fieldX, w, h);
        addField(formCard, "Middle Name",y + gap * 2, labelX, fieldX, w, h);

        tfMobNum = addField(formCard, "Mobile No.", y + gap * 3, labelX, fieldX, w, h);
        addField(formCard, "Address", y + gap * 4, labelX, fieldX, w, h);

       
        addLabel(formCard, "Account Type", y + gap * 5, labelX);

                cbAccType = new JComboBox<>(new String[]{"SAVINGS ACCOUNT", "CHECKING ACCOUNT"});
        cbAccType.setBounds(fieldX, y + gap * 5, w, h);
        styleCombo(cbAccType);
         formCard.add(cbAccType);

        addLabel(formCard, "Birthdate", y + gap * 6, labelX);

     JComboBox<String> month = new JComboBox<>(new String[]{"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"});
        JComboBox<String> day = new JComboBox<>();
        JComboBox<String> year = new JComboBox<>();

        for (int i = 1; i <= 31; i++) day.addItem(String.valueOf(i));
        int cy = Calendar.getInstance().get(Calendar.YEAR);
        for (int i = 0; i < 100; i++) year.addItem(String.valueOf(cy - i));

             month.setBounds(fieldX, y + gap * 6, 70, h);
        day.setBounds(fieldX + 75, y + gap * 6, 60, h);
        year.setBounds(fieldX + 140, y + gap * 6, 80, h);

       
        
        styleCombo(month);
        styleCombo(day);
        
            styleCombo(year);

        formCard.add(month);
        formCard.add(day);
            formCard.add(year);

        tfEmail  = addField(formCard, "Email", y + gap * 7, labelX, fieldX, w, h);
        tfuserId = addField(formCard, "User ID", y + gap * 8, labelX, fieldX, w, h);
            tfpass   = addField(formCard, "Password", y + gap * 9, labelX, fieldX, w, h);

      
        submit = new JButton("CREATE ACCOUNT");
             submit.setBounds(80, 500, 230, 35);
              submit.setBackground(PRIMARY);
        submit.setForeground(Color.WHITE);
        formCard.add(submit);

        logInPage = new JButton("BACK TO LOGIN");
             logInPage.setBounds(80, 540, 230, 35);
        logInPage.setBackground(LIGHT_BLUE);
        logInPage.setForeground(PRIMARY);
              formCard.add(logInPage);

        setVisible(true);
    }

    
    private JTextField addField(JPanel panel, String label, int y, int lx, int fx, int w, int h) {

        JLabel lbl = new JLabel(label);
            lbl.setBounds(lx, y + 5, 120, 20);
        lbl.setFont(FONT_LABEL);
        panel.add(lbl);

        JTextField tf = new JTextField();
        tf.setBounds(fx, y, w, h);
            tf.setFont(FONT_FIELD);
        tf.setBorder(BorderFactory.createLineBorder(LIGHT_BLUE));
        panel.add(tf);

        return tf;
    }

    private void addLabel(JPanel panel, String text, int y, int x) {
        JLabel lbl = new JLabel(text);
            lbl.setBounds(x, y + 5, 120, 20);
        lbl.setFont(FONT_LABEL);
        panel.add(lbl);
    }

    private void styleCombo(JComboBox<?> cb) {
        cb.setFont(FONT_FIELD);
        cb.setBackground(Color.WHITE);
             cb.setBorder(BorderFactory.createLineBorder(LIGHT_BLUE));
    }
        
    
    public final Font FONT_LABEL = new Font("Segoe UI", Font.BOLD, 13);
    
    
        public final Font FONT_FIELD = new Font("Segoe UI", Font.PLAIN, 13);
         public final Font FONT_TITLE = new Font("Segoe UI", Font.BOLD, 18);

    public final Color PRIMARY = new Color(25, 48, 90);
        public final Color LIGHT_BLUE = new Color(139, 172, 224);
            public final Color BG = new Color(200, 210, 230);
    public final Color CARD_BG = Color.WHITE;
}