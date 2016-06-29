package model;
/*
 * 可贷款接口
 */

import Exception.BusinessException.BalanceNotEnoughException;
import Exception.BusinessException.LoanException;

public interface Loannable {
    void requestLoan(double var1);

    void payLoan(double var1) throws LoanException, BalanceNotEnoughException;

    double getLoan();
}


