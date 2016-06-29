package Exception;

/*
 * �Լ�������һ���쳣BusinessException�̳�Exception��,
 * ���Լ��ĵ������쳣�඼�̳��������;
 * ΪʲôҪʹ���ڲ�����?
 * ��Ϊ���ֻ��һ��������ʵ�ֺ�����Զ����쳣��(�̳���)�Ľṹ;
 * ��һ������ֻ������һ������(public)��,���԰������쳣��,����
 * ����BusinessException��Ĺ�����̬(public static...)�ĳ�Ա�ڲ���;
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
