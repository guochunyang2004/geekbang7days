package com.gcy.demo.nettydemo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class ServerSocketDemo {

    public static final ThreadPoolExecutor threadPoolExecutor =(ThreadPoolExecutor) Executors.newCachedThreadPool();

    public static void main(String[] args) {
        //test();
        socketDemo();
    }

    public static void socketDemo(){
        try {
            ServerSocket serverSocket = new ServerSocket(8080);
            System.out.println("启动8080");
            byte[] bytes =new byte[1024];
            while (true){
                Socket socketConnection = serverSocket.accept();
                threadPoolExecutor.submit(()->{
                    socketConnection.getInputStream().read(bytes);
                    System.out.println(new String(bytes));
                    return null;
                });
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void test() {


        int port = 4343; //端口号
        // Socket 服务器端（简单的发送信息）
        Thread sThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    ServerSocket serverSocket = new ServerSocket(port);
                    while (true) {
                        // 等待连接
                        Socket socket = serverSocket.accept();
                        Thread sHandlerThread = new Thread(new Runnable() {
                            @Override
                            public void run() {

                                try (PrintWriter printWriter = new PrintWriter(socket.getOutputStream())) {
                                    printWriter.println("hello world！");
                                    printWriter.flush();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                        sHandlerThread.start();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        sThread.start();

// Socket 客户端（接收信息并打印）
        try (Socket cSocket = new Socket(InetAddress.getLocalHost(), port)) {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(cSocket.getInputStream()));
            bufferedReader.lines().forEach(s -> System.out.println("客户端：" + s));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
