package bankmanagementapp;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;


public class frontPage extends JFrame{
    
    JPanel  navbar, mainPanel ,heroCard ;//dark blue sa taas,baby blue na bg, white the navbar
    JPanel  sep ;
    JButton btnLogin;         // LOG IN btn
    JButton btnForgot;        // FORGOT USER-ID OR PASSWORD btn
    JButton btnCreate;          // CREATW btn
    JLabel statement,statemnt1, navTite, appTit, tagLine ,logoLabel;
   
    Color bgWhite;
    
      public frontPage(){
          
          
          setSize(430, 720);
          setLocationRelativeTo(null);
          setTitle("Bank Account Management");
          setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
          setLayout(null);
          setResizable(false);
          
          
          mainPanel = new JPanel();
          mainPanel.setLayout(null);
          mainPanel.setBackground(BG);
          setContentPane(mainPanel);// ito main Panel ung baby blue na bg

    
          
         navbar = new JPanel(null);
         navbar.setBounds(0, 0, 430, 90);
         navbar.setBackground(PRIMARY); // primary blue -- navbar sa taas na may banklogo etc
         navbar.setLayout(null);
         mainPanel.add(navbar);
         
         
        logoLabel = new JLabel();        //logo ng bank app
        logoLabel.setBounds(16, 15, 65, 65);   // position and size of logo in navbar
        ImageIcon logoIcon = loadImage("/images/bank_logo.png", 70, 70);
        if (logoIcon != null) {
            logoLabel.setIcon(logoIcon);        // show your Canva logo
        } else {
            // if image not found show a placeholder letter "S"
            logoLabel.setText("S");
            logoLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
            logoLabel.setForeground(LIGHT_BLUE);
        }
           navbar.add(logoLabel);
         
          statement = new JLabel("WELCOME TO STATE-BANK APP ");
          statement.setForeground(LIGHT_BLUE);
          statement.setBounds(90, 18, 300, 16);
          statement.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 15));
          navbar.add(statement);

        navTite = new JLabel("Good Day!");
        navTite.setForeground(Color.WHITE);
        navTite.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 26));
        navTite.setBounds(90, 34, 300, 36);
        navbar.add(navTite);

        heroCard = buildRoundedPanel(20);
        heroCard.setBounds(20, 112, 370, 500);
        heroCard.setLayout(null);
        mainPanel.add(heroCard);

        JPanel logoCircle = new JPanel();
        logoCircle.setBackground(LIGHT_BLUE);
        logoCircle.setBounds(40, 40, 52, 52);
        heroCard.add(logoCircle);
            
        
        appTit = new JLabel("STATE BANK APP");
        appTit.setBounds(105, 40, 280, 28);
        appTit.setFont(new java.awt.Font("Segoe UI", Font.BOLD, 25));
        appTit.setForeground(TEXT_DARK);
        heroCard.add(appTit);
        
        tagLine = new JLabel ("Your trusted digital banking partner");
        tagLine.setBounds(110,70, 280, 18);
        tagLine.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        tagLine.setForeground(TEXT_MUTED);
        heroCard.add(tagLine);

        
