

package bankmanagementapp;

import static bankmanagementapp.frontPage.LIGHT_BLUE;
import java.awt.*;
import javax.swing.*;
import dashboard_transactions.BankAppGUI;

public class logInPage extends JFrame {

    JLabel lblWelcome, lblTitle, lblUser, lblPass;
    JTextField tfUsername;
    JPasswordField tfPassword;
    JButton btnLogin, btnBack,bnCreate;
    JPanel cardPanel;

    public logInPage() {

        setTitle("Login Page");
        setSize(430, 720);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(null);

        JPanel bg = new JPanel();
        bg.setLayout(null);
        bg.setBackground(frontPage.LIGHT_BLUE);
        setContentPane(bg);

        JPanel navbar = new JPanel();
        navbar.setLayout(null);
        navbar.setBounds(0, 0, 430, 120);
        navbar.setBackground(new Color(20, 45, 95));
        bg.add(navbar);
        
        
        lblWelcome = new JLabel("WELCOME BACK");
        lblWelcome.setForeground(new Color(190, 200, 230));
        lblWelcome.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblWelcome.setBounds(35, 25, 200, 20);
        navbar.add(lblWelcome);

        lblTitle = new JLabel("Sign In");
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 32));
        lblTitle.setBounds(35, 45, 200, 40);
        navbar.add(lblTitle);

        cardPanel = new RoundedPanel(35);
        cardPanel.setLayout(null);
        cardPanel.setBackground(Color.WHITE);
        cardPanel.setBounds(35, 150, 340, 320);
        bg.add(cardPanel);

        JLabel lblCredential = new JLabel("YOUR CREDENTIALS");
        lblCredential.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblCredential.setForeground(new Color(90, 90, 90));
        lblCredential.setBounds(30, 25, 200, 20);
        cardPanel.add(lblCredential);

        lblUser = new JLabel("USER ID");
        lblUser.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblUser.setForeground(new Color(110, 110, 110));
        lblUser.setBounds(30, 60, 100, 20);
        cardPanel.add(lblUser);

        tfUsername = new JTextField();
        tfUsername.setBounds(30, 85, 280, 40);
        tfUsername.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        tfUsername.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        tfUsername.setBackground(new Color(245, 246, 252));
        cardPanel.add(tfUsername);

        lblPass = new JLabel("PASSWORD");
        lblPass.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblPass.setForeground(new Color(110, 110, 110));
        lblPass.setBounds(30, 135, 100, 20);
        cardPanel.add(lblPass);

        tfPassword = new JPasswordField();
        tfPassword.setBounds(30, 160, 280, 40);
        tfPassword.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        tfPassword.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        tfPassword.setBackground(new Color(245, 246, 252));
        cardPanel.add(tfPassword);

        JButton forgotBtn = new frontPage.RoundedButton("Forgot user ID or password?");
        forgotBtn.setBounds(65, 210, 220, 20);
        forgotBtn.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        forgotBtn.setForeground(new Color(90, 110, 180));

        cardPanel.add(forgotBtn);

        forgotBtn.addActionListener(e -> {
            new forgotCredentialPage().setVisible(true);
            dispose();
        });

        btnLogin = new frontPage.RoundedButton("LOGIN");
        btnLogin.setBounds(30, 245, 280, 42);

    btnLogin.setBackground(LIGHT_BLUE);
           btnLogin.setForeground(Color.white);
        btnLogin.setFont(new Font("Segoe UI", Font.BOLD, 15));
        cardPanel.add(btnLogin);



btnLogin.addActionListener(e -> {
    String id = tfUsername.getText();
    String password = String.valueOf(tfPassword.getPassword());

    try {
        java.sql.Connection con = accDatabase.getConnection();
        String query = "SELECT * FROM users WHERE user_id_str = ? AND password = ?";
        java.sql.PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, id);
        ps.setString(2, password);
        java.sql.ResultSet rs = ps.executeQuery();

        if (rs.next()) {
    int accountNumber = rs.getInt("account_number");
    new BankAppGUI(accountNumber);
    dispose();
} else {
            JOptionPane.showMessageDialog(this, "Incorrect User ID or Password");
        }
        con.close();
    } catch (Exception ex) {
        JOptionPane.showMessageDialog(this, "Database error: " + ex.getMessage());
    }
});

        btnBack = new frontPage.RoundedButton("Back to Homepage");
        btnBack.setBounds(35, 510, 340, 42);
        btnBack.setBackground(new Color(220, 226, 241));
        btnBack.setForeground(new Color(70, 70, 70));
        btnBack.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        bg.add(btnBack);

        btnBack.addActionListener(e -> {
            new frontPage().setVisible(true);
            dispose();
        });

        bnCreate = new frontPage.RoundedButton("Don't have an account yet? Create one");
        bnCreate.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        bnCreate.setForeground(new Color(120, 120, 120));
        bnCreate.setBounds(80, 590, 250, 20);
        bg.add(bnCreate);
            
           bnCreate.addActionListener(e -> {
            new accounts().setVisible(true);
            dispose();
        });

        
        setVisible(true);
    }

    class RoundedPanel extends JPanel {

        private int radius;

        RoundedPanel(int radius) {
            this.radius = radius;
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {

            Graphics2D g2 = (Graphics2D) g.create();

            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);

            g2.setColor(getBackground());
            g2.fillRoundRect(0, 0, getWidth(), getHeight(),
                    radius, radius);

            g2.dispose();

            super.paintComponent(g);
        }
    }
}
