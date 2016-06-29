package model;
/*
 * 储蓄账户，不允许透支
 */

import Exception.BusinessException.BalanceNotEnoughException;

public class SavingAccount extends Account {
    public SavingAccount(String password, String name, String personId, String email) {
        super(password, name, personId, email);
    }

    public synchronized void withdraw(double money) throws BalanceNotEnoughException {
        if (this.getBalance() - money >= 0.0D) {
            this.setBalance(this.getBalance() - money);
        } else {
            throw new BalanceNotEnoughException("BalanceNotEnoughException--->SavingAccount  您的余额不足 ! ");
        }
    }

    public boolean equals(Object o) {
        return super.equals(o);
    }

    public int hashCode() {
        return super.hashCode();
    }
}
