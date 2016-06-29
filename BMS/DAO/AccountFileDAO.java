package DAO;

/*
 * 文件DAO的实现,将系统中的所有有用数据信息保存到文件中;
 * saveAccounts(Map map):将传过来的Map中的数据保存到指定(name文件名)的文件中;
 * loadAccounts():以自己的id为key值
 */

import model.Account;

import java.io.*;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

class AccountFileDAO implements AccountDAO {
    private String name;

    public AccountFileDAO(String name) {
        this.name = name;
    }

    public void saveAccounts(HashMap map) {
        ObjectOutputStream out = null;

        try {
            FileOutputStream e = new FileOutputStream(this.name);
            out = new ObjectOutputStream(e);
            Collection c = map.values();
            Iterator it = c.iterator();

            while (it.hasNext()) {
                Account a = (Account) it.next();
                out.writeObject(a);
            }
        } catch (FileNotFoundException var16) {
        } catch (IOException var17) {
            var17.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException var15) {
                var15.printStackTrace();
            }

        }

    }

    public HashMap loadAccounts() {
        HashMap map = null;
        ObjectInputStream in = null;

        try {
            map = new HashMap();
            FileInputStream e = new FileInputStream(this.name);
            in = new ObjectInputStream(e);

            while (true) {
                Account ac = (Account) in.readObject();
                map.put(new Long(ac.getId()), ac);
            }
        } catch (FileNotFoundException var14) {
        } catch (IOException var15) {
        } catch (ClassNotFoundException var16) {
            var16.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException var13) {
                var13.printStackTrace();
            }

        }

        return map;
    }
}

