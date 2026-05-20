//
//package bankmanagementapp;
//
//import java.awt.*;
//import javax.swing.*;
//import java.awt.event.*;
//import java.util.ArrayList;
//
////email needed
//
//
//public class forgotPage2 extends JFrame {
//    
//     ArrayList <accCreationPage> acc;
//
//    
//    JLabel Title, desc,email, navTit;
//    JTextField tfEmail;
//    JButton submit, homepage;
//    JPanel navbar;
//    
//    public forgotPage2(){
//        
//      this.acc = accDatabase.acc;
//      
//        setVisible(true);
//        setSize(430, 720);
//        setLocationRelativeTo(null);
//        setLayout(null);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//    
//        
//       
//        JPanel mainPanel = new JPanel();
//        mainPanel.setBackground(new Color(200, 210, 230));
//        mainPanel.setLayout(null);
//        setContentPane(mainPanel);
//        
//        
//        
//        navbar = new JPanel();
//         navbar.setBounds(0, 0, 430, 100);
//         navbar.setBackground(theme.Primary); // primary blue
//         navbar.setLayout(null);
//         add(navbar);
//         
//        navTit = new JLabel("FORGOT MY ACCOUNT LOGIN");
//        navTit.setForeground(Color.WHITE);
//        navTit.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 20));
//        navTit.setBounds(50, 25, 300, 30);
//        navbar.add(navTit);
//        
//        desc= new JLabel("Enter your registered email address");
//        desc.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 14));
//        add(desc);
//        desc.setBounds(50, 160, 400, 30);
//        
//         email= new JLabel("Email: ");
//         email.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 14));
//        add(email);
//        email.setBounds(60, 230, 100, 30);
//        
//        tfEmail= new JTextField();
//        add(tfEmail);
//        tfEmail.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 14));
//        tfEmail.setBorder(null);
//        tfEmail.setBounds(110, 230, 220, 35);
//        
//         submit= new RoundedButton("Submit");
//        submit.setBounds(135, 320, 150, 40);
//        submit.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 14));
//         submit.setBackground(theme.Light_blue);
//        add(submit);
//        submit.addActionListener(e ->{
//        
//            String inputEmail = tfEmail.getText();
//            boolean found = false;
//            
//            for(accCreationPage user: acc){
//                 
//            
//            if(user.getEmail().equals(inputEmail)){
//                
//            found= true;
//            break;
//            }}
//                    
//            
//            if(found){JOptionPane.showMessageDialog(this, "Email Found!");
//                    new logInPage();
//                    this.dispose();
//            } else{
//                    JOptionPane.showMessageDialog(this, "Email not registered!");
//                }
//        });
//
//            homepage = new RoundedButton ("Back to Homepage");
//            homepage.setBounds(110, 450, 200, 40);
//            homepage.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 14));
//             homepage.setBackground(theme.Light_blue);
//                 add(homepage); 
//            homepage.addActionListener( e-> {
//            
//            new frontPage().setVisible(true);
//            this.dispose();
//            
//            
//            });
//
//    
//
//        }
//    
//     public class theme {
//          public static final Color Primary = new Color (25,42,86);
//        public static final Color Light_blue = new Color(0x8bace0);
//          public static final Color bg = new Color (230, 235, 245);
//          public static final Color text_d = new Color (40,40,40);
//         
//          
//          
//      }
//                }
//    
package bankmanagementapp;

import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;

public class forgotPage2 extends JFrame {

    ArrayList<accCreationPage> acc;

    JLabel lblMiniTitle, lblTitle, lblDesc, lblEmail;
    JTextField tfEmail;
    JButton btnSubmit, btnBack;

    public forgotPage2() {

        this.acc = accDatabase.acc;

        setTitle("Forgot Account Login");
        setSize(430, 720);
        setLocationRelativeTo(null);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        JPanel bg = new JPanel();
        bg.setBackground(new Color(220, 226, 241));
        bg.setLayout(null);
        setContentPane(bg);

        JPanel navbar = new JPanel();
        navbar.setBounds(0, 0, 430, 120);
        navbar.setBackground(new Color(20, 45, 95));
        navbar.setLayout(null);
        bg.add(navbar);

        lblMiniTitle = new JLabel("ACCOUNT RECOVERY");
        lblMiniTitle.setForeground(new Color(190, 200, 230));
        lblMiniTitle.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblMiniTitle.setBounds(35, 25, 250, 20);
        navbar.add(lblMiniTitle);

        lblTitle = new JLabel("Forgot Login");
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 28));
        lblTitle.setBounds(35, 45, 320, 40);
        navbar.add(lblTitle);

        RoundedPanel card = new RoundedPanel(35);
        card.setLayout(null);
        card.setBackground(Color.WHITE);
        card.setBounds(35, 170, 340, 270);
        bg.add(card);

        lblDesc = new JLabel("Enter your registered email address");
        lblDesc.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        lblDesc.setForeground(new Color(100, 100, 100));
        lblDesc.setBounds(30, 30, 260, 20);
        card.add(lblDesc);

        lblEmail = new JLabel("EMAIL ADDRESS");
        lblEmail.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblEmail.setForeground(new Color(110, 110, 110));
        lblEmail.setBounds(30, 75, 150, 20);
        card.add(lblEmail);

        tfEmail = new JTextField();
        tfEmail.setBounds(30, 100, 280, 42);
        tfEmail.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        tfEmail.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        tfEmail.setBackground(new Color(245, 246, 252));
        card.add(tfEmail);

        btnSubmit = new RoundedButton("Submit");
        btnSubmit.setBounds(30, 180, 280, 42);
        btnSubmit.setBackground(new Color(138, 173, 255));
        btnSubmit.setForeground(Color.WHITE);
        btnSubmit.setFont(new Font("Segoe UI", Font.BOLD, 15));
        card.add(btnSubmit);

        btnSubmit.addActionListener(e -> {

            String inputEmail = tfEmail.getText();
            boolean found = false;

            for (accCreationPage user : acc) {

                if (user.getEmail().equals(inputEmail)) {

                    found = true;
                    break;
                }
            }

            if (found) {

                JOptionPane.showMessageDialog(this,
                        "Email Found!");

                new logInPage();
                dispose();

            } else {

                JOptionPane.showMessageDialog(this,
                        "Email not registered!");

            }
        });

        btnBack = new RoundedButton("Back to homepage");
        btnBack.setBounds(35, 520, 340, 42);
        btnBack.setBackground(new Color(220, 226, 241));
        btnBack.setForeground(new Color(70, 70, 70));
        btnBack.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        bg.add(btnBack);

        btnBack.addActionListener(e -> {

            new frontPage().setVisible(true);
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

    public class theme {

        public static final Color Primary = new Color(25, 42, 86);
        public static final Color Light_blue = new Color(0x8bace0);
        public static final Color bg = new Color(230, 235, 245);
        public static final Color text_d = new Color(40, 40, 40);

    }
}
