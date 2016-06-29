package model;
/*
 * �û����Դ���,������͸֧
 * �����͸֧�ǲ�һ����,͸ָ֧�����˻����С��0,�������û���Ҫһ������������.
 * ��ATM����,�û�����ѡ�����,Ҳ����ѡ�񻹴���,�����������Ҫ���˻�����ϵ��ʽ�ת���������
 * ����:�û����10000Ԫ,�����100000Ԫ,�û�����ѡ�񻹿�5000Ԫ,���û�����Ϊ5000,������Ϊ95000Ԫ.
 */

import Exception.BusinessException.BalanceNotEnoughException;
import Exception.BusinessException.LoanException;

public class LoanSavingAccount extends SavingAccount implements Loannable {
    private double loanmoney = 0.0D;

    public LoanSavingAccount(String password, String name, String personId, String email) {
        super(password, name, personId, email);
    }

    public void requestLoan(double money) {
        this.loanmoney += money;
    }

    public void payLoan(double money) throws LoanException, BalanceNotEnoughException {
        if (this.loanmoney - money >= 0.0D) {
            System.out.println("setBalance");
            this.withdraw(money);
            this.loanmoney -= money;
        } else {
            throw new LoanException("LoanException-->LoanSavingAccount ������Ҫ����ô����� ! ");
        }
    }

    public double getLoan() {
        return this.loanmoney;
    }

    public boolean equals(Object o) {
        boolean flag = super.equals(o);
        if (!flag) {
            return false;
        } else {
            Loannable c = (Loannable) o;
            return this.loanmoney == c.getLoan();
        }
    }

    public int hashCode() {
        return super.hashCode() ^ (new Double(this.loanmoney)).hashCode();
    }
}
