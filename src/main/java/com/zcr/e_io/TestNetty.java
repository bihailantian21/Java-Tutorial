package com.zcr.e_io;
/*

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

public class TestNetty {

    public void serve(int port) throws InterruptedException {
        final ByteBuf buffer = Unpooled.unreleasableBuffer(Unpooled.copiedBuffer("Hi\r\n", Charset.forName("UTF-8")));
        // 第一步，创建线程池
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try{
            // 第二步，创建启动类
            ServerBootstrap b = new ServerBootstrap();
            // 第三步，配置各组件
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)//.channel(OioServerSocketChannel.class) // 使用阻塞的SocketChannel
                    .localAddress(new InetSocketAddress(port))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(new ChannelInboundHandlerAdapter(){
                                @Override
                                public void channelActive(ChannelHandlerContext ctx) throws Exception {
                                    ctx.writeAndFlush(buffer.duplicate()).addListener(ChannelFutureListener.CLOSE);
                                }
                            });
                        }
                    });
            // 第四步，开启监听
            ChannelFuture f = b.bind().sync();
            f.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully().sync();
            workerGroup.shutdownGracefully().sync();
        }
    }
    */
/*
/**
 * UnpooledHeapByteBuf(ridx: 0, widx: 5, cap: 5/5)
 * UnpooledHeapByteBuf(ridx: 1, widx: 5, cap: 5/5)
 * UnpooledHeapByteBuf(ridx: 2, widx: 5, cap: 5/5)
 * UnpooledHeapByteBuf(ridx: 0, widx: 3, cap: 5/5)
 * UnpooledHeapByteBuf(ridx: 0, widx: 0, cap: 5/5)
 * UnpooledHeapByteBuf(ridx: 0, widx: 3, cap: 5/5)
 * mark:UnpooledHeapByteBuf(ridx: 0, widx: 3, cap: 5/5)
 * read:UnpooledHeapByteBuf(ridx: 2, widx: 3, cap: 5/5)
 * reset:UnpooledHeapByteBuf(ridx: 0, widx: 3, cap: 5/5)
 * 有兴趣的可以看一下上一篇分享的ByteBuffer，对比一下，就能发现在Netty中通过独立的读写索引维护，避免读写模式的切换，更加方便了。
 */
       /* public static void main(String[]args){
            //创建bytebuf
            ByteBuf buf = Unpooled.copiedBuffer("hello".getBytes());
            System.out.println(buf);

            // 读取一个字节
            buf.readByte();
            System.out.println(buf);

            // 读取一个字节
            buf.readByte();
            System.out.println(buf);

            // 丢弃无用数据
            buf.discardReadBytes();
            System.out.println(buf);

            // 清空
            buf.clear();
            System.out.println(buf);

            // 写入
            buf.writeBytes("123".getBytes());
            System.out.println(buf);

            buf.markReaderIndex();
            System.out.println("mark:"+buf);

            buf.readByte();
            buf.readByte();
            System.out.println("read:"+buf);

            buf.resetReaderIndex();
            System.out.println("reset:"+buf);
        }



}

*/