sep = new JPanel();// ung nag didivide sa white na panel(eme emehan lang)
sep.setBackground(BG);
sep.setBounds(0, 120, 400, 30);
heroCard.add(sep);
        
          statemnt1 = new JLabel("WHAT CAN I DO FOR YOU TODAY? ");
          statemnt1.setBounds(18, 170, 280, 18);
          statemnt1.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 14));
          statemnt1.setForeground(TEXT_MUTED);
          heroCard.add(statemnt1);

         

     //  Button 1
        btnLogin = makeIconButton(
            "LOG IN",                    //  text ng btn
            "/images/login_icon.png"     // your Canva icon file
        );
        btnLogin.setBounds(30, 230, 310, 50); // position sa loob ng hero card
        heroCard.add(btnLogin);
        
        btnLogin.addActionListener(e -> {
            new logInPage();
            this.dispose();
        });
 
        // Button 2 
        btnForgot = makeIconButton(
            "FORGOT USER-ID OR PASSWORD",
            "/images/forgot_icon.png"
        );
        btnForgot.setBounds(30, 300, 310, 50);
        heroCard.add(btnForgot);

        btnForgot.addActionListener(e -> {
            new forgotCredentialPage();
            this.dispose();
        });
 
        // Button 3
        btnCreate = makeIconButton(
            "CREATE AN ACCOUNT",
            "/images/create_icon.png"
        );
        btnCreate.setBounds(30, 370, 310, 50);
        heroCard.add(btnCreate);

        btnCreate.addActionListener(e -> {
            new accounts();
            this.dispose();
        });

          setVisible(true);
          
          
          
          
      }
      
      
          
    public static final Color PRIMARY      = new Color(25, 48, 90);   // # dark blue
    public static final Color LIGHT_BLUE   = new Color(139, 172, 224); // #8bace0
    public  static final Color BG           = new Color(200, 210, 230); // baby blue
    public static final Color CARD_BG      = Color.WHITE; // white sa hero card
    public static final Color TEXT_DARK    = new Color(25, 48, 90); // app name
    public static final Color TEXT_MUTED   = new Color(100, 110, 130);// sa tagline & stmnt1
    public static final Color ICON_BG      = new Color(232, 238, 248); // soft blue tint
     
     
    public JButton makeIconButton(String text, String imagePath) {
 
        // load the Canva icon (36x36 px size inside the button)
        ImageIcon icon = loadImage(imagePath, 36, 36);
 
        // create the button — if icon loaded, show icon + text
        JButton btn = new JButton(text, icon);
 
        // put the icon on the LEFT side of the text
        btn.setHorizontalAlignment(SwingConstants.LEFT);
        btn.setIconTextGap(12);  // spacing ng icon and text
 
        // styling — keep it clean like your RoundedButton
        btn.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        btn.setBackground(LIGHT_BLUE);
        btn.setForeground(Color.BLACK);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setContentAreaFilled(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR)); // hand cursor siya
 
        //  left padding ng icon
        btn.setBorder(BorderFactory.createEmptyBorder(0, 14, 0, 0));
 
        //more on design at shapes ng button
        JButton roundBtn = new JButton(text, icon) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                // make edges smooth
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                                    RenderingHints.VALUE_ANTIALIAS_ON);
                // dadark if mouse hover
                if (getModel().isRollover()) {
                    g2.setColor(LIGHT_BLUE.darker());
                } else {
                    g2.setColor(LIGHT_BLUE);
                }
                // draw the rounded rectangle background
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
                g2.dispose();
                super.paintComponent(g); // draws the icon and text on top
            }
        };
 
        // apply the same settings to roundBtn
        roundBtn.setHorizontalAlignment(SwingConstants.LEFT);
        roundBtn.setIconTextGap(12);
        roundBtn.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        roundBtn.setForeground(Color.BLACK);
        roundBtn.setFocusPainted(false);
        roundBtn.setBorderPainted(false);
        roundBtn.setContentAreaFilled(false);
        roundBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        roundBtn.setBorder(BorderFactory.createEmptyBorder(0, 14, 0, 0));
        roundBtn.setIcon(icon); // attach the Canva icon
 
        return roundBtn;
    }
 
  
    //  HELPER METHOD 2: buildRoundedPanel()
    //
    //  Creates a plain white JPanel with ROUNDED corners.
    //  "cornerRadius" controls how round the corners are.
    //  Bigger number = more round.
  
    public JPanel buildRoundedPanel(int cornerRadius) {
        JPanel panel = new JPanel(null) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                                    RenderingHints.VALUE_ANTIALIAS_ON);
                // fill the rounded rectangle with white
                g2.setColor(CARD_BG);
                g2.fill(new RoundRectangle2D.Float(
                    0, 0, getWidth(), getHeight(),
                    cornerRadius, cornerRadius));
                g2.dispose();
                super.paintComponent(g);
            }
 
            @Override
            protected void paintBorder(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                                    RenderingHints.VALUE_ANTIALIAS_ON);
                // draw a thin grey border around the rounded rectangle
                g2.setColor(new Color(210, 218, 235));
                g2.setStroke(new BasicStroke(1f));
                g2.draw(new RoundRectangle2D.Float(
                    0.5f, 0.5f, getWidth() - 1, getHeight() - 1,
                    cornerRadius, cornerRadius));
                g2.dispose();
            }
        };
        panel.setOpaque(false); 
        return panel;
    }
         
      
       public ImageIcon loadImage(String path, int width, int height) {
        try {
            // getClass().getResource() looks inside your src/ folder
            java.net.URL imgURL = getClass().getResource(path);
 
            if (imgURL == null) {
                // file not found (button still works, just no icon)
                return null;
            }
 
            // load ,resize
            Image img = new ImageIcon(imgURL).getImage()
                            .getScaledInstance(width, height, Image.SCALE_SMOOTH);
            return new ImageIcon(img);
 
        } catch (Exception e) {
            return null; // always safe 
        }
        
        
        
       }
}

      

   
