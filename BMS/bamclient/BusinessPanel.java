package bamclient;

import model.Account;
import model.CreditAccount;
import model.Loannable;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class BusinessPanel extends JPanel {
    private JLabel label1 = new JLabel("账户:", 0);
    private JLabel label2 = new JLabel("姓名:", 0);
    private JLabel label3 = new JLabel("余额:", 0);
    private JLabel label4 = new JLabel("信用额度:", 0);
    private JLabel label5 = new JLabel("贷款额:", 0);
    private JLabel accountLabel = new JLabel("", 0);
    private JLabel nameLabel = new JLabel("", 0);
    private JLabel balanceLabel = new JLabel("", 0);
    private JLabel ceilingLabel = new JLabel("", 0);
    private JLabel loanLabel = new JLabel("", 0);
    private JComboBox choiceBox = new JComboBox();
    private JTextField inputField = new JTextField(15);
    private JButton okButton = new JButton("提交");

    public BusinessPanel() {
        this.setBorder(new EmptyBorder(10, 0, 10, 0));
        this.setLayout(new BorderLayout());
        JPanel p = new JPanel();
        p.setLayout(new GridLayout(5, 2, 0, 10));
        p.add(this.label1);
        p.add(this.accountLabel);
        p.add(this.label2);
        p.add(this.nameLabel);
        p.add(this.label3);
        p.add(this.balanceLabel);
        p.add(this.label4);
        p.add(this.ceilingLabel);
        p.add(this.label5);
        p.add(this.loanLabel);
        this.add(p);
        JPanel p2 = new JPanel();
        this.choiceBox.addItem("存款");
        this.choiceBox.addItem("取款");
        p2.add(this.choiceBox);
        p2.add(this.inputField);
        p2.add(this.okButton);
        this.add(p2, "South");
    }

    public JComboBox getChoiceBox() {
        return this.choiceBox;
    }

    public JTextField getInputField() {
        return this.inputField;
    }

    public JButton getOkButton() {
        return this.okButton;
    }

    public void initComponent(Account c) {
        if (c instanceof CreditAccount) {
            this.choiceBox.addItem("设置透支额度");
        } else {
            this.label4.setEnabled(false);
        }

        if (c instanceof Loannable) {
            this.choiceBox.addItem("申请贷款");
            this.choiceBox.addItem("还贷款");
        } else {
            this.label5.setEnabled(false);
        }

    }

    public void setText(Account c) {
        this.accountLabel.setText(String.valueOf(c.getId()));
        this.nameLabel.setText(c.getName());
        this.balanceLabel.setText(String.valueOf(c.getBalance()));
        if (c instanceof CreditAccount) {
            CreditAccount l = (CreditAccount) c;
            this.ceilingLabel.setText(String.valueOf(l.getCeiling()));
        }

        if (c instanceof Loannable) {
            Loannable l1 = (Loannable) c;
            this.loanLabel.setText(String.valueOf(l1.getLoan()));
        }

    }
}
