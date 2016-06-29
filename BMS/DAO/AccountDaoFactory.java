package DAO;

/*
 * DAO工厂,用来构建出不同的DAO实现;
 * 通过配置文件来获得不同的DAO实现;
 * 所以工厂要通过读配置文件,来确定创建具体的DAO实现;
 * 
 * DAO工厂也是单例模式,
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.reflect.Constructor;

public class AccountDaoFactory {
    private static AccountDaoFactory factory = new AccountDaoFactory();

    private AccountDaoFactory() {
    }

    public static AccountDaoFactory newFactory() {
        return factory;
    }

    public AccountDAO getAccountDao() {
        Object account = null;

        try {
            String root = System.getProperty("user.dir");
            FileReader e = new FileReader(root + "/BMS/Data/confige.txt");
            BufferedReader fin = new BufferedReader(e);
            String daoname = fin.readLine();
            String filename = root + fin.readLine();
            fin.close();
            Class name = Class.forName(daoname);
            Constructor con = name.getConstructor(String.class);
            account = con.newInstance(filename);
        } catch (Exception var8) {
            var8.printStackTrace();
        }

        return (AccountDAO) account;
    }
}
