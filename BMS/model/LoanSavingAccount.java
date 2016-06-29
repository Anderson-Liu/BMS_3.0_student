package model;
/*
 * 用户可以贷款,不可以透支
 * 贷款和透支是不一样的,透支指的是账户余额小于0,而贷款用户需要一个贷款额的属性.
 * 在ATM机上,用户可以选择贷款,也可以选择还贷款,而还贷款就是要把账户余额上的资金转到贷款额上
 * 例如:用户余额10000元,贷款额100000元,用户可以选择还款5000元,则用户余额变为5000,贷款额变为95000元.
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
            throw new LoanException("LoanException-->LoanSavingAccount 您不需要还这么多贷款 ! ");
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
