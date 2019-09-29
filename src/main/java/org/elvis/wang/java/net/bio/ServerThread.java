package org.elvis.wang.java.net.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

/**
 * Created by zhiqun.wang on 2019/7/3 22:32
 *
 *
 * server端 每建立一个socket连接即创建一个线程去服务
 */
public class ServerThread implements Runnable {

    private Socket client;

    public ServerThread(Socket client){
        this.client = client;
    }

    @Override
    public void run() {
        try {
            //获取Socket的输出流，用来向客户端发送数据
            PrintStream out = new PrintStream(client.getOutputStream());

            //获取Socket的输入流，用来接收从客户端发送过来的数据
            BufferedReader buf = new BufferedReader(new InputStreamReader(client.getInputStream()));

            boolean flag =true;
            while (flag){

                String str = buf.readLine();//阻塞
                if(str == null || "".equals(str)||"byte".equals(str)){
                    flag =false;
                }else{
                    out.println("echo: "+ str);
                }
            }

            out.close();
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
