package DAO;

/*
 * DAO����,������������ͬ��DAOʵ��;
 * ͨ�������ļ�����ò�ͬ��DAOʵ��;
 * ���Թ���Ҫͨ���������ļ�,��ȷ�����������DAOʵ��;
 * 
 * DAO����Ҳ�ǵ���ģʽ,
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
