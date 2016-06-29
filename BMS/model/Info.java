package model;
/*
 * Info类用来封装客户的数据和动作(相当于一个特殊的指令)；
 * 用户想要干什么?完成什么操作?
 * 用Info类将用户的动作分别定义为三个静态常量来表示;
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
