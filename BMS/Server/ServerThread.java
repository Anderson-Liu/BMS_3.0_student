package Server;
/*
 * 服务器端线程，每个线程为一个客户端提供服务，多线程实现并发访问
 */

import Exception.BusinessException;
import business.Bank;
import model.Account;
import model.Info;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;

public class ServerThread extends Thread {
    private Socket s;
    private ObjectOutputStream out;
    private ObjectInputStream in;

    public ServerThread(Socket s) throws Exception {
        this.s = s;
        this.out = new ObjectOutputStream(s.getOutputStream());
        this.in = new ObjectInputStream(s.getInputStream());
    }

    public void run() {
        try {
            while (true) {
                try {
                    while (true) {
                        Object e = this.in.readObject();
                        if (e == null) {
                            System.out.println("客户端下线或者网络故障 !");
                            return;
                        }

                        Info inf = (Info) e;
                        HashMap data = inf.getData();
                        Account account = null;
                        Bank bank = Bank.newBank();
                        String choice;
                        if (inf.getType() == 0) {
                            choice = (String) data.get("pass1");
                            String money2 = (String) data.get("pass2");
                            String name = (String) data.get("name");
                            String id1 = (String) data.get("personId");
                            String email = (String) data.get("email");
                            int type = ((Integer) data.get("type")).intValue();
                            account = bank.register(choice, money2, name, id1, email, type);
                            this.out.writeObject(account.clone());
                            this.out.flush();
                        } else if (inf.getType() != 1) {
                            choice = (String) data.get("choice");
                            double money1 = ((Double) data.get("money")).doubleValue();
                            long id = ((Long) data.get("id")).longValue();
                            if (choice.equals("存款")) {
                                account = bank.deposit(id, money1);
                                System.out.println(account.getBalance());
                            } else if (choice.equals("取款")) {
                                account = bank.withdraw(id, money1);
                            } else if (choice.equals("设置透支额度")) {
                                account = bank.setCeiling(id, money1);
                            } else if (choice.equals("申请贷款")) {
                                account = bank.requestLoan(id, money1);
                            } else {
                                account = bank.payLoan(id, money1);
                            }

                            this.out.writeObject(account.clone());
                            this.out.flush();
                        } else {
                            choice = (String) data.get("pass");
                            long money = ((Long) data.get("id")).longValue();
                            account = bank.login(money, choice);
                            this.out.writeObject(account.clone());
                            this.out.flush();
                        }
                    }
                } catch (BusinessException var12) {
                    this.out.writeObject(var12.getMessage());
                    this.out.flush();
                }
            }
        } catch (IOException var13) {
        } catch (ClassNotFoundException var14) {
        }

    }
}

