
package AccountManagement;

import java.awt.*;
import javax.swing.*;

public abstract class BaseFrame extends JFrame {

    protected static final Color NAVY       = new Color(25, 48, 90);
    protected static final Color ACCENT     = new Color(139, 172, 224);
    protected static final Color BG_COLOR   = new Color(200, 210, 230);
    protected static final Color RED_BTN    = new Color(200, 70, 70);
    protected static final Color WHITE      = Color.WHITE;
    protected static final Color LIGHT_GRAY = new Color(245, 245, 245);
    protected static final Color CARD_BG    = new Color(235, 240, 250);
    protected static final Color LIGHT_BLUE = new Color(139, 172, 224);

    public BaseFrame(String title) {
        setTitle(title);
        setSize(430, 720);
        setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(BG_COLOR);
    }

    protected JPanel buildCard(int x, int y, int w, int h) {
        JPanel card = new JPanel();
        card.setBackground(WHITE);
        card.setBounds(x, y, w, h);
        card.setLayout(null);
        return card;
    }

    protected abstract void initComponents();
}
