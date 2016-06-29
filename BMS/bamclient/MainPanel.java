package bamclient;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class MainPanel extends JPanel {
    JButton registerButton = new JButton("¿ª»§");
    JButton loginButton = new JButton("µÇÂ¼");

    public MainPanel() {
        this.setLayout(new GridLayout(2, 1, 0, 40));
        this.setBorder(new EmptyBorder(40, 40, 40, 40));
        this.add(this.registerButton);
        this.add(this.loginButton);
    }

    public JButton getLoginButton() {
        return this.loginButton;
    }

    public JButton getRegisterButton() {
        return this.registerButton;
    }
}
