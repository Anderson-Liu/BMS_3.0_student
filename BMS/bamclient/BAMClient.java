package bamclient;

/*
 * BAMClient 其中会包含一个Frame,这是用户主界面
 * MainPanel:主界面,用户可以选择开户或者登录
 * RegisterPanel:用户开户填写详细信息用到的界面
 * LoginPanel:用户登录是填写信息时需要的界面
 * BusinessPanel:界面上会显示账户的功能至少包括存款和取款;
 * 对于可透支的用户,还允许用户修改透支额度;
 * 对于贷款用户,还允许用户贷款和还贷款;
 */

import model.Account;
import model.Info;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ConnectException;
import java.net.Socket;
import java.util.HashMap;

public class BAMClient {
    private JFrame frame = new JFrame("BAM Client");
    private MainPanel mp = new MainPanel();
    private RegisterPanel rp = new RegisterPanel();
    private LoginPanel lp = new LoginPanel();
    private BusinessPanel bp = new BusinessPanel();
    private CardLayout layout = new CardLayout();
    private Account account;
    private Socket s;
    private ObjectOutputStream out;
    private ObjectInputStream in;

    public BAMClient() throws Exception {
        this.frame.setSize(350, 300);
        this.frame.setDefaultCloseOperation(3);
        this.frame.setLayout(this.layout);
        this.frame.add("main", this.mp);
        this.frame.add("register", this.rp);
        this.frame.add("login", this.lp);
        this.frame.add("business", this.bp);
        this.frame.setVisible(true);
        this.addListeners();

        try {
            this.s = new Socket("127.0.0.1", 9000);
            this.out = new ObjectOutputStream(this.s.getOutputStream());
            this.in = new ObjectInputStream(this.s.getInputStream());
        } catch (ConnectException var2) {
            var2.printStackTrace();
        } catch (NullPointerException var3) {
            var3.printStackTrace();
        }

    }

    public static void main(String[] args) throws Exception {
        new BAMClient();
    }

    private void addListeners() {
        this.mp.getRegisterButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                BAMClient.this.layout.show(BAMClient.this.frame.getContentPane(), "register");
            }
        });
        this.mp.getLoginButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                BAMClient.this.layout.show(BAMClient.this.frame.getContentPane(), "login");
            }
        });
        this.rp.getOkButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                BAMClient.this.register();
            }
        });
        this.lp.getOkButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                BAMClient.this.login();
            }
        });
        this.bp.getOkButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                BAMClient.this.business();
            }
        });
    }

    private void register() {
        try {
            String e = new String(this.rp.getPassField1().getPassword());
            String pass2 = new String(this.rp.getPassField2().getPassword());
            String name = this.rp.getNameField().getText();
            String personId = this.rp.getPersonIdField().getText();
            String email = this.rp.getEmailField().getText();
            int type = this.rp.getTypeBox().getSelectedIndex();
            Info inf = new Info(0);
            HashMap m = inf.getData();
            m.put("pass1", e);
            m.put("pass2", pass2);
            m.put("name", name);
            m.put("personId", personId);
            m.put("email", email);
            m.put("type", new Integer(type));
            this.out.writeObject(inf);
            this.out.flush();
            Object o = this.in.readObject();
            if (o instanceof Account) {
                this.account = (Account) o;
                this.bp.initComponent(this.account);
                this.bp.setText(this.account);
                this.layout.show(this.frame.getContentPane(), "business");
                System.out.println("注册成功!");
            } else {
                JOptionPane.showMessageDialog(this.frame, o);
            }
        } catch (HeadlessException var10) {
            var10.printStackTrace();
        } catch (IOException var11) {
            var11.printStackTrace();
        } catch (ClassNotFoundException var12) {
            var12.printStackTrace();
        }

    }

    private void login() {
        try {
            String e = this.lp.getIdField().getText();
            String pass = new String(this.lp.getPassField().getPassword());
            long lid = Long.parseLong(e);
            Info inf = new Info(1);
            HashMap data = inf.getData();
            data.put("id", Long.valueOf(lid));
            data.put("pass", pass);
            this.out.writeObject(inf);
            this.out.flush();
            Object o = this.in.readObject();
            if (o instanceof Account) {
                this.account = (Account) o;
                this.bp.initComponent(this.account);
                this.bp.setText(this.account);
                this.layout.show(this.frame.getContentPane(), "business");
                System.out.println("登录成功 !");
            } else {
                JOptionPane.showMessageDialog(this.frame, o);
            }
        } catch (NumberFormatException var8) {
            var8.printStackTrace();
        } catch (HeadlessException var9) {
            var9.printStackTrace();
        } catch (IOException var10) {
            var10.printStackTrace();
        } catch (ClassNotFoundException var11) {
            var11.printStackTrace();
        }

    }

    private void business() {
        try {
            String e = (String) this.bp.getChoiceBox().getSelectedItem();
            String sm = this.bp.getInputField().getText();
            double money = Double.parseDouble(sm);
            long id = this.account.getId();
            Info inf = new Info(2);
            HashMap data = inf.getData();
            data.put("choice", e);
            data.put("money", Double.valueOf(money));
            data.put("id", Long.valueOf(id));
            this.out.writeObject(inf);
            this.out.flush();
            Object o = this.in.readObject();
            if (o instanceof Account) {
                this.account = (Account) o;
                this.bp.setText(this.account);
                System.out.println("交易成功!");
            } else {
                JOptionPane.showMessageDialog(this.frame, o);
            }
        } catch (NumberFormatException var10) {
            var10.printStackTrace();
        } catch (HeadlessException var11) {
            var11.printStackTrace();
        } catch (IOException var12) {
            var12.printStackTrace();
        } catch (ClassNotFoundException var13) {
            var13.printStackTrace();
        }

    }
}
