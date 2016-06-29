package model;
/*
 *信用账户实体，允许透支，允许用户设置自己的透支额度
 */

import Exception.BusinessException;
import Exception.BusinessException.BalanceNotEnoughException;

public class CreditAccount extends Account {
    private double ceiling;

    public CreditAccount(String password, String name, String personId, String email) {
        super(password, name, personId, email);
    }

    public double getCeiling() {
        return this.ceiling;
    }

    public double setCeiling(double ceiling) {
        return this.ceiling = ceiling;
    }

    public void withdraw(double money) throws BalanceNotEnoughException {
        if (this.getBalance() - money >= -1.0D * this.ceiling) {
            this.setBalance(this.getBalance() - money);
        } else {
            throw new BusinessException.BalanceNotEnoughException("BalanceNotEnoughException--->CreditAccount 您的取款额超出透支额度! ");

        }
    }

    public boolean equals(Object o) {
        boolean flag = super.equals(o);
        if (!flag) {
            return false;
        } else {
            CreditAccount c = (CreditAccount) o;
            return this.ceiling == c.ceiling;
        }
    }

    public int hashCode() {
        return super.hashCode() ^ (new Double(this.ceiling)).hashCode();
    }
}
