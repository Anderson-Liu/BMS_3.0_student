package bamclient;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class RegisterPanel extends JPanel {
    private JComboBox typeBox = new JComboBox();
    private JPasswordField passField1 = new JPasswordField();
    private JPasswordField passField2 = new JPasswordField();
    private JTextField nameField = new JTextField();
    private JTextField personIdField = new JTextField();
    private JTextField emailField = new JTextField();
    private JButton okButton = new JButton("提交");

    public RegisterPanel() {
        this.typeBox.addItem("储蓄账户");
        this.typeBox.addItem("信用账户");
        this.typeBox.addItem("贷款储蓄账户");
        this.typeBox.addItem("贷款信用账户");
        this.setBorder(new EmptyBorder(10, 0, 0, 20));
        this.setLayout(new BorderLayout());
        JPanel p = new JPanel();
        p.setLayout(new GridLayout(6, 2, 0, 10));
        p.add(new JLabel("账户类型:", 0));
        p.add(this.typeBox);
        p.add(new JLabel("账户密码:", 0));
        p.add(this.passField1);
        p.add(new JLabel("密码重复:", 0));
        p.add(this.passField2);
        p.add(new JLabel("用户姓名:", 0));
        p.add(this.nameField);
        p.add(new JLabel("身份证号:", 0));
        p.add(this.personIdField);
        p.add(new JLabel("电子邮箱:", 0));
        p.add(this.emailField);
        this.add(p);
        JPanel p2 = new JPanel();
        p2.add(this.okButton);
        this.add(p2, "South");
    }

    public JTextField getEmailField() {
        return this.emailField;
    }

    public JTextField getNameField() {
        return this.nameField;
    }

    public JPasswordField getPassField1() {
        return this.passField1;
    }

    public JPasswordField getPassField2() {
        return this.passField2;
    }

    public JTextField getPersonIdField() {
        return this.personIdField;
    }

    public JComboBox getTypeBox() {
        return this.typeBox;
    }

    public JButton getOkButton() {
        return this.okButton;
    }
}
