package com.gcy.demo.nettydemo;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class NioDemo {
    private final static ThreadPoolExecutor threadPoolExecutor =(ThreadPoolExecutor) Executors.newCachedThreadPool();
    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);//非阻塞模式
        serverSocketChannel.bind(new InetSocketAddress(8080));
        System.out.println("启动：8080");

        // 底层 操作系统的特性--jdk 跨平台统一封装--选择我们的程序需要处理的连接
        // 事件机制
        Selector selector = Selector.open();
        while (true) {
            //【非阻塞模式】 这个方法返回null ：serverSocketChannel.configureBlocking(false)
            SocketChannel socketChannel = serverSocketChannel.accept();
            if(socketChannel==null){
                continue;
            }
            socketChannel.configureBlocking(false);//设置非阻塞模式
            // 注册一个事件通知机制：read
            socketChannel.register(selector, SelectionKey.OP_READ);

            selector.select();
            Set<SelectionKey> eventKeys = selector.selectedKeys();

            Iterator<SelectionKey> iterator = eventKeys.iterator();
            while (iterator.hasNext()){
                SelectionKey event = iterator.next();
                if (event.isReadable()){
                    SocketChannel socketConnection = (SocketChannel) event.channel();
                    threadPoolExecutor.submit(()->{
                        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                        socketChannel.read(byteBuffer);
                        byteBuffer.flip();//翻转为读模式，翻译：将限制设置为当前位置，然后*位置设为零。如果标记已定义，则为丢弃。
                        System.out.println(new String(byteBuffer.array()));
                        return null;
                    });
                }
            }



        }
    }
}
