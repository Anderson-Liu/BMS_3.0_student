package Server;

/*
 * 银行服务器,一直在监听客户的请求过来;
 */

import java.net.ServerSocket;
import java.net.Socket;

public class BAMServer {
    public BAMServer() {
    }

    public static void main(String[] args) throws Exception {
        ServerSocket ss = new ServerSocket(9000);

        while (true) {
            Socket s = ss.accept();
            ServerThread t = new ServerThread(s);
            t.start();
        }
    }
}
