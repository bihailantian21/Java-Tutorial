package com.zcr.e_io;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 基于原始的IO和Socket就可以编写一个最基本的BIO服务器。
 * 这个模型很简单，就是主线程（Acceptor）负责接收连接，然后开启新的线程专门负责连接处理客户端的请求。
 * 然后执行telnet localhost 5555，就能看到返回结果了。
 *
 * 这种阻塞模式的服务器，原理上很简单，问题也容易就暴露出来：
 * 1.服务端与客户端的连接相当于1:1，因此如果连接数上升，服务器的压力会很大
 * 2.如果主线程Acceptor阻塞，那么整个服务器将会阻塞，单点问题严重
 * 3.线程数膨胀后，整个服务器性能都会下降
 *
 * 改进的方式可以基于线程池或者消息队列，不过也存在一些问题：
 * 1.线程池的数量、消息队列后端服务器并发处理数，都是并发数的限制
 * 2.仍然存在Acceptor的单点阻塞问题
 *
 */
public class TestBIO {

    public void serve(int port) throws IOException {
        // 开启Socket服务器，并监听端口
        final ServerSocket socket = new ServerSocket(port);
        try{
            for(;;){
                // 轮训接收监听
                final Socket clientSocket = socket.accept();
                try {
                    Thread.sleep(500000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("accepted connection from "+clientSocket);
                // 创建新线程处理请求
                new Thread(()->{
                    OutputStream out;
                    try{
                        out = clientSocket.getOutputStream();
                        out.write("Hi\r\n".getBytes("UTF-8"));
                        out.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        try{
                            clientSocket.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        TestBIO server = new TestBIO();
        server.serve(5555);
    }
}
