package model;
/*
 * 用户可以贷款,可以透支
 */

import Exception.BusinessException.BalanceNotEnoughException;
import Exception.BusinessException.LoanException;

public class LoanCreditAccount extends CreditAccount implements Loannable {
    private double loanmoney = 0.0D;

    public LoanCreditAccount(String password, String name, String personId, String email) {
        super(password, name, personId, email);
    }

    public void requestLoan(double money) {
        this.loanmoney += money;
    }

    public void payLoan(double money) throws LoanException, BalanceNotEnoughException {
        if (this.loanmoney - money >= 0.0D) {
            this.withdraw(money);
            this.loanmoney -= money;
        } else {
            throw new LoanException("LoanException-->LoanCreditAccount  您不需要还这么多贷款 ! ");
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
