//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package model;

import Exception.BusinessException.BalanceNotEnoughException;

import java.io.*;

public abstract class Account implements Serializable, Cloneable {
    private long id;
    private String password;
    private String name;
    private String personId;
    private String email;
    private double balance;

    public Account() {
    }

    public Account(String password, String name, String personId, String email) {
        this();
        this.id = getNextId();
        this.password = password;
        this.name = name;
        this.personId = personId;
        this.email = email;
    }

    private static synchronized long getNextId() {
        DataInputStream in = null;
        long sid = 10000L;

        try {
            String root = System.getProperty("user.dir");
            FileInputStream out = new FileInputStream(root + "/BMS/Data/id.txt");
            in = new DataInputStream(out);
            sid = in.readLong();
        } catch (FileNotFoundException var34) {
        } catch (IOException var35) {
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException var29) {
            }

        }

        ++sid;
        DataOutputStream var37 = null;

        try {
            FileOutputStream fou = new FileOutputStream(".\\Data\\id.txt");
            var37 = new DataOutputStream(fou);
            var37.writeLong(sid);
        } catch (FileNotFoundException var31) {
        } catch (IOException var32) {
        } finally {
            try {
                if (var37 != null) {
                    var37.close();
                }
            } catch (IOException var30) {
            }

        }

        return sid;
    }

    public long getId() {
        return this.id;
    }

    public double getBalance() {
        return this.balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getEmail() {
        return this.email;
    }

    public String getName() {
        return this.name;
    }

    public String getPassword() {
        return this.password;
    }

    public String getPersonId() {
        return this.personId;
    }

    public final synchronized void deposit(double money) {
        this.balance += money;
    }

    public abstract void withdraw(double var1) throws BalanceNotEnoughException;

    public String toString() {
        return this.getName() + "\t" + this.getPersonId() + "\t" + this.getEmail();
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o == null) {
            return false;
        } else if (this.getClass() != o.getClass()) {
            return false;
        } else {
            Account c = (Account) o;
            boolean flag = true;
            if (!this.password.equals(c.password)) {
                flag = false;
            } else if (!this.name.equals(c.name)) {
                flag = false;
            } else if (!this.personId.equals(c.personId)) {
                flag = false;
            } else if (!this.email.equals(c.email)) {
                flag = false;
            } else if (this.getBalance() != c.getBalance()) {
                flag = false;
            }

            return flag;
        }
    }

    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException var2) {
            var2.printStackTrace();
            return null;
        }
    }

    public int hashCode() {
        return (new Long(this.id)).hashCode() ^ this.password.hashCode() ^ this.name.hashCode() ^ this.personId.hashCode() ^ this.email.hashCode() ^ (new Double(this.balance)).hashCode();
    }
}
