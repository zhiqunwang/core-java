package org.elvis.wang.java.net.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by zhiqun.wang on 2019/7/3 22:51
 *
 * 改进 使用线程池的方式去
 */
public class Server2 {

    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(8080);

        Socket client = null;

        ExecutorService pool = Executors.newCachedThreadPool();

        boolean f = true;
        while(f){
            //等待客户端的连接，如果没有获取连接
            client = server.accept();
            System.out.println("与客户端连接成功！");
            //为每个客户端连接开启一个线程
            pool.execute(new ServerThread(client));

        }
        server.close();
    }
}
