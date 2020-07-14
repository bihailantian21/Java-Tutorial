package com.zcr.e_io;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 *
 */
public class TestNIO {

    public static void main(String[] args) throws IOException {

        /**
         * 得到的输出为：
         *  * java.nio.HeapByteBuffer[pos=0 lim=88 cap=88]
         *  * java.nio.HeapByteBuffer[pos=17 lim=88 cap=88]
         *  * java.nio.HeapByteBuffer[pos=0 lim=17 cap=88]
         *  * java.nio.HeapByteBuffer[pos=17 lim=17 cap=88]
         *  * Netty权威指南
         *  * 读者可以自己领会一下，这几个变量的含义。另外说明一点，如果遇到自己定义POJO类，就可以像这里的Buffer重载toString()方法，这样输出的时候就很方便了。
         */
        ByteBuffer buffer = ByteBuffer.allocate(88);
        System.out.println(buffer);

        String value = "Netty权威指南";
        buffer.put(value.getBytes());
        System.out.println(buffer);

        buffer.flip();
        System.out.println(buffer);

        byte[] v = new byte[buffer.remaining()];
        buffer.get(v);

        System.out.println(buffer);
        System.out.println(new String(v));


        /**
         * ByteBuffer在Channel中的使用
         *
         */
        String file = "xxxx/test.txt";
        RandomAccessFile accessFile = new RandomAccessFile(file, "rw");
        FileChannel fileChannel = accessFile.getChannel();
        // 20个字节
        ByteBuffer buffer2 = ByteBuffer.allocate(20);
        int bytesRead = fileChannel.read(buffer2);
        // buffer.put()也能写入buffer
        while (bytesRead != -1) {
            // 写切换到读
            buffer.flip();

            while (buffer.hasRemaining()) {
                System.out.println((char) buffer.get());
            }

            // buffer.rewind()重新读
            // buffer.mark()标记position buffer.reset()恢复

            // 清除缓冲区
            buffer.clear();
            // buffer.compact(); 清楚读过的数据
            bytesRead = fileChannel.read(buffer);


            /**
             * 创建ServerSocketChannel并绑定端口
             * 创建Selector多路复用器，并注册Channel
             * 循环监听是否有感兴趣的事件发生selector.select();
             * 获得事件的句柄，并进行处理
             * 其中Selector可以一次监听多个IO处理，效率就提高很多了。
             */
            TestNIO testNIO = new TestNIO();
            testNIO.serve(8080);

        }
    }


    /**
     * 1.创建一个选择器Selector
     * 2.创建一个服务端的Chanel，绑定到一个Socket对象、并把这个通信信道注册到选择器Selector上、把这个通信信道设置为非阻塞模式。
     * 3.调用Selector的selectedKeys方法来检查已经注册在这个选择器上的所有通信信道是否有需要的事件发生。
     * 如果有某个事件发生，将会返回所有的SelectionKey，通过这个对象Channel方法就可以取得这个通信信道对象，
     * 从而可以读取通信的数据。这里读取的数据是Buffer，是我们可以控制的缓冲器。
     * @param port
     * @throws IOException
     */
    public void serve(int port) throws IOException {
        // 创建channel，并绑定监听端口
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        ServerSocket ssocket = serverSocketChannel.socket();
        InetSocketAddress address = new InetSocketAddress(port);
        ssocket.bind(address);

        //创建selector,并将channel注册到selector
        Selector selector = Selector.open();
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        final ByteBuffer msg = ByteBuffer.wrap("Hi\r\b".getBytes());

        for(;;){
            try{
                selector.select();

            }catch (IOException e){
                e.printStackTrace();
                break;
            }

            Set<SelectionKey> readyKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = readyKeys.iterator();

            while(iterator.hasNext()){
                SelectionKey key = iterator.next();
                iterator.remove();

                try{
                    if(key.isAcceptable()){
                        ServerSocketChannel server = (ServerSocketChannel)key.channel();
                        SocketChannel client=  server.accept();
                        client.configureBlocking(false);
                        client.register(selector, SelectionKey.OP_WRITE | SelectionKey.OP_READ, msg.duplicate());
                        System.out.println("accepted connection from "+client);
                    }

                    if(key.isWritable()){
                        SocketChannel client = (SocketChannel) key.channel();
                        ByteBuffer buffer = (ByteBuffer) key.attachment();
                        while(buffer.hasRemaining()){
                            if(client.write(buffer)==0){
                                break;
                            }
                        }
                        client.close();
                    }
                }catch (IOException e){
                    key.cancel();
                    try{
                        key.channel().close();
                    } catch (IOException ex){
                        ex.printStackTrace();
                    }
                }
            }

        }
    }
}