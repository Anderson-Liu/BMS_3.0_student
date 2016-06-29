package bamclient;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class LoginPanel extends JPanel {
    private JTextField idField;
    private JPasswordField passField;
    private JButton okButton;

    public LoginPanel() {
        idField = new JTextField();
        passField = new JPasswordField();
        okButton = new JButton("提交");//起别名.

        this.setLayout(new BorderLayout());

        JPanel p = new JPanel();
        p.setLayout(new GridLayout(2, 2, 0, 60));
        p.setBorder(new EmptyBorder(40, 0, 50, 30));
        p.add(new JLabel("账号:", SwingConstants.CENTER));
        p.add(idField);
        p.add(new JLabel("密码:", SwingConstants.CENTER));
        p.add(passField);

        this.add(p);

        JPanel p2 = new JPanel();
        p2.add(okButton);
        this.add(p2, "South");

    }

    public JTextField getIdField() {
        return idField;
    }

    public JButton getOkButton() {
        return okButton;
    }

    public JPasswordField getPassField() {
        return passField;
    }
}