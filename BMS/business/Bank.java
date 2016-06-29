package business;

/*
 * ��������Bank���ɵ���ģʽ
 * ��Ϊ������BankҪά���ܶ෽��������,ÿ����һ��ʵ���ͺ��úܴ���ڴ�,
 * ����������ɵ���ģʽ
 *
 * Bank����κ�һ���޸Ŀͻ����ݵĲ�����Ҫ����һ�α���,
 * ���޸ĺ�Ľ�����±��浽�ļ���;
 * ��Ϊ�˱�֤�ڴ��еĿͻ������ݺ��ļ��пͻ������ݱ���һ��;
 * 0��ʾ�����˻�,1��ʾ�����˻�
 */

import DAO.AccountDAO;
import DAO.AccountDaoFactory;
import Exception.BusinessException.BalanceNotEnoughException;
import Exception.BusinessException.LoanException;
import Exception.BusinessException.LoginException;
import Exception.BusinessException.RegisterException;
import model.*;

import java.util.*;

public class Bank {
    private static Bank bank = null;
    private HashMap map1;
    private AccountDAO dao;

    private Bank() {
        AccountDaoFactory factory = AccountDaoFactory.newFactory();
        this.dao = factory.getAccountDao();
        this.map1 = this.dao.loadAccounts();
    }

    public static Bank newBank() {
        if (bank == null) {
            bank = new Bank();
        }

        return bank;
    }

    public static void main(String[] args) {
        newBank().print();
    }

    public Account register(String pass1, String pass2, String name,
                            String personId, String email, int type) throws RegisterException {
        if (!pass1.equals(pass2)) {
            throw new RegisterException(name + " RegisterException--->register: PasswordError !  �����������벻һ�£�");
        } else {
            Object c = null;
            if (type == 0) {
                c = new SavingAccount(pass1, name, personId, email);
            } else if (type == 1) {
                c = new CreditAccount(pass1, name, personId, email);
            } else if (type == 2) {
                c = new LoanSavingAccount(pass1, name, personId, email);
            } else {
                if (type != 3) {
                    return null;
                }

                c = new LoanCreditAccount(pass1, name, personId, email);
            }

            this.map1.put(Long.valueOf(((Account) c).getId()), c);
            this.dao.saveAccounts(this.map1);
            return (Account) c;
        }
    }

    public Account login(long id, String password) throws LoginException {
        Account c = (Account) this.map1.get(new Long(id));
        if (c == null) {
            throw new LoginException("LoginException--->register:NotFoundId ! id �����ڣ�");
        } else if (!password.equals(c.getPassword())) {
            throw new LoginException("LoginException--->register:PasswordError ! �������");
        } else {
            System.out.println("Welcom \t" + c.getName() + " !");
            return c;
        }
    }

    public Account deposit(long id, double money) throws NullPointerException {
        Account c = (Account) this.map1.get(new Long(id));
        c.deposit(money);
        this.dao.saveAccounts(this.map1);
        return c;
    }

    public Account withdraw(long id, double money) throws BalanceNotEnoughException, NullPointerException {
        Account c = (Account) this.map1.get(new Long(id));
        c.withdraw(money);
        this.dao.saveAccounts(this.map1);
        return c;
    }

    public Account setCeiling(long id, double ceiling) {
        Account c = (Account) this.map1.get(new Long(id));
        if (c instanceof CreditAccount) {
            CreditAccount ca = (CreditAccount) c;
            ca.setCeiling(ceiling);
            this.dao.saveAccounts(this.map1);
        }

        return c;
    }

    public Account requestLoan(long id, double money) {
        Account c = (Account) this.map1.get(new Long(id));
        if (c instanceof Loannable) {
            Loannable an = (Loannable) c;
            an.requestLoan(money);
            this.dao.saveAccounts(this.map1);
        }

        return c;
    }

    public Account payLoan(long id, double money) throws LoanException, BalanceNotEnoughException {
        Account c = (Account) this.map1.get(new Long(id));
        if (c instanceof Loannable) {
            Loannable an = (Loannable) c;
            an.payLoan(money);
            this.dao.saveAccounts(this.map1);
        }

        return c;
    }

    public double getAllBalance() {
        double d = 0.0D;
        Collection c = this.map1.values();

        Account ac;
        for (Iterator it = c.iterator(); it.hasNext(); d += ac.getBalance()) {
            ac = (Account) it.next();
        }

        return d;
    }

    public double getAllCeiling() {
        double d = 0.0D;
        Collection c = this.map1.values();
        Iterator it = c.iterator();

        while (it.hasNext()) {
            Account ac = (Account) it.next();
            if (ac instanceof CreditAccount) {
                CreditAccount ca = (CreditAccount) ac;
                d += ca.getCeiling();
            }
        }

        return d;
    }

    public double getAllLoan() {
        double d = 0.0D;
        Collection c = this.map1.values();
        Iterator it = c.iterator();

        while (it.hasNext()) {
            Account ac = (Account) it.next();
            if (ac instanceof Loannable) {
                Loannable l = (Loannable) ac;
                d += l.getLoan();
            }
        }

        return d;
    }

    public void print() {
        HashMap map2 = new HashMap();
        Collection c = this.map1.values();
        Iterator it = c.iterator();

        while (it.hasNext()) {
            Account list = (Account) it.next();
            String set = list.getPersonId();
            if (map2.containsKey(set)) {
                Double ite = (Double) map2.get(set);
                map2.put(set, Double.valueOf(ite.doubleValue() + list.getBalance()));
            } else {
                map2.put(set, Double.valueOf(list.getBalance()));
            }
        }

        ArrayList list1 = new ArrayList();
        Set set1 = map2.keySet();
        Iterator ite1 = set1.iterator();

        while (ite1.hasNext()) {
            final String iter = (String) ite1.next();
            class Temp implements Comparable {
                String pid = iter;
                double money;

                public Temp(double maoney) {
                    this.money = maoney;
                }

                public String toString() {
                    return this.pid + " " + this.money;
                }

                public int compareTo(Object o) {
                    Temp t = (Temp) o;
                    return this.money > t.money ? -1 : (this.money == t.money ? 0 : 1);
                }
            }

            Temp o = new Temp(((Double) map2.get(iter)).doubleValue());
            list1.add(o);
        }

        Collections.sort(list1);
        Iterator iter1 = list1.iterator();

        while (iter1.hasNext()) {
            Object o1 = iter1.next();
            System.out.println(o1);
        }

    }
}

