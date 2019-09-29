package org.elvis.wang.java.net.nio;

import java.net.InetSocketAddress;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * Created by zhiqun.wang on 2019/7/4 13:35
 */
public class TCPEchoClientNonblocking {

    /**
     * args[0] 服务器端IP地址
     * args[1] 发送的字符串
     * args[2] 端口号
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {

        SocketChannel clientChannel = SocketChannel.open();
        clientChannel.configureBlocking(false);

        //向服务端发起连接
        if(!clientChannel.connect(new InetSocketAddress("localhost",8899))){
            while (!clientChannel.finishConnect()){
                //在等待连接的时间里，可以执行其他任务，以充分发挥非阻塞IO的异步特性
                //这里为了演示该方法的使用，只是一直打印"."
                System.out.println(".");
            }
        }

        //为了与后面打印的"."区别开来，这里输出换行符
        System.out.print("\n");

        byte[] messages = "hello world".getBytes();

        //分别实例化用来读写的缓冲区
        ByteBuffer writeBuf = ByteBuffer.wrap("hello world".getBytes());
        ByteBuffer readBuf = ByteBuffer.allocate("hello world".getBytes().length);

        //接收到的总的字节数
        int totalBytesRcvd = 0;
        //每一次调用read（）方法接收到的字节数
        int bytesRcvd;

        //循环执行，直到接收到的字节数与发送的字符串的字节数相等
        while (totalBytesRcvd < messages.length){
            //如果用来向通道中写数据的缓冲区中还有剩余的字节，则继续将数据写入信道
            if (writeBuf.hasRemaining()){
                clientChannel.write(writeBuf);
            }
            //如果read（）接收到-1，表明服务端关闭，抛出异常
            if ((bytesRcvd = clientChannel.read(readBuf)) == -1){
                throw new SocketException("Connection closed prematurely");
            }
            //计算接收到的总字节数
            totalBytesRcvd += bytesRcvd;
            //在等待通信完成的过程中，程序可以执行其他任务，以体现非阻塞IO的异步特性
            //这里为了演示该方法的使用，同样只是一直打印"."
            System.out.print(".");
        }
        //打印出接收到的数据
        System.out.println("Received: " +  new String(readBuf.array(), 0, totalBytesRcvd));
        //关闭信道
        clientChannel.close();

    }
}
