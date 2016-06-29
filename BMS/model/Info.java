package model;
/*
 * Info��������װ�ͻ������ݺͶ���(�൱��һ�������ָ��)��
 * �û���Ҫ��ʲô?���ʲô����?
 * ��Info�ཫ�û��Ķ����ֱ���Ϊ������̬��������ʾ;
 */

import java.io.Serializable;
import java.util.HashMap;

public class Info implements Serializable {
    public static final int REGISTER = 0;
    public static final int LOGIN = 1;
    public static final int BUSINESS = 2;
    private int type;
    private HashMap data = new HashMap();

    public Info(int type) {
        this.type = type;
    }

    public HashMap getData() {
        return this.data;
    }

    public int getType() {
        return this.type;
    }
}
