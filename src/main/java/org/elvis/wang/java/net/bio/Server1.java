package org.elvis.wang.java.net.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by zhiqun.wang on 2019/7/3 22:38
 */
public class Server1 {

    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(8080);

        Socket client = null;
        boolean f = true;
        while(f){
            //等待客户端的连接，如果没有获取连接
            client = server.accept();
            System.out.println("与客户端连接成功！");
            //为每个客户端连接开启一个线程
            new Thread(new ServerThread(client)).start();
        }
        server.close();
    }
}
