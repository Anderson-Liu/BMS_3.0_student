package Exception;

/*
 * 自己定义了一个异常BusinessException继承Exception类,
 * 让自己的的所有异常类都继承这个父类;
 * 为什么要使用内部类呢?
 * 因为设计只在一个包中来实现和完成自定义异常类(继承树)的结构;
 * 而一个包中只允许有一个公开(public)类,所以把其它异常类,都做
 * 成了BusinessException类的公开静态(public static...)的成员内部类;
 */

public class BusinessException extends Exception {
    public BusinessException(String message) {
        super(message);
    }

    public static class BalanceNotEnoughException extends BusinessException {
        public BalanceNotEnoughException(String message) {
            super(message);
        }
    }

    public static class LoanException extends BusinessException {
        public LoanException(String message) {
            super(message);
        }
    }

    public static class LoginException extends BusinessException {
        public LoginException(String message) {
            super(message);
        }
    }

    public static class RegisterException extends BusinessException {
        public RegisterException(String message) {
            super(message);
        }
    }
}
