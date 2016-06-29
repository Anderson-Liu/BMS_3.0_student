package model;
/*
 * �ɴ���ӿ�
 */

import Exception.BusinessException.BalanceNotEnoughException;
import Exception.BusinessException.LoanException;

public interface Loannable {
    void requestLoan(double var1);

    void payLoan(double var1) throws LoanException, BalanceNotEnoughException;

    double getLoan();
}


